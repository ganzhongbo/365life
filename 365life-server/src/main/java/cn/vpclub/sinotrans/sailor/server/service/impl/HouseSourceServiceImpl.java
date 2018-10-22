package cn.vpclub.sinotrans.sailor.server.service.impl;


import cn.vpclub.demo.common.model.core.enums.ReturnCodeEnum;
import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.sinotrans.sailor.feign.domain.constants.FollowRecordConstant;
import cn.vpclub.sinotrans.sailor.feign.domain.constants.HouseSourceConstants;
import cn.vpclub.sinotrans.sailor.feign.domain.constants.UserResourceConstants;
import cn.vpclub.sinotrans.sailor.feign.domain.dto.HouseSourceDTO;
import cn.vpclub.sinotrans.sailor.feign.domain.dto.SourceDetailDTO;
import cn.vpclub.sinotrans.sailor.feign.domain.dto.TradeRecordDTO;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.*;
import cn.vpclub.sinotrans.sailor.feign.model.request.AppointRecordRequest;
import cn.vpclub.sinotrans.sailor.feign.model.request.BringRecordRequest;
import cn.vpclub.sinotrans.sailor.feign.model.request.FollowRecordRequest;
import cn.vpclub.sinotrans.sailor.feign.model.request.HouseSourceRequest;
import cn.vpclub.sinotrans.sailor.server.dao.HouseSourceDao;
import cn.vpclub.sinotrans.sailor.server.service.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 房源基本信息表 服务实现类
 *
 * @author 南政
 * @since 2018-10-15
 */
@Service
public class HouseSourceServiceImpl extends ServiceImpl<HouseSourceDao, HouseSource> implements HouseSourceService {

    @Resource
    private SourceDetailService sourceDetailService;

    @Resource
    private UserResouceServise useResourceService;

    @Resource
    private FollowRecordService followRecordService;

    @Resource
    private BringRecordService bringRecordService;

    @Resource
    private AppointRecordService appointRecordService;

    @Resource
    private TradeRecordService tradeRecordService;


    /**
     * 房源信息-分页列表
     *
     * @param param
     * @return
     */
    @Override
    public BaseResponse<Page<HouseSourceDTO>> getPage(HouseSourceRequest param) {
        BaseResponse<Page<HouseSourceDTO>> response = new BaseResponse<>();
        EntityWrapper<HouseSource> wrapper = new EntityWrapper<>();
        //时间倒序
        wrapper.orderBy("created_time", false);
        //搜索条件-商圈
        wrapper.eq(null == param.getTradeAreaId(), "trade_area_id", param.getTradeAreaId());
        //搜索条件-户型
        wrapper.eq(StringUtils.isNotBlank(param.getModelName()), "model_name", param.getModelName());
        //搜索条件-类型
        wrapper.eq(null != param.getHouseType(), "house_type", param.getHouseType());
        //搜索条件-属性
        if (null != param.getHouseNature()) {
            //如果是公盘
            if (param.getHouseNature() == HouseSourceConstants.PUBLIC_SOURCE) {
                wrapper.isNull("user_id");
            } else if (param.getHouseNature() == HouseSourceConstants.PRIVATE_SOURCE) {
                wrapper.isNotNull("user_id");
            }
        }
        //是否租售（根据状态）
        wrapper.eq(null == param.getHouseStatus(), "house_status", param.getHouseStatus());

        Page<HouseSource> page = new Page<>();

        page.setCurrent(null == param.getPageNumber() ? 1 : param.getPageNumber());
        page.setSize(null == param.getPageSize() ? 10 : param.getPageSize());
        //执行查询
        page = selectPage(page, wrapper);
        //再去查找图片
        List<HouseSource> houseSources = page.getRecords();

        List<SourceDetailDTO> sourceDetailDTOS = new ArrayList<>();

        if (CollectionUtils.isEmpty(houseSources)) {
            List<Long> sourceIds = new ArrayList<>(houseSources.size());
            for (HouseSource houseSource : houseSources) {
                sourceIds.add(houseSource.getId());
            }
            sourceDetailDTOS = sourceDetailService.getDetailByIds(sourceIds);
        }

        List<HouseSourceDTO> sourceDTOS = new ArrayList<>();
        for (HouseSource houseSource : houseSources) {
            HouseSourceDTO houseSourceDTO = new HouseSourceDTO();
            BeanUtils.copyProperties(houseSource, houseSourceDTO);
            for (SourceDetailDTO sourceDetailDTO : sourceDetailDTOS) {
                if (houseSource.getId().compareTo(sourceDetailDTO.getSourceId()) == 0) {
                    houseSourceDTO.setSourceDetailDTO(sourceDetailDTO);
                    break;
                }
            }
            sourceDTOS.add(houseSourceDTO);
        }
        Page<HouseSourceDTO> dtoPage = new Page<>();
        BeanUtils.copyProperties(page, dtoPage);
        dtoPage.setRecords(sourceDTOS);
        response.setReturnCode(ReturnCodeEnum.CODE_1000.getCode());
        response.setMessage("请求成功");
        response.setDataInfo(dtoPage);
        return response;
    }

