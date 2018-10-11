package cn.vpclub.sinotrans.sailor.server.client;

import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.UserMenuEntity;
import cn.vpclub.sinotrans.sailor.feign.feignClient.UserMenuClient;
import cn.vpclub.sinotrans.sailor.server.service.UserMenuServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class UserMenuClientImpl implements UserMenuClient{

    @Autowired
    private UserMenuServerService userMenuServerService;

    @Override
    public BaseResponse saveUserMenu(@RequestBody UserMenuEntity userMenuEntity) {
        return userMenuServerService.saveUserMenu(userMenuEntity);
    }

    @Override
    public BaseResponse updateUserMenu(@RequestBody UserMenuEntity userMenuEntity) {
        return userMenuServerService.updateUserMenu(userMenuEntity);
    }

    @Override
    public BaseResponse deleteUserMenu(@RequestBody UserMenuEntity userMenuEntity) {
        return userMenuServerService.deleteUserMenu(userMenuEntity);
    }

    @Override
    public BaseResponse<List<UserMenuEntity>> selectAllUserMenu(@RequestBody UserMenuEntity userMenuEntity) {
        return userMenuServerService.selectAllUserMenu(userMenuEntity);
    }

    @Override
    public BaseResponse<List<UserMenuEntity>> selectUserMenuByRole(@RequestBody UserMenuEntity userMenuEntity) {
        return userMenuServerService.selectUserMenuByRole(userMenuEntity);
    }

    @Override
    public BaseResponse setUserMenu(@RequestBody UserMenuEntity userMenuEntity) {
        return userMenuServerService.setUserMenu(userMenuEntity);
    }
}
