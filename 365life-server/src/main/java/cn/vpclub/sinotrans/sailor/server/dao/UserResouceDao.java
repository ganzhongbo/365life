package cn.vpclub.sinotrans.sailor.server.dao;


import cn.vpclub.sinotrans.sailor.feign.domain.entity.UserResouceEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * Created by chentao on 2018/6/6.
 */
public interface UserResouceDao extends BaseMapper<UserResouceEntity> {

    List<String> selectAll(UserResouceEntity userResouceEntity);
}
