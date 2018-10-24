package cn.vpclub.sinotrans.sailor.server.service.impl;


import cn.vpclub.demo.common.model.core.enums.ReturnCodeEnum;
import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.sinotrans.sailor.feign.domain.constants.AppointRecordConstant;
import cn.vpclub.sinotrans.sailor.feign.domain.constants.HouseSourceConstants;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.AppointRecord;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.BringRecord;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.HouseSource;
import cn.vpclub.sinotrans.sailor.feign.model.request.AppointRecordRequest;
import cn.vpclub.sinotrans.sailor.server.dao.AppointRecordDao;
import cn.vpclub.sinotrans.sailor.server.service.AppointRecordService;
import cn.vpclub.sinotrans.sailor.server.service.BringRecordService;
import cn.vpclub.sinotrans.sailor.server.service.HouseSourceService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 房源预约记录表 服务实现类
 *
 * @author 南政
 * @since 2018-10-16
 */
@Service
public class AppointRecordServiceImpl extends ServiceImpl<AppointRecordDao, AppointRecord> implements AppointRecordService {

    @Resource
    private HouseSourceService houseSourceService;

    @Resource
    private BringRecordService bringRecordService;

    /**
     * 预约记录-分页列表
     *
     * @param param
     * @return
     */
    @Override
    public BaseResponse<List<AppointRecord>> getList(AppointRecordRequest param) {
        BaseResponse<List<AppointRecord>> response = new BaseResponse<>();
        EntityWrapper<AppointRecord> wrapper = new EntityWrapper<>();
        //时间倒序
        wrapper.orderBy("created_time", false);
        //搜索按照房源id
        wrapper.eq("source_id", param.getSourceId());
        //执行查询
        List<AppointRecord> list = selectList(wrapper);
        response.setReturnCode(ReturnCodeEnum.CODE_1000.getCode());
        response.setMessage("请求成功");
        response.setDataInfo(list);
        return response;
    }

    /**
     * 预约信息-预约新增
     *
     * @param appointRecord
     * @return
     */
    @Override
    public BaseResponse<Boolean> save(AppointRecord appointRecord) {
        BaseResponse<Boolean> response = new BaseResponse<>();
        //先查询是否有这个房源
        HouseSource houseSource = houseSourceService.selectById(appointRecord.getSourceId());
        if (null == houseSource) {
            response.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
            response.setMessage("房源不存在");
            return response;
        }
        //再判断状态
        if (houseSource.getHouseStatus() < HouseSourceConstants.HAVE_SOLID) {
            response.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
            response.setMessage("房源信息未实勘");
            return response;
        }
        //再判断状态
        if (houseSource.getHouseStatus() > HouseSourceConstants.HAVE_SOLID) {
            response.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
            response.setMessage("房源已预约或已成交");
            return response;
        }
        //再判断是否有此客户的带看信息
        EntityWrapper<BringRecord> wrapper = new EntityWrapper<>();
        //按预约时间之前
        wrapper.lt("created_time", appointRecord.getAppointTime());
        //搜索房源id
        wrapper.eq("source_id", appointRecord.getSourceId());
        //搜索同一个客户手机号
        wrapper.eq("passenger_phone", appointRecord.getPassengerPhone());
        //搜索同一个客户名字
        wrapper.eq("passenger_name", appointRecord.getPassengerName());
        //通过房源id和客户电话以及客户姓名判断是否有待看信息
        List<BringRecord> list = bringRecordService.selectList(wrapper);
        if (null == list || list.size() <= 0) {
            response.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
            response.setMessage("无客户带看数据");
            return response;
        }
        //保存信息
        boolean success = insert(appointRecord);
        if (!success) {
            response.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
            response.setMessage("保存失败");
            return response;
        }
        //修改房源的当前状态
        HouseSource entity = new HouseSource();
        entity.setId(appointRecord.getSourceId());
        entity.setHouseStatus(HouseSourceConstants.HAVE_RESERVED);
        entity.setUpdatedBy(appointRecord.getUpdatedBy());
        entity.setUpdatedTime(appointRecord.getUpdatedTime());
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

    /**
     * 预约信息-修改状态
     *
     * @param appointRecord
     * @return
     */
    @Override
    @Transactional
    public BaseResponse<Boolean> updateStatus(AppointRecord appointRecord) {
        BaseResponse<Boolean> response = new BaseResponse<>();
        //先判断是否有这条预约记录
        AppointRecord entity = selectById(appointRecord.getId());
        //如果为空，则不存在
        if (null == entity) {
            response.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
            response.setMessage("未找到预约信息");
            return response;
        }
        //在去判断填写这条记录的预约人是不是此次的修改人
        if (entity.getAppointId().compareTo(appointRecord.getUpdatedBy()) != 0) {
            response.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
            response.setMessage("非本人预约，不能修改");
            return response;
        }
        //执行修改
        boolean success = updateById(appointRecord);
        if (!success) {
            response.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
            response.setMessage("修改失败");
            return response;
        }
        //如果是未达成签约再去修改房源状态
        if (appointRecord.getAppointStatus() == AppointRecordConstant.NOT_DEALED) {
            HouseSource houseSource = new HouseSource();
            houseSource.setId(entity.getSourceId());
            houseSource.setHouseStatus(HouseSourceConstants.HAVE_SOLID);
            houseSource.setUpdatedBy(appointRecord.getUpdatedBy());
            houseSource.setUpdatedTime(appointRecord.getUpdatedTime());
            success = houseSourceService.updateById(houseSource);
            if (!success) {
                response.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
                response.setMessage("修改房源信息状态失败");
            }
        }
        response.setDataInfo(success);
        response.setReturnCode(ReturnCodeEnum.CODE_1000.getCode());
        response.setMessage("修改成功");
        return response;
    }
}
