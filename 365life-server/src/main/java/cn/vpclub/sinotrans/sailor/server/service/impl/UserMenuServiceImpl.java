package cn.vpclub.sinotrans.sailor.server.service.impl;

import cn.vpclub.demo.common.model.core.enums.ReturnCodeEnum;
import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.RoleMenuEntity;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.UserMenuEntity;
import cn.vpclub.sinotrans.sailor.server.dao.UserMenuDao;
import cn.vpclub.sinotrans.sailor.server.service.RoleMenuService;
import cn.vpclub.sinotrans.sailor.server.service.UserMenuServerService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@Slf4j
public class UserMenuServiceImpl extends ServiceImpl<UserMenuDao, UserMenuEntity> implements UserMenuServerService {

    @Autowired
    private RoleMenuService roleMenuService;

    @Override
    public BaseResponse saveUserMenu(UserMenuEntity userMenuEntity) {
        log.info("字典修改接口请求数据 {} :",userMenuEntity.toString());
        BaseResponse baseResponse = new BaseResponse();
        boolean isRight = this.insert(userMenuEntity);
        if(isRight){
            baseResponse.setReturnCode(ReturnCodeEnum.CODE_1000.getCode());
            baseResponse.setMessage("保存成功");
        }else{
            baseResponse.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
            baseResponse.setMessage("失败");
        }

        return baseResponse;
    }

    @Override
    public BaseResponse updateUserMenu(UserMenuEntity userMenuEntity) {
        log.info("字典修改接口请求数据 {} :",userMenuEntity.toString());
        BaseResponse baseResponse = new BaseResponse();
        boolean isRight = this.updateById(userMenuEntity);
        if(isRight){
            baseResponse.setReturnCode(ReturnCodeEnum.CODE_1000.getCode());
            baseResponse.setMessage("保存成功");
        }else{
            baseResponse.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
            baseResponse.setMessage("失败");
        }
        return baseResponse;
    }

    @Override
    public BaseResponse deleteUserMenu(UserMenuEntity userMenuEntity) {
        log.info("字典修改接口请求数据 {} :",userMenuEntity.toString());
        BaseResponse baseResponse = new BaseResponse();
        boolean isRight = this.deleteById(userMenuEntity);
        if(isRight){
            baseResponse.setReturnCode(ReturnCodeEnum.CODE_1000.getCode());
            baseResponse.setMessage("保存成功");
        }else{
            baseResponse.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
            baseResponse.setMessage("失败");
        }
        return baseResponse;
    }

    /**
     * 查询所有的菜单
     * 当parentId为空时就是查所有菜单
     * 否则就是这个父级(parentId)的子菜单
     * @param userMenuEntity
     * @return
     */
    @Override
    public BaseResponse<List<UserMenuEntity>> selectAllUserMenu(UserMenuEntity userMenuEntity) {
        log.info("字典修改接口请求数据 {} :",userMenuEntity.toString());
        BaseResponse baseResponse = new BaseResponse();

        List<UserMenuEntity> userMenuEntityList =  baseMapper.selectAllUserMenu(userMenuEntity);
        if(userMenuEntityList!=null&&userMenuEntityList.size()>0){
            baseResponse.setDataInfo(userMenuEntityList);
            baseResponse.setReturnCode(ReturnCodeEnum.CODE_1000.getCode());
            baseResponse.setMessage("保存成功");
        }else{
            baseResponse.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            baseResponse.setMessage("暂无数据");
        }
        return baseResponse;
    }
    /**
     * 根据角色查询菜单
     * 当parentId为空时就是查询该角色的所有菜单
     * 否则就是这个角色 以及此父级(parentId)的子菜单
     * @param userMenuEntity
     * @return
     */
    @Override
    public BaseResponse<List<UserMenuEntity>> selectUserMenuByRole(UserMenuEntity userMenuEntity) {
        log.info("字典修改接口请求数据 {} :",userMenuEntity.toString());
        BaseResponse baseResponse = new BaseResponse();
        List<UserMenuEntity> userMenuEntityList =  baseMapper.selectUserMenuByRole(userMenuEntity);
        if(userMenuEntityList!=null&&userMenuEntityList.size()>0){
            baseResponse.setDataInfo(userMenuEntityList);
            baseResponse.setReturnCode(ReturnCodeEnum.CODE_1000.getCode());
            baseResponse.setMessage("保存成功");
        }else{
            baseResponse.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            baseResponse.setMessage("暂无数据");
        }
        return baseResponse;
    }

    /***
     * 配置菜单
     * @param userMenuEntity
     * @return
     */
    @Override
    public BaseResponse setUserMenu(UserMenuEntity userMenuEntity) {
        log.info("字典修改接口请求数据 {} :",userMenuEntity.toString());
        BaseResponse baseResponse = new BaseResponse();
        List<RoleMenuEntity> roleMenuEntityList = new ArrayList<>();
        List<Long> ids =userMenuEntity.getIds();
        if(ids!=null&&ids.size()>0){
            //先删除之前的配置
            RoleMenuEntity roleMenuDo = new RoleMenuEntity();
            roleMenuDo.setUserRole(userMenuEntity.getUserRole());
            boolean isTrue = roleMenuService.deleteRoleMenu(roleMenuDo);
            if(isTrue){
                for(int i = 0 ;i<ids.size();i++){
                    RoleMenuEntity roleMenuEntity = new RoleMenuEntity();
                    roleMenuEntity.setMenuId(ids.get(i));
                    roleMenuEntity.setUserRole(userMenuEntity.getUserRole());
                    roleMenuEntityList.add(roleMenuEntity);
                }
                //批量保存新的配置
                boolean isRight = roleMenuService.saveRoleMenu(roleMenuEntityList);
                if(isRight){
                    baseResponse.setReturnCode(ReturnCodeEnum.CODE_1000.getCode());
                    baseResponse.setMessage("配置成功");
                }else{
                    baseResponse.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
                    baseResponse.setMessage("配置菜单失败");
                }
            }else{
                baseResponse.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
                baseResponse.setMessage("删除之前的菜单配置失败");
            }
        }else{
            baseResponse.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
            baseResponse.setMessage("没有选中菜单");
        }
        return baseResponse;
    }
}
