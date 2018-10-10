package cn.vpclub.sinotrans.sailor.feign.domain.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("role_menu")
public class RoleMenuEntity implements Serializable {

    /**
     * 主键id
     */
    @TableId(value = "id",type = IdType.ID_WORKER)
    private Long id;
    /**
     * 菜单名称
     */
    private Long menuId;
    /**
     * 用户角色
     */
    private Integer userRole;

}
