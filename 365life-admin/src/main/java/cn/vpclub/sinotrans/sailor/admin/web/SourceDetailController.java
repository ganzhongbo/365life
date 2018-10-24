package cn.vpclub.sinotrans.sailor.admin.web;


import cn.vpclub.demo.common.model.core.enums.ReturnCodeEnum;
import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.sinotrans.sailor.admin.service.SourceDetailService;
import cn.vpclub.sinotrans.sailor.feign.domain.constants.SourceDetailConstants;
import cn.vpclub.sinotrans.sailor.feign.domain.dto.SourceDetailDTO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 房源实勘详细信息表 前端控制器
 *
 * @author 南政
 * @since 2018-10-15
 */
@RestController
@RequestMapping("/sourceDetail")
public class SourceDetailController {

    @Resource
    private SourceDetailService sourceDetailService;

    /**
     * 房源信息-房源实勘
     *
     * @param sourceDetailDTO
     * @return
     */
    @PostMapping(value = "/saveOrUpdate")
    public BaseResponse<Boolean> saveOrUpdate(@RequestBody SourceDetailDTO sourceDetailDTO) {
        BaseResponse<Boolean> response = new BaseResponse<>();
        if (null == sourceDetailDTO.getSourceId()) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参房源信息主键sourceId为空");
            return response;
        }
        if (StringUtils.isBlank(sourceDetailDTO.getOrientation())) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参房屋朝向orientation为空");
            return response;
        }
        if (null == sourceDetailDTO.getDictId()) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参房屋户型主键dictId为空");
            return response;
        }
        if (null == sourceDetailDTO.getInnerStructure()) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参内部结构innerStructure为空");
            return response;
        }
        if (null == sourceDetailDTO.getDecoration()) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参装修情况decoration为空");
            return response;
        }
        if (null == sourceDetailDTO.getPropertyBelong()) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参物业权属propertyBelong为空");
            return response;
        }
        if (null == sourceDetailDTO.getPropertyUse()) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参物业用途propertyUse为空");
            return response;
        }
        if (null == sourceDetailDTO.getWarrant()) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参权证情况warrant为空");
            return response;
        }
        if (null == sourceDetailDTO.getLandStatus()) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参土地性质landStatus为空");
            return response;
        }
        if (null == sourceDetailDTO.getHouseAge()) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参房龄houseAge为空");
            return response;
        }
        if (StringUtils.isBlank(sourceDetailDTO.getServiceLife())) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参产权年限serviceLife为空");
            return response;
        }
        if (null == sourceDetailDTO.getBuildingStructure()) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参建筑结构buildingStructure为空");
            return response;
        }
        if (null == sourceDetailDTO.getCurrentFloor()) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参当前楼层currentFloor为空");
            return response;
        }
        if (null == sourceDetailDTO.getTotalFloor()) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参总楼层totalFloor为空");
            return response;
        }
        if (null == sourceDetailDTO.getUseCondition()) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参使用情况useCondition为空");
            return response;
        }
        if (StringUtils.isBlank(sourceDetailDTO.getDecorationTime())) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参装修时间decorationTime为空");
            return response;
        }
        if (null == sourceDetailDTO.getLoanCondition()) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参贷款loanCondition为空");
            return response;
        }
        if (StringUtils.isBlank(sourceDetailDTO.getPropertyName())) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参物业名称propertyName为空");
            return response;
        }
        if (null == sourceDetailDTO.getPropertyFee()) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参物业费propertyFee为空");
            return response;
        }
        if (StringUtils.isBlank(sourceDetailDTO.getGarageArea())) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参车库面积garageArea为空");
            return response;
        }
        if (null == sourceDetailDTO.getBuildingArea()) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参建筑面积buildingArea为空");
            return response;
        }
        if (null == sourceDetailDTO.getUsedArea()) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参使用面积usedArea为空");
            return response;
        }
        if (null == sourceDetailDTO.getPooledArea()) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参公摊面积pooledArea为空");
            return response;
        }
        if (StringUtils.isBlank(sourceDetailDTO.getSupportFacility())) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参配套设施supportFacility为空");
            return response;
        }
        if (null == sourceDetailDTO.getIsKey()) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参是否拿到钥匙为空");
            return response;
        }
        //如果是已经拿到钥匙
        if (sourceDetailDTO.getIsKey() == SourceDetailConstants.HAVE_TAKE && null == sourceDetailDTO.getTakeKeyId()) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参拿到钥匙人的主键takeKeyId为空");
            return response;
        }
        if (sourceDetailDTO.getIsKey() == SourceDetailConstants.HAVE_TAKE && StringUtils.isBlank(sourceDetailDTO.getTakeKeyName())) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参拿钥匙人名takeKeyName为空");
            return response;
        }
        if (sourceDetailDTO.getIsKey() == SourceDetailConstants.HAVE_TAKE && null == sourceDetailDTO.getTakeKeyTime()) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参拿钥匙的时间takeKeyTime为空");
            return response;
        }
        if (CollectionUtils.isEmpty(sourceDetailDTO.getHouseImg())) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参房屋图片houseImg为空");
            return response;
        }
        if (CollectionUtils.isEmpty(sourceDetailDTO.getPropertyDelivery())) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参物业交接propertyDelivery为空");
            return response;
        }
        return sourceDetailService.saveOrUpdate(sourceDetailDTO);

    }

}

