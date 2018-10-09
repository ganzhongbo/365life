package cn.vpclub.sinotrans.sailor.admin.web;

import cn.vpclub.demo.common.model.core.model.response.BaseResponse;

import cn.vpclub.demo.common.model.core.model.response.PageResponse;
import cn.vpclub.sinotrans.sailor.admin.service.UserService;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.User;
import cn.vpclub.sinotrans.sailor.feign.model.request.UserRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * Created by zhanchenggui on 2018/7/13
 */
@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /***
     * 保存用户信息
     */
    @PostMapping(value = "/saveUser")
    public BaseResponse saveUser(@RequestBody User userInfo){
        return userService.saveUser(userInfo);
    }

    /***
     * 保存用户信息
     */
    @PostMapping(value = "/updateUser")
    public BaseResponse updateUser(@RequestBody User userInfo){
        return userService.updateUser(userInfo);
    }

    /***
     * 保存用户信息
     */
    @PostMapping(value = "/deleteUser")
    public BaseResponse deleteUser(@RequestBody User userInfo){
        return userService.deleteUser(userInfo);
    }

    /**
     *  根据id查询数据
     * @param userInfo
     * @return
     */
    @PostMapping(value = "/getUserById")
    public BaseResponse getUserById(@RequestBody User userInfo){
        return  userService.selectByUserId(userInfo);
    }

    /***
     * 用户数据分页查询
     * @param
     * @return
     */
    @PostMapping(value = "/userdatapage")
    public PageResponse<User> userdatapage(@RequestBody UserRequest request){

        return userService.userdatapage(request);
    }

    /***
     * 登录
     */
    @PostMapping(value = "/login")
    public BaseResponse login(@RequestBody User userInfo){

        return  userService.login(userInfo);
    }

    /**
     * 重置密码
     * @param user
     * @return
     */
    @PostMapping(value = "/reSetPassword")
    public BaseResponse reSetPassword(@RequestBody User user){

        return  userService.reSetPassword(user);
    }
}
