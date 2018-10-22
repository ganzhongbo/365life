package cn.vpclub.sinotrans.sailor.admin.service;

import cn.vpclub.demo.common.model.core.enums.ReturnCodeEnum;
import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.demo.common.model.core.model.response.PageResponse;
import cn.vpclub.demo.common.model.utils.common.JsonUtil;
import cn.vpclub.sinotrans.sailor.admin.client.UserServerClient;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.User;
import cn.vpclub.sinotrans.sailor.feign.model.request.UserDataRequest;
import cn.vpclub.sinotrans.sailor.feign.model.request.UserRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Created by chentao on 2018/6/6.
 */
@Service("userService")
@Slf4j
public class UserService extends  BaseServicce{
    @Autowired
    private UserServerClient userServerClient;

    public BaseResponse saveUser(User user){
        return userServerClient.saveUser(user);
    }

    public BaseResponse updateUser(User user){
        return userServerClient.updateUser(user);
    }
    public BaseResponse deleteUser(User user){
        return userServerClient.deleteUser(user);
    }
    public BaseResponse selectByUserId(User user) {
        return userServerClient.selectByUserId(user);
    }

    public PageResponse<User> userdatapage(@RequestBody UserRequest request){

        return userServerClient.userdatapage(request);
    }

    public BaseResponse login(User user){
        return userServerClient.login(user);
    }

    public BaseResponse reSetPassword(User user){
        return userServerClient.reSetPassword(user);
    }

    public PageResponse<User> selectUser(UserRequest request){
        return userServerClient.selectUser(request);
    }

    public BaseResponse getUserData(UserDataRequest userDataRequest){
        BaseResponse response = null;
        User user = JsonUtil.jsonToObject(getUser() ,User.class);
        if(user==null){
            response.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
            response.setMessage("获取用户信息失败");
            return response;
        }
        userDataRequest.setUserId(user.getUserId());
        userDataRequest.setUserCode(user.getUserCode());
        userDataRequest.setUserName(user.getUserName());
        userDataRequest.setRealName(user.getRealName());
        userDataRequest.setUserRole(user.getUserRole());
        return userServerClient.getUserData(userDataRequest);

    }

    public BaseResponse businessReport(UserDataRequest userDataRequest){

        BaseResponse response = null;
        User user = JsonUtil.jsonToObject(getUser() ,User.class);
        if(user==null){
            response.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
            response.setMessage("获取用户信息失败");
            return response;
        }
        userDataRequest.setUserId(user.getUserId());
        userDataRequest.setUserCode(user.getUserCode());
        userDataRequest.setUserName(user.getUserName());
        userDataRequest.setRealName(user.getRealName());
        userDataRequest.setUserRole(user.getUserRole());
        return userServerClient.businessReport(userDataRequest);
    }


}
