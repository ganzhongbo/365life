package cn.vpclub.sinotrans.sailor.server.dao;


import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.User;
import cn.vpclub.sinotrans.sailor.feign.model.request.UserDataRequest;
import cn.vpclub.sinotrans.sailor.feign.model.request.UserRequest;
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

    //根据用户名查询用户
    public User selectByUserName(User user);

    //重置密码
    public boolean updatePassword(User user);
    //绑定经济人和选择上级,查询用户
    public BaseResponse selectUser();

    //获取的租房或者卖房的客源数量
    public Integer getRentingOrTenantCount(UserDataRequest request);
    //获取的租房或者卖房的房源数量
    public Integer getRentingOrTenantHouseCount(UserDataRequest request);

    public Integer getAddHoseCount(UserDataRequest request);
    public Integer getRealExplorationCount(UserDataRequest request);
    public Integer getTakeLookCount(UserDataRequest request);
    public Integer getAddGuestsCount(UserDataRequest request);
    public Integer getReceiveGuestsCount(UserDataRequest request);






}
