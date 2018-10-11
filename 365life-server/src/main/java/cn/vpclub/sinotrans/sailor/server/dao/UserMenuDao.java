package cn.vpclub.sinotrans.sailor.server.dao;

import cn.vpclub.sinotrans.sailor.feign.domain.entity.UserMenuEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

public interface UserMenuDao extends BaseMapper<UserMenuEntity> {

    /**
     * 查询所有的菜单
     * 当parentId为空时就是查所有菜单
     * 否则就是这个父级(parentId)的子菜单
     * @param userMenuEntity
     * @return
     */

    public List<UserMenuEntity> selectAllUserMenu(UserMenuEntity userMenuEntity);

    /**
     * 根据角色查询菜单
     * 当parentId为空时就是查询该角色的所有菜单
     * 否则就是这个角色 以及此父级(parentId)的子菜单
     * @param userMenuEntity
     * @return
     */
    public List<UserMenuEntity> selectUserMenuByRole(UserMenuEntity userMenuEntity);

}
