package cn.vpclub.sinotrans.sailor.admin.service;

import cn.vpclub.sinotrans.sailor.admin.client.UserServerClient;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by chentao on 2018/6/6.
 */
@Service("userService")
@Slf4j
public class UserService {
    @Autowired
    private UserServerClient userServerClient;

    public User selectByUserId(Long userId) {
        return userServerClient.selectByUserId(userId);
    }
}
