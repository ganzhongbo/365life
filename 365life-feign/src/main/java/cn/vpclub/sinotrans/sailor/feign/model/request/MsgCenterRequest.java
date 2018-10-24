package cn.vpclub.sinotrans.sailor.feign.model.request;

import cn.vpclub.demo.common.model.core.model.request.PageBaseSearchParam;
import lombok.Data;

/**
 * @author 南政
 * @className MsgCenterRequest
 * @desc 消息中心请求类
 * @since 2018/10/22 11:45
 */
@Data
public class MsgCenterRequest extends PageBaseSearchParam {

    /**
     * 如果消息所属为私有，那么此字段不为空
     */
    private Long receiverId;
}
