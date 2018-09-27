package cn.vpclub.sinotrans.sailor.server.service.impl;

import cn.vpclub.sinotrans.sailor.feign.domain.entity.User;
import cn.vpclub.sinotrans.sailor.server.dao.UserDao;
import cn.vpclub.sinotrans.sailor.server.service.UserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * Created by chentao on 2018/7/19.
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {
}
