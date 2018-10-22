package cn.vpclub.sinotrans.sailor.server.service.impl;


import cn.vpclub.demo.common.model.core.enums.ReturnCodeEnum;
import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.demo.common.model.utils.common.IdWorker;
import cn.vpclub.sinotrans.sailor.feign.domain.constants.HouseSourceConstants;
import cn.vpclub.sinotrans.sailor.feign.domain.constants.UserResourceConstants;
import cn.vpclub.sinotrans.sailor.feign.domain.dto.SourceDetailDTO;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.HouseSource;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.SourceDetail;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.UserResouceEntity;
import cn.vpclub.sinotrans.sailor.server.dao.SourceDetailDao;
import cn.vpclub.sinotrans.sailor.server.service.HouseSourceService;
import cn.vpclub.sinotrans.sailor.server.service.SourceDetailService;
import cn.vpclub.sinotrans.sailor.server.service.UserResouceServise;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 房源实勘详细信息表 服务实现类
 *
 * @author 南政
 * @since 2018-10-15
 */
@Service
public class SourceDetailServiceImpl extends ServiceImpl<SourceDetailDao, SourceDetail> implements SourceDetailService {

    @Resource
    private HouseSourceService houseSourceService;

    @Resource
    private UserResouceServise userResourceService;

    /**
     * 房源实勘信息-新增修改
     *
     * @param sourceDetailDTO
     * @return
     */
    @Override
    @Transactional
    public BaseResponse<Boolean> saveOrUpdate(SourceDetailDTO sourceDetailDTO) {
        BaseResponse<Boolean> response = new BaseResponse<>();
        HouseSource houseSource = houseSourceService.selectById(sourceDetailDTO.getSourceId());
        //是否存在相关的基本信息
        if (null == houseSource) {
            response.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
            response.setMessage("房源基本信息不存在");
            return response;
        }
        SourceDetail sourceDetail = new SourceDetail();
        //对象拷贝
        BeanUtils.copyProperties(sourceDetailDTO, sourceDetail);
        //如果id不为空，那么就是修改
        if (null != sourceDetailDTO.getId()) {
            //先将图片和文件删除
            EntityWrapper<UserResouceEntity> wrapper = new EntityWrapper<>();
            wrapper.eq("resouse_id", sourceDetail.getId());
            //执行删除原有文件
            boolean success = userResourceService.delete(wrapper);
            if (!success) {
                response.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
                response.setMessage("删除原文件出错");
                return response;
            }
        }
        //房屋图片文件集合
        List<UserResouceEntity> houseImages = sourceDetailDTO.getHouseImg();
        //物业交接文件集合
        List<UserResouceEntity> propertyDeliveries = sourceDetailDTO.getPropertyDelivery();
        boolean success = insertOrUpdate(sourceDetail);
        if (!success) {
            response.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
            response.setMessage("操作失败");
            return response;
        }
        //循环赋值
        for (UserResouceEntity houseImg : houseImages) {
            houseImg.setId(IdWorker.getId());
            houseImg.setResouseId(sourceDetail.getId());
            houseImg.setType(UserResourceConstants.HOUSE_IMAGE);
        }
        //循环赋值
        for (UserResouceEntity propertyDelivery : propertyDeliveries) {
            propertyDelivery.setId(IdWorker.getId());
            propertyDelivery.setResouseId(sourceDetail.getId());
            propertyDelivery.setType(UserResourceConstants.PROPERTY_DELIVERY);
        }
        //集合相加
        houseImages.addAll(propertyDeliveries);
        //批量执行
        success = userResourceService.insertBatch(houseImages);
        if (!success) {
            response.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
            response.setMessage("批量操作文件失败");
            return response;
        }
        //如果是增加，那么修改基本信息的房屋状态
        if (null == sourceDetailDTO.getId()) {
            HouseSource entity = new HouseSource();
            entity.setId(sourceDetailDTO.getSourceId());
            entity.setHouseStatus(HouseSourceConstants.HAVE_SOLID);
            entity.setUpdatedBy(sourceDetailDTO.getUpdatedBy());
            entity.setUpdatedTime(sourceDetailDTO.getUpdatedTime());
            success = houseSourceService.updateById(entity);
            response.setDataInfo(success);
            if (!success) {
                response.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
                response.setMessage("更新房源基本信息失败");
                return response;
            }
        }
        response.setReturnCode(ReturnCodeEnum.CODE_1000.getCode());
        response.setMessage("操作成功");
        return response;
    }

    /**
     * 通过房源id获取实勘信息集合（包括图片）
     *
     * @param sourceIds
     * @return
     */
    @Override
    public List<SourceDetailDTO> getDetailByIds(List<Long> sourceIds) {
        List<SourceDetail> sourceDetails = new ArrayList<>();
        List<UserResouceEntity> resources = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(sourceIds)) {
            //先通过id查询实勘信息
            EntityWrapper<SourceDetail> wrapper = new EntityWrapper<>();
            wrapper.in("source_id", sourceIds);
            sourceDetails = selectList(wrapper);
            //如果实勘信息不为空
            if (CollectionUtils.isNotEmpty(sourceDetails)) {
                List<Long> detailIds = new ArrayList<>(sourceDetails.size());
                for (SourceDetail sourceDetail : sourceDetails) {
                    detailIds.add(sourceDetail.getId());
                }
                //再去查找文件资料
                EntityWrapper<UserResouceEntity> wrapper1 = new EntityWrapper<>();
                wrapper1.in("resouse_id", detailIds);
                resources = userResourceService.selectList(wrapper1);
            }
        }
        List<SourceDetailDTO> detailDTOS = new ArrayList<>();
        for (SourceDetail sourceDetail : sourceDetails) {
            SourceDetailDTO sourceDetailDTO = new SourceDetailDTO();
            List<UserResouceEntity> houseImgs = new ArrayList<>();
            for (UserResouceEntity resource : resources) {
                if (sourceDetail.getId().compareTo(resource.getResouseId()) == 0 && resource.getType() == UserResourceConstants.HOUSE_IMAGE) {
                    houseImgs.add(resource);
                }
            }
            BeanUtils.copyProperties(sourceDetail, sourceDetailDTO);
            sourceDetailDTO.setHouseImg(houseImgs);
            detailDTOS.add(sourceDetailDTO);
        }
        return detailDTOS;
    }

}
