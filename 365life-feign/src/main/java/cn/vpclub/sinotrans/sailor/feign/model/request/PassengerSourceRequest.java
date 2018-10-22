package cn.vpclub.sinotrans.sailor.feign.model.request;

import cn.vpclub.demo.common.model.core.model.request.PageBaseSearchParam;
import lombok.Data;

/**
 * @author 南政
 * @className PassengerSourceRequest
 * @desc 客源信息请求实体类
 * @since 2018/10/10 11:30
 */
@Data
public class PassengerSourceRequest extends PageBaseSearchParam {

    /**
     * 需求类型，默认为1（1、租房，2、买房）
     */
    private Integer requireType;

    /**
     * 最低需求预算，单位分
     */
    private Long minRequireBudget;

    /**
     * 最低需求预算，单位分
     */
    private Long maxRequireBudget;

    /**
     * 需求地段
     */
    private String requireLocation;

}
