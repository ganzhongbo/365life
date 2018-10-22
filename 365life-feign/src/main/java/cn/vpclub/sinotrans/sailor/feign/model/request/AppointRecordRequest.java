package cn.vpclub.sinotrans.sailor.feign.model.request;

import cn.vpclub.demo.common.model.core.model.request.PageBaseSearchParam;
import lombok.Data;

/**
 * @author 南政
 * @className AppointRecordRequest
 * @desc
 * @since 2018/10/16 15:18
 */
@Data
public class AppointRecordRequest extends PageBaseSearchParam {
    /**
     * 带看记录的房源id
     */
    private Long sourceId;
}
