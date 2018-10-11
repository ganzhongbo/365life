package cn.vpclub.sinotrans.sailor.admin.web;

import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.sinotrans.sailor.admin.service.UserMenuService;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.UserMenuEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/userMenu")
public class UserMenuController {

    @Autowired
    private UserMenuService userMenuService;

    /**
     * 保存
     */
    @RequestMapping(value = "/saveUserMenu")
    public BaseResponse saveUserMenu(@RequestBody UserMenuEntity userMenuEntity){
       return  userMenuService.saveUserMenu(userMenuEntity);
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/updateUserMenu")
    public  BaseResponse  updateUserMenu(@RequestBody UserMenuEntity userMenuEntity){
        return  userMenuService.updateUserMenu(userMenuEntity);
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/deleteUserMenu")
    public  BaseResponse  deleteUserMenu(@RequestBody UserMenuEntity userMenuEntity){
        return  userMenuService.deleteUserMenu(userMenuEntity);
    }

    /**
     * 查询所有的菜单
     * 当parentId为空时就是查所有菜单
     * 否则就是这个父级(parentId)的子菜单
     * @param userMenuEntity
     * @return
     */
    @RequestMapping(value = "/selectAllUserMenu")
    public BaseResponse<List<UserMenuEntity>> selectAllUserMenu(@RequestBody UserMenuEntity userMenuEntity){
        return  userMenuService.selectAllUserMenu(userMenuEntity);
    }
    /**
     * 根据角色查询菜单
     * 当parentId为空时就是查询该角色的所有菜单
     * 否则就是这个角色 以及此父级(parentId)的子菜单
     * @param userMenuEntity
     * @return
     */
    @RequestMapping(value = "/selectUserMenuByRole")
    public BaseResponse<List<UserMenuEntity>> selectUserMenuByRole(@RequestBody UserMenuEntity userMenuEntity){
        return  userMenuService.selectUserMenuByRole(userMenuEntity);
    }
    /**
     * 给角色配置菜单
     * @param userMenuEntity
     * @return
     */
    @RequestMapping(value = "/setUserMenu")
    public BaseResponse setUserMenu(@RequestBody UserMenuEntity userMenuEntity){
        return  userMenuService.setUserMenu(userMenuEntity);
    }





}
