package cn.vpclub.sinotrans.sailor.feign.domain.entity;



import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

/**
 * <p>
 * 短信发送记录日志表
 * </p>
 *
 * @author 明冲
 * @since 2018-09-19
 */
@Data
@TableName("sms_msg_log")
public class SmsMsgLogEntity {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 短信类型
     */
    private Integer type;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 短信验证码
     */
    private String smsCode;

    /**
     * 短信返回json
     */
    private String smsResponse;

    private Long createdTime;
    private Long updatedTime;
    private Integer deleted;
}
