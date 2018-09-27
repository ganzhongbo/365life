package cn.vpclub.sinotrans.sailor.feign.feignClient;


import cn.vpclub.sinotrans.sailor.feign.domain.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by chentao on 2018/6/6.
 */
@RequestMapping("/userclient")
public interface UserClient {

    @RequestMapping(value = "/selectByUserId")
    User selectByUserId(@RequestParam("userId") Long userId);

}
