package cn.vpclub.sinotrans.sailor.server.service.impl;

import cn.vpclub.sinotrans.sailor.feign.domain.entity.RoleMenuEntity;
import cn.vpclub.sinotrans.sailor.server.dao.RoleMenuDao;
import cn.vpclub.sinotrans.sailor.server.service.RoleMenuService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;
import java.util.List;
@Service
@Slf4j
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuDao, RoleMenuEntity> implements RoleMenuService {


    @Override
    public boolean saveRoleMenu(List<RoleMenuEntity> roleMenuEntityList) {
        return this.insertBatch(roleMenuEntityList);
    }

    /**
     * 根据角色删除数据
     * @param roleMenuEntity
     * @return
     */
    @Override
    public boolean deleteRoleMenu(RoleMenuEntity roleMenuEntity) {
        EntityWrapper ew=new EntityWrapper();
        ew.eq("user_role",roleMenuEntity.getUserRole());
        return this.delete(ew);
    }
}
