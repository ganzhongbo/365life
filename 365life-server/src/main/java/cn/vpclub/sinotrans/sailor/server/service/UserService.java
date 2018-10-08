package cn.vpclub.sinotrans.sailor.server.service;

import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.demo.common.model.core.model.response.PageResponse;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.User;
import cn.vpclub.sinotrans.sailor.feign.model.request.UserRequest;
import com.baomidou.mybatisplus.service.IService;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * Created by chentao on 2018/6/6.
 */
public interface UserService extends IService<User> {

    public BaseResponse saveUser( User user);

    public BaseResponse updateUser(User user);

    public BaseResponse deleteUser( User user);

    public PageResponse<User> userdatapage( UserRequest request);

    public  BaseResponse selectByUserId(User user);

    public BaseResponse login(User user);


}
