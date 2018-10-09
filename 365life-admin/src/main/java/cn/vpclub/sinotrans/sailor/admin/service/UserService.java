package cn.vpclub.sinotrans.sailor.admin.service;

import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.demo.common.model.core.model.response.PageResponse;
import cn.vpclub.sinotrans.sailor.admin.client.UserServerClient;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.User;
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
public class UserService {
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
}
