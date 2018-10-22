package cn.vpclub.sinotrans.sailor.feign.model.request;

import cn.vpclub.demo.common.model.core.model.request.PageBaseSearchParam;
import lombok.Data;

import java.util.List;

/**
 * Created by chentao on 2018/6/6.
 */
@Data
public class UserRequest extends PageBaseSearchParam{

    /**
     * 用户名
     */
    private String userName;

    private Integer postLevel;

    private String  realName;

    private String userCode;
    /**
     * 手机号
     */
    private String tellPhone;
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
    private String userId;

    /**
     * 新密码
     */
    private String newPassword;

    private List<Long> idList;

    private  Integer type;//绑定经济人和选择上级的type去分


}
