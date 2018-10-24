package cn.vpclub.sinotrans.sailor.server.service;

import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.UserLevelEntity;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

public interface UserLevelServise extends IService<UserLevelEntity> {

    /***
     * 字典类新增方法
     * @return
     */
    public BaseResponse saveUserLevel(UserLevelEntity entity);


    /***
     * 字典类新增方法
     * @return
     */
    public  BaseResponse updateUserLevel(UserLevelEntity entity);

    /**
     * 查询详情
     * @return
     */
    public  BaseResponse  getUserLevel();




}
