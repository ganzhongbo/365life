package cn.vpclub.sinotrans.sailor.server.service.impl;


import cn.vpclub.demo.common.model.core.enums.ReturnCodeEnum;
import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.sinotrans.sailor.feign.domain.constants.AppointRecordConstant;
import cn.vpclub.sinotrans.sailor.feign.domain.constants.HouseSourceConstants;
import cn.vpclub.sinotrans.sailor.feign.domain.constants.UserResourceConstants;
import cn.vpclub.sinotrans.sailor.feign.domain.dto.TradeRecordDTO;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.AppointRecord;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.HouseSource;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.TradeRecord;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.UserResouceEntity;
import cn.vpclub.sinotrans.sailor.server.dao.TradeRecordDao;
import cn.vpclub.sinotrans.sailor.server.service.AppointRecordService;
import cn.vpclub.sinotrans.sailor.server.service.HouseSourceService;
import cn.vpclub.sinotrans.sailor.server.service.TradeRecordService;
import cn.vpclub.sinotrans.sailor.server.service.UserResouceServise;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 房源成交记录表 服务实现类
 *
 * @author 南政
 * @since 2018-10-17
 */
@Service
public class TradeRecordServiceImpl extends ServiceImpl<TradeRecordDao, TradeRecord> implements TradeRecordService {

    @Resource
    private UserResouceServise userResourceService;

    @Resource
    private HouseSourceService houseSourceService;

    @Resource
    private AppointRecordService appointRecordService;

    /**
     * 成交记录-获取成交记录
     *
     * @param tradeRecord
     * @return
     */
    @Override
    public BaseResponse<TradeRecordDTO> getTradeRecord(TradeRecord tradeRecord) {
        BaseResponse<TradeRecordDTO> response = new BaseResponse<>();
        EntityWrapper<TradeRecord> wrapper = new EntityWrapper<>();
        wrapper.eq("source_id", tradeRecord.getSourceId());
        TradeRecord entity = selectOne(wrapper);
        if (null == entity) {
            response.setReturnCode(ReturnCodeEnum.CODE_1000.getCode());
            response.setMessage("请求成功，但无数据");
            response.setDataInfo(new TradeRecordDTO());
            return response;
        }
        //如果不为空那么去查询相关的合同资料
        EntityWrapper<UserResouceEntity> wrapper1 = new EntityWrapper<>();
        wrapper1.eq("resouse_id", entity.getId());
        wrapper1.eq("type", UserResourceConstants.CONTRACT_DOCUMENT);
        List<UserResouceEntity> list = userResourceService.selectList(wrapper1);
        TradeRecordDTO tradeRecordDTO = new TradeRecordDTO();
        BeanUtils.copyProperties(entity, tradeRecordDTO);
        tradeRecordDTO.setFiles(list);
        response.setReturnCode(ReturnCodeEnum.CODE_1000.getCode());
        response.setMessage("请求成功");
        response.setDataInfo(tradeRecordDTO);
        return response;
    }

    /**
     * 成交记录-新增成交记录
     *
     * @param tradeRecordDTO
     * @return
     */
    @Override
    @Transactional
    public BaseResponse<Boolean> save(TradeRecordDTO tradeRecordDTO) {
        BaseResponse<Boolean> response = new BaseResponse<>();
        //先查询房源是否存在
        HouseSource houseSource = houseSourceService.selectById(tradeRecordDTO.getSourceId());
        if (null == houseSource) {
            response.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
            response.setMessage("房源不存在");
            return response;
        }
        if (houseSource.getHouseStatus() < HouseSourceConstants.HAVE_SOLID) {
            response.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
            response.setMessage("房源未实勘");
            return response;
        }
        if (houseSource.getHouseStatus() < HouseSourceConstants.HAVE_RESERVED) {
            response.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
            response.setMessage("房源未预约，无法成交");
            return response;
        }
        if (houseSource.getHouseStatus() == HouseSourceConstants.HAVE_TRADE) {
            response.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
            response.setMessage("房源已成交");
            return response;
        }
        //再去查询房源预约记录
        EntityWrapper<AppointRecord> wrapper = new EntityWrapper<>();
        wrapper.eq("source_id", tradeRecordDTO.getSourceId());
        wrapper.orderBy("created_time", false);
        List<AppointRecord> list = appointRecordService.selectList(wrapper);
        if (CollectionUtils.isEmpty(list) || list.get(0).getAppointStatus() == AppointRecordConstant.NOT_DEALED) {
            response.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
            response.setMessage("无预约记录或预约不成功");
            return response;
        }
        TradeRecord tradeRecord = new TradeRecord();
        BeanUtils.copyProperties(tradeRecordDTO, tradeRecord);
        boolean success = insert(tradeRecord);
        if (!success) {
            response.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
            response.setMessage("保存成交记录失败");
            return response;
        }
        List<UserResouceEntity> files = tradeRecordDTO.getFiles();
        for (UserResouceEntity file : files) {
            file.setResouseId(tradeRecord.getId());
            file.setType(UserResourceConstants.CONTRACT_DOCUMENT);
        }
        success = userResourceService.insertBatch(files);
        if (!success) {
            response.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
            response.setMessage("保存合同文件出错");
            return response;
        }
        //在去修改房源的状态为已成交
        HouseSource entity = new HouseSource();
        entity.setId(tradeRecordDTO.getSourceId());
        entity.setHouseStatus(HouseSourceConstants.HAVE_TRADE);
        entity.setUpdatedBy(tradeRecordDTO.getUpdatedBy());
        entity.setUpdatedTime(new Date().getTime());
        //执行修改
        success = houseSourceService.updateById(entity);
        response.setDataInfo(success);
        if (!success) {
            response.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
            response.setMessage("修改房源状态失败");
            return response;
        }
        response.setReturnCode(ReturnCodeEnum.CODE_1000.getCode());
        response.setMessage("保存成功");
        return response;
    }
}
