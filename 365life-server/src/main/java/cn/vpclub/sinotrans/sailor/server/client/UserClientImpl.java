package cn.vpclub.sinotrans.sailor.server.client;



import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.demo.common.model.core.model.response.PageResponse;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.User;
import cn.vpclub.sinotrans.sailor.feign.feignClient.UserClient;
import cn.vpclub.sinotrans.sailor.feign.model.request.UserRequest;
import cn.vpclub.sinotrans.sailor.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chentao on 2018/6/6.
 */
@RestController
public class UserClientImpl implements UserClient {

    @Autowired
    private UserService userService;


    @Override
    public BaseResponse saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @Override
    public BaseResponse updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @Override
    public BaseResponse deleteUser(@RequestBody User user) {
        return userService.deleteUser(user);
    }

    @Override
    public PageResponse<User> userdatapage(@RequestBody UserRequest request) {
        return userService.userdatapage(request);
    }

    @Override
    public BaseResponse selectByUserId(@RequestBody User user) {
        return userService.selectByUserId(user);
    }

    @Override
    public BaseResponse login(@RequestBody User user) {



        return null;
    }


}
