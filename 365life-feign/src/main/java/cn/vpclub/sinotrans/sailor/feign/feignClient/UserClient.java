package cn.vpclub.sinotrans.sailor.feign.feignClient;


import cn.vpclub.sinotrans.sailor.feign.domain.entity.User;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by chentao on 2018/6/6.
 */
@RequestMapping("/userclient")
public interface UserClient {

    @RequestMapping(value = "/selectByUserId",  method = RequestMethod.POST ,consumes = MediaType.APPLICATION_JSON_VALUE)
    User selectByUserId(@RequestBody User user);

}
