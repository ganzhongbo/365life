package cn.vpclub.sinotrans.sailor.server.service;

import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.UserMenuEntity;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

public interface UserMenuServerService extends IService<UserMenuEntity> {
    /**
     * 保存
     */
    public  BaseResponse  saveUserMenu(UserMenuEntity userMenuEntity);

    /**
     * 修改
     */
    public  BaseResponse  updateUserMenu(UserMenuEntity userMenuEntity);

    /**
     * 删除
     */
    public  BaseResponse  deleteUserMenu(UserMenuEntity roleMenuEntity);

    /**
     * 查询所有的菜单
     * 当parentId为空时就是查所有菜单
     * 否则就是这个父级(parentId)的子菜单
     * @param userMenuEntity
     * @return
     */
    public BaseResponse<List<UserMenuEntity>> selectAllUserMenu(UserMenuEntity userMenuEntity);
    /**
     * 根据角色查询菜单
     * 当parentId为空时就是查询该角色的所有菜单
     * 否则就是这个角色 以及此父级(parentId)的子菜单
     * @param userMenuEntity
     * @return
     */
    public BaseResponse<List<UserMenuEntity>> selectUserMenuByRole(UserMenuEntity userMenuEntity);


    /**
     * 给角色配置菜单
     * @param roleMenuEntity
     * @return
     */
    public BaseResponse setUserMenu(UserMenuEntity roleMenuEntity);


}
