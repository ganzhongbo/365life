package cn.vpclub.sinotrans.sailor.admin.web;


import cn.vpclub.demo.common.model.core.enums.ReturnCodeEnum;
import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.sinotrans.sailor.admin.service.PassengerSourceService;
import cn.vpclub.sinotrans.sailor.feign.domain.dto.PassengerSourceDTO;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.PassengerSource;
import cn.vpclub.sinotrans.sailor.feign.model.request.PassengerSourceRequest;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 南政
 * @fileName PassengerSourceController.java
 * @desc 客源信息记录表 前端控制器
 * @since 2018/10/10 11:05
 */
@RestController
@RequestMapping("/passengerSource")
public class PassengerSourceController {

    @Resource
    private PassengerSourceService passengerSourceService;

    /**
     * 客源信息-分页列表
     *
     * @param param
     * @return
     */
    @PostMapping(value = "/getPage")
    public BaseResponse<Page<PassengerSource>> getPage(@RequestBody PassengerSourceRequest param) {
        return passengerSourceService.getPage(param);
    }

    /**
     * 客源信息-新增或者修改
     *
     * @param entity
     * @return
     */
    @PostMapping(value = "/saveOrUpdate")
    public BaseResponse<Boolean> saveOrUpdate(@RequestBody PassengerSource entity) {
        BaseResponse<Boolean> response = new BaseResponse<>();
        if (StringUtils.isBlank(entity.getPassengerName())) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参客户姓名passengerName为空");
            return response;
        }
        if (null == entity.getPassengerSex()) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参客户性别passengerSex为空");
            return response;
        }
        if (StringUtils.isBlank(entity.getPassengerPhone())) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参客户电话passengerPhone为空");
            return response;

        }
        if (null == entity.getRequireType()) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参需求类型requireType为空");
            return response;

        }
        if (StringUtils.isBlank(entity.getRequireLocation())) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参需求地段requireLocation为空");
            return response;

        }
        if (StringUtils.isBlank(entity.getRequireModel())) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参需求户型requireModel为空");
            return response;

        }
        if (null == entity.getRequireBudget()) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参需求预算requireBudget为空");
            return response;

        }
        if (StringUtils.isBlank(entity.getPreferenceDesc())) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参偏好描述preferenceDesc为空");
            return response;

        }
        return passengerSourceService.saveOrUpdate(entity);
    }

    /**
     * 客源信息-获取详情
     *
     * @param entity
     * @return
     */
    @PostMapping(value = "/getDetail")
    public BaseResponse<PassengerSourceDTO> getDetail(@RequestBody PassengerSource entity) {
        BaseResponse<PassengerSourceDTO> response = new BaseResponse<>();
        if (null == entity.getId()) {
            response.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
            response.setMessage("入参id为空");
            return response;
        }
        return passengerSourceService.getDetail(entity);
    }


    /**
     * 个人中心-我的客源
     *
     * @return
     */
    @PostMapping(value = "/getMyPassenger")
    public BaseResponse<Page<PassengerSource>> getMyPassenger() {
        return passengerSourceService.getMyPassenger();
    }

}

