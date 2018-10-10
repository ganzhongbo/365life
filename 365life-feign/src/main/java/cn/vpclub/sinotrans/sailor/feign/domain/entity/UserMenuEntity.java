package cn.vpclub.sinotrans.sailor.feign.domain.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;
@Data
@TableName("user_menu")
public class UserMenuEntity implements Serializable {

    /**
     * 主键id
     */
    @TableId(value = "id",type = IdType.ID_WORKER)
    private Long id;
	 /**
     * 父级id
     */
    private Long parentId;
    /**
     * 菜单名称
     */
    private String menuName;
    /**
     * 请求地址
     */
    private String requestUrl;
    private Integer deleted;
}
