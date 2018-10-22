package cn.vpclub.sinotrans.sailor.feign.model.request;

import cn.vpclub.demo.common.model.core.model.request.PageBaseSearchParam;
import lombok.Data;

/**
 * @author 南政
 * @className PassengerSourceRequest
 * @desc 跟进记录请求实体类
 * @since 2018/10/10 11:30
 */
@Data
public class FollowRecordRequest extends PageBaseSearchParam {

    /**
     * 房源/客源id
     */
    private Long sourceId;

    /**
     * 跟进记录的类型（1、房源，2、客源）
     */
    private Integer followType;


}
