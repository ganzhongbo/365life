package cn.vpclub.sinotrans.sailor.server.dao;


import cn.vpclub.sinotrans.sailor.feign.domain.entity.User;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * Created by chentao on 2018/6/6.
 */
public interface UserDao extends BaseMapper<User> {

    //批量删除用户关联的资源
    public boolean batchDelete(User user);

    //根据账号密码查询用户
    public User selectByUser(User user);

    //根据电话查询用户
    public User selectByTell(User user);


}
