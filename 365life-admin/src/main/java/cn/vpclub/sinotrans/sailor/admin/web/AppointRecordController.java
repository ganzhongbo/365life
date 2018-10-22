package cn.vpclub.sinotrans.sailor.admin.web;


import cn.vpclub.demo.common.model.core.enums.ReturnCodeEnum;
import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.sinotrans.sailor.admin.service.AppointRecordService;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.AppointRecord;
import cn.vpclub.sinotrans.sailor.feign.model.request.AppointRecordRequest;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 房源预约记录表 前端控制器
 *
 * @author 南政
 * @since 2018-10-16
 */
@RestController
@RequestMapping("/appointRecord")
public class AppointRecordController {

    @Resource
    private AppointRecordService appointRecordService;

    /**
     * 预约记录-分页列表
     *
     * @param param
     * @return
     */
    @PostMapping(value = "/getList")
    public BaseResponse<List<AppointRecord>> getList(@RequestBody AppointRecordRequest param) {
        BaseResponse<List<AppointRecord>> response = new BaseResponse<>();
        if (null == param.getSourceId()) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参房源主键sourceId为空");
            return response;
        }
        return appointRecordService.getList(param);
    }

    /**
     * 预约信息-预约新增
     *
     * @param appointRecord
     * @return
     */
    @PostMapping(value = "/save")
    public BaseResponse<Boolean> save(@RequestBody AppointRecord appointRecord) {
        BaseResponse<Boolean> response = new BaseResponse<>();
        if (null == appointRecord.getSourceId()) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参房源主键sourceId为空");
            return response;
        }
        if (StringUtils.isBlank(appointRecord.getAppointNeed())) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参经纪人所需材料appointNeed为空");
            return response;
        }
        if (StringUtils.isBlank(appointRecord.getPassengerName())) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参客户姓名passengerName为空");
            return response;
        }
        if (StringUtils.isBlank(appointRecord.getPassengerPhone())) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参客户电话passengerPhone为空");
            return response;
        }
        if (StringUtils.isBlank(appointRecord.getPassengerNeed())) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参客户所需材料passengerNeed为空");
            return response;
        }
        if (null == appointRecord.getAppointTime()) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参预约时间appointTime为空");
            return response;
        }
        return appointRecordService.save(appointRecord);
    }

    /**
     * 预约信息-修改状态
     *
     * @param appointRecord
     * @return
     */
    @PostMapping(value = "/updateStatus")
    public BaseResponse<Boolean> updateStatus(@RequestBody AppointRecord appointRecord) {
        BaseResponse<Boolean> response = new BaseResponse<>();
        if (null == appointRecord.getId()) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参预约信息主键id为空");
            return response;
        }
        if(null == appointRecord.getAppointStatus()){
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参预约结果appointStatus为空");
            return response;
        }
        return appointRecordService.updateStatus(appointRecord);
    }

}

