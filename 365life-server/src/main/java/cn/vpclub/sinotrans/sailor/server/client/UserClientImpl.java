package cn.vpclub.sinotrans.sailor.server.client;



import cn.vpclub.sinotrans.sailor.feign.domain.entity.User;
import cn.vpclub.sinotrans.sailor.feign.feignClient.UserClient;
import cn.vpclub.sinotrans.sailor.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public User selectByUserId(@RequestParam("userId") Long userId) {
        User userResponse = userService.selectById(userId);
        return userResponse;
    }



}
