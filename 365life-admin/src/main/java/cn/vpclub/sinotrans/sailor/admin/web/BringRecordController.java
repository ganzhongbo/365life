package cn.vpclub.sinotrans.sailor.admin.web;


import cn.vpclub.demo.common.model.core.enums.ReturnCodeEnum;
import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.sinotrans.sailor.admin.service.BringRecordService;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.BringRecord;
import cn.vpclub.sinotrans.sailor.feign.model.request.BringRecordRequest;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 房源带看记录表 前端控制器
 *
 * @author 南政
 * @since 2018-10-16
 */
@RestController
@RequestMapping("/bringRecord")
public class BringRecordController {

    @Resource
    private BringRecordService bringRecordService;


    /**
     * 带看记录-分页列表
     *
     * @param param
     * @return
     */
    @PostMapping(value = "/getList")
    public BaseResponse<List<BringRecord>> getList(@RequestBody BringRecordRequest param) {
        BaseResponse<List<BringRecord>> response = new BaseResponse<>();
        if (null == param.getSourceId()) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参房源主键sourceId为空");
            return response;
        }
        return bringRecordService.getList(param);
    }

    /**
     * 带看记录-新增带看
     *
     * @param bringRecord
     * @return
     */
    @PostMapping(value = "/save")
    public BaseResponse<Boolean> save(@RequestBody BringRecord bringRecord) {
        BaseResponse<Boolean> response = new BaseResponse<>();
        if (null == bringRecord.getSourceId()) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参房源主键sourceId为空");
            return response;
        }
        if (null == bringRecord.getBringId()) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参带看人主键bringId为空");
            return response;
        }
        if (StringUtils.isBlank(bringRecord.getBringName())) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参带看人姓名bringName为空");
            return response;
        }
        if (null == bringRecord.getBringTime()) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参带看时间bringTime为空");
            return response;
        }
        if (StringUtils.isBlank(bringRecord.getPassengerName())) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参客户姓名passengerName为空");
            return response;
        }
        if (StringUtils.isBlank(bringRecord.getPassengerPhone())) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参客户电话passengerPhone为空");
            return response;
        }
        return bringRecordService.save(bringRecord);
    }

}

