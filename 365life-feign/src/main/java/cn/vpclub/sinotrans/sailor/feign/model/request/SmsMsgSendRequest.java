package cn.vpclub.sinotrans.sailor.feign.model.request;

import lombok.Data;

/**
 * 短信发送入参
 *
 * @author
 * @date 2018/9/19
 */
@Data
public class SmsMsgSendRequest {
    /**
     * 电话号码
     */
    private String mobile;

    /**
     * 发送短信类型
     */
    private Integer type;
}