    /**
     * 房源信息-新增修改
     *
     * @param houseSource
     * @return
     */
    @Override
    public BaseResponse<Boolean> saveOrUpdate(HouseSource houseSource) {
        BaseResponse<Boolean> response = new BaseResponse<>();
        //判断是否有相关的房源信息(根据商圈、小区名字、楼栋号、单元号和门室号判断唯一)
        EntityWrapper<HouseSource> wrapper = new EntityWrapper<>();
        wrapper.eq("detail_address", houseSource.getDetailAddress());
        wrapper.eq("community_name", houseSource.getCommunityName());
        wrapper.eq("building_number", houseSource.getBuildingNumber());
        wrapper.eq("unit_number", houseSource.getUnitNumber());
        wrapper.eq("room_number", houseSource.getRoomNumber());
        HouseSource entity = selectOne(wrapper);
        if (null != entity) {
            //如果id为空则为新增，那么就重复了
            if (null == houseSource.getId()) {
                response.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
                response.setMessage("新增失败，存在相同的房源");
                return response;
            }
            //根据id查找是否有实勘信息，如果有那么基本信息不能修改
            EntityWrapper<SourceDetail> wrapper1 = new EntityWrapper<>();
            wrapper1.eq("source_id", houseSource.getId());
            SourceDetail sourceDetail = sourceDetailService.selectOne(wrapper1);
            if (null != sourceDetail) {
                response.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
                response.setMessage("已存在实勘信息，不可修改");
                return response;
            }

            //id不为空但是与查出来的对象的id不同，那么就重复了
            if (houseSource.getId().compareTo(entity.getId()) != 0) {
                response.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
                response.setMessage("修改失败，存在相同的房源");
                return response;
            }
        }
        boolean success = insertOrUpdate(houseSource);
        response.setDataInfo(success);
        if (!success) {
            response.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
            response.setMessage("操作失败");
            return response;
        }
        response.setReturnCode(ReturnCodeEnum.CODE_1000.getCode());
        response.setMessage("操作成功");
        return response;
    }

    /**
     * 房源信息-获取详情
     *
     * @param houseSource
     * @return
     */
    @Override
    public BaseResponse<HouseSourceDTO> getDetail(HouseSource houseSource) {
        BaseResponse<HouseSourceDTO> response = new BaseResponse<>();
        //先查找房源基本信息，如果不存在那么直接返回
        HouseSource entity = selectById(houseSource.getId());
        if (null == entity) {
            response.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
            response.setMessage("房源基本信息为空");
            return response;
        }
        //需要返回的对象
        HouseSourceDTO houseSourceDTO = new HouseSourceDTO();
        //属性拷贝
        BeanUtils.copyProperties(entity, houseSourceDTO);
        //再去查找实勘信息
        EntityWrapper<SourceDetail> wrapper = new EntityWrapper<>();
        wrapper.eq("source_id", houseSource.getId());
        SourceDetail sourceDetail = sourceDetailService.selectOne(wrapper);
        //如果不为空，那么则去查找相关的文件信息
        if (null != sourceDetail) {
            //实勘完整信息
            SourceDetailDTO sourceDetailDTO = new SourceDetailDTO();
            //属性拷贝
            BeanUtils.copyProperties(sourceDetail, sourceDetailDTO);
            //查找文件集合
            EntityWrapper<UserResouceEntity> resourceEntityEntityWrapper = new EntityWrapper<>();
            resourceEntityEntityWrapper.eq("resouse_id", sourceDetail.getId());
            List<UserResouceEntity> files = useResourceService.selectList(resourceEntityEntityWrapper);
            List<UserResouceEntity> houseImg = new ArrayList<>();
            List<UserResouceEntity> propertyDelivery = new ArrayList<>();
            //如果文件不为空，归档
            if (null != files && files.size() > 0) {
                for (UserResouceEntity resourceEntity : files) {
                    if (resourceEntity.getType() == UserResourceConstants.HOUSE_IMAGE) {
                        houseImg.add(resourceEntity);
                    }
                    if (resourceEntity.getType() == UserResourceConstants.PROPERTY_DELIVERY) {
                        propertyDelivery.add(resourceEntity);
                    }
                }
            }
            //房屋图片集合放入
            sourceDetailDTO.setHouseImg(houseImg);
            //物业交接文件集合放入
            sourceDetailDTO.setPropertyDelivery(propertyDelivery);
            //实勘信息放入基本信息的拓展类
            houseSourceDTO.setSourceDetailDTO(sourceDetailDTO);
        }
        //再查询出跟进信息
        FollowRecordRequest followRequest = new FollowRecordRequest();
        followRequest.setFollowType(FollowRecordConstant.HOUSE_SOURCE);
        followRequest.setSourceId(entity.getId());
        List<FollowRecord> followRecords = followRecordService.getList(followRequest).getDataInfo();
        //将跟进信息放入返回对象
        houseSourceDTO.setFollowRecords(followRecords);
        //查询出带看信息的集合
        BringRecordRequest bringRequest = new BringRecordRequest();
        bringRequest.setSourceId(entity.getId());
        List<BringRecord> bringRecords = bringRecordService.getList(bringRequest).getDataInfo();
        //将带看信息放入返回对象
        houseSourceDTO.setBringRecords(bringRecords);
        //查询出预约信息的集合
        AppointRecordRequest appointRequest = new AppointRecordRequest();
        appointRequest.setSourceId(entity.getId());
        List<AppointRecord> appointRecords = appointRecordService.getList(appointRequest).getDataInfo();
        //将预约信息放入返回对象
        houseSourceDTO.setAppointRecords(appointRecords);
        //查询出成交信息
        TradeRecord tradeRecord = new TradeRecord();
        tradeRecord.setSourceId(entity.getId());
        TradeRecordDTO tradeRecordDTO = tradeRecordService.getTradeRecord(tradeRecord).getDataInfo();
        //将成交信息放入返回对象
        houseSourceDTO.setTradeRecordDTO(tradeRecordDTO);

        response.setReturnCode(ReturnCodeEnum.CODE_1000.getCode());
        response.setMessage("请求成功");
        response.setDataInfo(houseSourceDTO);
        return response;
    }
}
