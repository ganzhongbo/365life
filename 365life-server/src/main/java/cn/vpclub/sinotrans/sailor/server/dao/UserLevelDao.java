package cn.vpclub.sinotrans.sailor.server.dao;


import cn.vpclub.sinotrans.sailor.feign.domain.entity.UserLevelEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;


/**
 * Created by chentao on 2018/6/6.
 */
public interface UserLevelDao extends BaseMapper<UserLevelEntity> {

    /**
     * 只有一种配置
     * @return
     */
    public UserLevelEntity getUserLevel();

}
