package cn.vpclub.sinotrans.sailor.feign.domain.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.Version;

import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 房源带看记录表
 *
 * @author 南政
 * @since 2018-10-17
 */
@Data
@TableName(value = "bring_record")
public class BringRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.ID_WORKER)
    private Long id;
    /**
     * 房源id
     */
    private Long sourceId;
    /**
     * 带看人的id（即房源拥有者的id）
     */
    private Long bringId;
    /**
     * 带看人的姓名（即房源拥有者的姓名）
     */
    private String bringName;
    /**
     * 带看时间
     */
    private Long bringTime;
    /**
     * 客户的电话
     */
    private String passengerPhone;
    /**
     * 客户的姓名
     */
    private String passengerName;
    /**
     * 填报人姓名
     */
    private String createdName;
    /**
     * 创建人id
     */
    private Long createdBy;
    /**
     * 创建时间
     */
    private Long createdTime;
    /**
     * 更新人
     */
    private Long updatedBy;
    /**
     * 更新时间
     */
    private Long updatedTime;
    /**
     * 删除标识(1:在线; 2:删除)
     */
    @TableLogic
    private Integer deleted;
    /**
     * 备注
     */
    private String remark;
    /**
     * 扩展字段1
     */
    private String field01;
    /**
     * 扩展字段2
     */
    private String field02;
    /**
     * 扩展字段3
     */
    private String field03;


}
