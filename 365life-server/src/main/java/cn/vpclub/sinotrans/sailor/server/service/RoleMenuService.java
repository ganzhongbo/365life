package cn.vpclub.sinotrans.sailor.server.service;

import cn.vpclub.sinotrans.sailor.feign.domain.entity.RoleMenuEntity;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

public interface RoleMenuService extends IService<RoleMenuEntity> {

    /**
     * 保存
     */
    public  boolean  saveRoleMenu(List<RoleMenuEntity> roleMenuEntityList);

    /**
     * 删除
     */
    public  boolean  deleteRoleMenu(RoleMenuEntity roleMenuEntity);





}
