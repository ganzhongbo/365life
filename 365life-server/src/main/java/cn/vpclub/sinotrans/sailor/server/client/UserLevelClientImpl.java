package cn.vpclub.sinotrans.sailor.server.client;

import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.UserLevelEntity;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.UserMenuEntity;
import cn.vpclub.sinotrans.sailor.feign.feignClient.UserLevelClient;
import cn.vpclub.sinotrans.sailor.feign.feignClient.UserMenuClient;
import cn.vpclub.sinotrans.sailor.server.service.UserLevelServise;
import cn.vpclub.sinotrans.sailor.server.service.UserMenuServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserLevelClientImpl implements UserLevelClient{

    @Autowired
    private UserLevelServise userLevelServise;

    @Override
    public BaseResponse saveUserLevel(@RequestBody UserLevelEntity userLevelEntity) {
        return userLevelServise.saveUserLevel(userLevelEntity);
    }

    @Override
    public BaseResponse updateUserLevel(@RequestBody UserLevelEntity userLevelEntity) {
        return userLevelServise.updateUserLevel(userLevelEntity);
    }

    @Override
    public BaseResponse getUserLevel() {
        return userLevelServise.getUserLevel();
    }

}
