package cn.vpclub.sinotrans.sailor.admin.web;


import cn.vpclub.demo.common.model.core.enums.ReturnCodeEnum;
import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.sinotrans.sailor.admin.service.HouseSourceService;
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
     * @param houseSource
     * @return
     */
    @PostMapping(value = "/saveOrUpdate")
    public BaseResponse<Boolean> saveOrUpdate(@RequestBody HouseSource houseSource) {
        BaseResponse<Boolean> response = new BaseResponse<>();
        if (null == houseSource.getBusinessType()) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参业务类型businessType为空");
            return response;
        }
        if (null == houseSource.getTradeAreaId()) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参商圈主键tradeAreaId为空");
            return response;
        }
        if (StringUtils.isBlank(houseSource.getTradeAreaName())) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参商圈名字tradeAreaName为空");
            return response;
        }
        if (StringUtils.isBlank(houseSource.getCommunityName())) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参小区名称communityName为空");
            return response;
        }
        if (StringUtils.isBlank(houseSource.getOwnerPhone())) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参业主电话ownerPhone为空");
            return response;
        }
        if (StringUtils.isBlank(houseSource.getDetailAddress())) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参具体地址detailAddress为空");
            return response;
        }
        if (StringUtils.isBlank(houseSource.getBuildingNumber())) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参楼栋号buildingNumber为空");
            return response;
        }
        if (StringUtils.isBlank(houseSource.getUnitNumber())) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参单元号buildingNumber为空");
            return response;
        }
        if (StringUtils.isBlank(houseSource.getRoomNumber())) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参门室号roomNumber为空");
            return response;
        }
        if (StringUtils.isBlank(houseSource.getModelName())) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参户型名称modelName为空");
            return response;
        }
        if (StringUtils.isBlank(houseSource.getHouseModel())) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参房型houseModel为空");
            return response;
        }
        if (null == houseSource.getHouseType()) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参房屋类型houseType为空");
            return response;
        }
        if (StringUtils.isBlank(houseSource.getAcreage())) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参出租/产权面积acreage为空");
            return response;
        }
        if (null == houseSource.getExpectPrice()) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参期望售价expectPrice为空");
            return response;
        }
        if (null == houseSource.getCreatedBy()) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("用户主键createdBy为空");
            return response;
        }
        return houseSourceService.saveOrUpdate(houseSource);
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


}

