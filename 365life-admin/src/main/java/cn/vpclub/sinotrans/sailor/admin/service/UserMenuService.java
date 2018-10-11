package cn.vpclub.sinotrans.sailor.admin.service;

import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.sinotrans.sailor.admin.client.UserMenuServerClient;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.UserMenuEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userMenuService")
@Slf4j
public class UserMenuService {

    @Autowired
    private UserMenuServerClient userMenuServerClient;
    /**
     * 保存
     */
    public BaseResponse saveUserMenu(UserMenuEntity userMenuEntity){
        return userMenuServerClient.saveUserMenu(userMenuEntity);
    }

    /**
     * 修改
     */
    public  BaseResponse  updateUserMenu(UserMenuEntity userMenuEntity){
        return userMenuServerClient.updateUserMenu(userMenuEntity);
    }

    /**
     * 删除
     */
    public  BaseResponse  deleteUserMenu(UserMenuEntity userMenuEntity){
        return userMenuServerClient.deleteUserMenu(userMenuEntity);
    }

    /**
     * 查询所有的菜单
     * 当parentId为空时就是查所有菜单
     * 否则就是这个父级(parentId)的子菜单
     * @param userMenuEntity
     * @return
     */
    public BaseResponse<List<UserMenuEntity>> selectAllUserMenu(UserMenuEntity userMenuEntity){
        return userMenuServerClient.selectAllUserMenu(userMenuEntity);
    }
    /**
     * 根据角色查询菜单
     * 当parentId为空时就是查询该角色的所有菜单
     * 否则就是这个角色 以及此父级(parentId)的子菜单
     * @param userMenuEntity
     * @return
     */
    public BaseResponse<List<UserMenuEntity>> selectUserMenuByRole(UserMenuEntity userMenuEntity){
        return userMenuServerClient.selectUserMenuByRole(userMenuEntity);
    }


    /**
     * 给角色配置菜单
     * @param userMenuEntity
     * @return
     */
    public BaseResponse setUserMenu(UserMenuEntity userMenuEntity){
        return userMenuServerClient.setUserMenu(userMenuEntity);
    }





}
