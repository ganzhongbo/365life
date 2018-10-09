package cn.vpclub.sinotrans.sailor.feign.feignClient;


import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.demo.common.model.core.model.response.PageResponse;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.User;
import cn.vpclub.sinotrans.sailor.feign.model.request.UserRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by chentao on 2018/6/6.
 */
@RequestMapping("/userclient")
public interface UserClient {

    @RequestMapping(value = "/saveUser",  method = RequestMethod.POST ,consumes = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse saveUser(@RequestBody User user);

    @RequestMapping(value = "/updateUser",  method = RequestMethod.POST ,consumes = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse updateUser(@RequestBody User user);

    @RequestMapping(value = "/deleteUser",  method = RequestMethod.POST ,consumes = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse deleteUser(@RequestBody User user);

    @RequestMapping(value = "/userdatapage",  method = RequestMethod.POST ,consumes = MediaType.APPLICATION_JSON_VALUE)
    public PageResponse<User> userdatapage(@RequestBody UserRequest request);

    @RequestMapping(value = "/selectByUserId",  method = RequestMethod.POST ,consumes = MediaType.APPLICATION_JSON_VALUE)
    public  BaseResponse selectByUserId(@RequestBody User user);

    @RequestMapping(value = "/login",  method = RequestMethod.POST ,consumes = MediaType.APPLICATION_JSON_VALUE)
    public  BaseResponse login(@RequestBody User user);


    @RequestMapping(value = "/reSetPassword",  method = RequestMethod.POST ,consumes = MediaType.APPLICATION_JSON_VALUE)
    public  BaseResponse reSetPassword(@RequestBody User user);

}
