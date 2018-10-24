package cn.vpclub.sinotrans.sailor.admin.service;

import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.sinotrans.sailor.admin.client.UserLevelServerClient;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.UserLevelEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("userLevelService")
@Slf4j
public class UserLevelService {

    @Autowired
    private UserLevelServerClient userLevelServerClient;
    /**
     * 保存
     */
    public BaseResponse saveUserLevel(UserLevelEntity userLevelEntity){
        return userLevelServerClient.saveUserLevel(userLevelEntity);
    }

    /**
     * 修改
     */
    public  BaseResponse  updateUserLevel(UserLevelEntity userLevelEntity){
        return userLevelServerClient.updateUserLevel(userLevelEntity);
    }

    /**
     * 查询
     */
    public  BaseResponse  getUserLevel(){
        return userLevelServerClient.getUserLevel();
    }

}
