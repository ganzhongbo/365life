package cn.vpclub.sinotrans.sailor.server.service;

import cn.vpclub.sinotrans.sailor.feign.domain.entity.UserResouceEntity;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

public interface UserResouceServise extends IService<UserResouceEntity> {

    /***
     * 字典类新增方法
     * @return
     */
    boolean save(UserResouceEntity entity);



    /**
     * 查询详情
     * @return
     */
    List<UserResouceEntity> getByUserId(String userId);




}
