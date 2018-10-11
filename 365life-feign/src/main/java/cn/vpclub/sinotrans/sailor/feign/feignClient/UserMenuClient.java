package cn.vpclub.sinotrans.sailor.feign.feignClient;

import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.UserMenuEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
@RequestMapping("/userMenuClient")
public interface UserMenuClient {


    /**
     * 保存
     */
    @RequestMapping(value = "/saveUserMenu",  method = RequestMethod.POST ,consumes = MediaType.APPLICATION_JSON_VALUE)
    public  BaseResponse  saveUserMenu(@RequestBody UserMenuEntity userMenuEntity);

    /**
     * 修改
     */
    @RequestMapping(value = "/updateUserMenu",  method = RequestMethod.POST ,consumes = MediaType.APPLICATION_JSON_VALUE)
    public  BaseResponse  updateUserMenu(@RequestBody UserMenuEntity userMenuEntity);

    /**
     * 删除
     */
    @RequestMapping(value = "/deleteUserMenu",  method = RequestMethod.POST ,consumes = MediaType.APPLICATION_JSON_VALUE)
    public  BaseResponse  deleteUserMenu(@RequestBody UserMenuEntity roleMenuEntity);

    /**
     * 查询所有的菜单
     * 当parentId为空时就是查所有菜单
     * 否则就是这个父级(parentId)的子菜单
     * @param userMenuEntity
     * @return
     */
    @RequestMapping(value = "/selectAllUserMenu",  method = RequestMethod.POST ,consumes = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<List<UserMenuEntity>> selectAllUserMenu(@RequestBody UserMenuEntity userMenuEntity);
    /**
     * 根据角色查询菜单
     * 当parentId为空时就是查询该角色的所有菜单
     * 否则就是这个角色 以及此父级(parentId)的子菜单
     * @param userMenuEntity
     * @return
     */
    @RequestMapping(value = "/selectUserMenuByRole",  method = RequestMethod.POST ,consumes = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<List<UserMenuEntity>> selectUserMenuByRole(@RequestBody UserMenuEntity userMenuEntity);


    /**
     * 给角色配置菜单
     * @param userMenuEntity
     * @return
     */
    @RequestMapping(value = "/setUserMenu",  method = RequestMethod.POST ,consumes = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse setUserMenu(@RequestBody UserMenuEntity userMenuEntity);
}
