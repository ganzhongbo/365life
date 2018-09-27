package cn.vpclub.sinotrans.sailor.feign.model.request;

import lombok.Data;

import java.util.List;

/**
 * Created by chentao on 2018/6/6.
 */
@Data
public class UserRequest {
    /**
     * 用户名
     */
    private String userName;
    /**
     * 身份证号
     */
    private String certificateNo;
    /**
     * 职务
     */
    private String position;
    /**
     * 手机号
     */
    private String mobile;
    /**
     *  密码
     */
    private String password;
    /**
     * 确认密码
     */
    private String okeyPassword;
    /**
     *  用户id
     */
    private Long userId;

    /**
     * 新密码
     */
    private String newPassword;

    private List<Long> idList;

}
