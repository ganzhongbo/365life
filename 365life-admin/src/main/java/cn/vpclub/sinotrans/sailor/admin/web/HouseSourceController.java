package cn.vpclub.sinotrans.sailor.admin.web;


import cn.vpclub.demo.common.model.core.enums.ReturnCodeEnum;
import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.sinotrans.sailor.admin.service.HouseSourceService;
import cn.vpclub.sinotrans.sailor.feign.domain.constants.MsgCenterConstant;
import cn.vpclub.sinotrans.sailor.feign.domain.dto.HouseSourceDTO;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.HouseSource;
import cn.vpclub.sinotrans.sailor.feign.model.request.HouseSourceRequest;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 房源基本信息表 前端控制器
 *
 * @author 南政
 * @since 2018-10-15
 */
@RestController
@RequestMapping("/houseSource")
public class HouseSourceController {


    @Resource
    private HouseSourceService houseSourceService;


    /**
     * 房源信息-分页列表
     *
     * @param param
     * @return
     */
    @PostMapping(value = "/getPage")
    public BaseResponse<Page<HouseSourceDTO>> getPage(@RequestBody HouseSourceRequest param) {
        return houseSourceService.getPage(param);
    }

    /**
     * 房源信息-新增修改
     *
     * @param houseSourceDTO
     * @return
     */
    @PostMapping(value = "/saveOrUpdate")
    public BaseResponse<Boolean> saveOrUpdate(@RequestBody HouseSourceDTO houseSourceDTO) {
        BaseResponse<Boolean> response = new BaseResponse<>();
        if (null == houseSourceDTO.getBusinessType()) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参业务类型businessType为空");
            return response;
        }
        if (null == houseSourceDTO.getTradeAreaId()) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参商圈主键tradeAreaId为空");
            return response;
        }
        if (StringUtils.isBlank(houseSourceDTO.getTradeAreaName())) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参商圈名字tradeAreaName为空");
            return response;
        }
        if (StringUtils.isBlank(houseSourceDTO.getCommunityName())) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参小区名称communityName为空");
            return response;
        }
        if (StringUtils.isBlank(houseSourceDTO.getOwnerPhone())) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参业主电话ownerPhone为空");
            return response;
        }
        if (StringUtils.isBlank(houseSourceDTO.getDetailAddress())) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参具体地址detailAddress为空");
            return response;
        }
        if (StringUtils.isBlank(houseSourceDTO.getBuildingNumber())) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参楼栋号buildingNumber为空");
            return response;
        }
        if (StringUtils.isBlank(houseSourceDTO.getUnitNumber())) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参单元号unitNumber为空");
            return response;
        }
        if (StringUtils.isBlank(houseSourceDTO.getRoomNumber())) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参门室号roomNumber为空");
            return response;
        }
        if (StringUtils.isBlank(houseSourceDTO.getModelName())) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参户型名称modelName为空");
            return response;
        }
        if (StringUtils.isBlank(houseSourceDTO.getHouseModel())) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参房型houseModel为空");
            return response;
        }
        if (null == houseSourceDTO.getHouseType()) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参房屋类型houseType为空");
            return response;
        }
        if (StringUtils.isBlank(houseSourceDTO.getAcreage())) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参出租/产权面积acreage为空");
            return response;
        }
        if (null == houseSourceDTO.getExpectPrice()) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参期望售价expectPrice为空");
            return response;
        }
        return houseSourceService.saveOrUpdate(houseSourceDTO);
    }

    /**
     * 房源信息-获取详情
     *
     * @param houseSource
     * @return
     */
    @PostMapping(value = "/getDetail")
    public BaseResponse<HouseSourceDTO> getDetail(@RequestBody HouseSource houseSource) {
        BaseResponse<HouseSourceDTO> response = new BaseResponse<>();
        if (null == houseSource.getId()) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("房源信息主键id为空");
            return response;
        }
        return houseSourceService.getDetail(houseSource);
    }

    /**
     * 房源信息-房源抢单/放弃房源
     *
     * @param param
     * @return
     */
    @PostMapping(value = "/grabSource")
    public BaseResponse<Boolean> grabSource(@RequestBody HouseSourceRequest param) {
        BaseResponse<Boolean> response = new BaseResponse<>();
        if (null == param.getId()) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参房源主键id为空");
            return response;
        }
        if (null == param.getIsGrab()) {
            response.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
            response.setMessage("入参是否抢单isGrab为空");
            return response;
        }
        if (param.getIsGrab() == MsgCenterConstant.NOT_GRAB && null == param.getMsgId()) {
            response.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
            response.setMessage("入参如果为不抢，那么消息id不能为空");
            return response;
        }
        return houseSourceService.grabSource(param);
    }

}

