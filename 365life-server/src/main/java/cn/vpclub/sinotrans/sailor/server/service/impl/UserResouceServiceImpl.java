package cn.vpclub.sinotrans.sailor.server.service.impl;

import cn.vpclub.sinotrans.sailor.feign.domain.entity.UserResouceEntity;
import cn.vpclub.sinotrans.sailor.server.dao.UserResouceDao;
import cn.vpclub.sinotrans.sailor.server.service.UserResouceServise;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chentao on 2018/7/19.
 */
@Service
@Slf4j
public class UserResouceServiceImpl extends ServiceImpl<UserResouceDao, UserResouceEntity> implements UserResouceServise {


    @Override
    public boolean save(UserResouceEntity entity) {
        log.info("用户资源保存接口请求数据 {} :",entity.toString());
        return this.insert(entity);
    }

    @Override
    public List<UserResouceEntity> getByUserId(String userId) {
        log.info("根据用户主键查询关联的资源请求数据 {} :",userId.toString());
        List<UserResouceEntity>  userResouceEntityList = new ArrayList<>();
        EntityWrapper<UserResouceEntity> ew = new EntityWrapper<>();
        ew.eq("user_id", userId);
        userResouceEntityList = baseMapper.selectList(ew);
        return userResouceEntityList;
    }


}
