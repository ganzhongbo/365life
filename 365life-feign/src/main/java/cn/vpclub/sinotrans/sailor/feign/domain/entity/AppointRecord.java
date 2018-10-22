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
 * 房源预约记录表
 *
 * @author 南政
 * @since 2018-10-17
 */
@Data
@TableName(value = "appoint_record")
public class AppointRecord implements Serializable {

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
     * 预约人的id（即操作人的id）
     */
    private Long appointId;
    /**
     * 预约人的姓名（即操作人的姓名）
     */
    private String appointName;
    /**
     * 预约人的工号（即操作人的工号）
     */
    private String appointCode;
    /**
     * 预约人所需要的材料
     */
    private String appointNeed;
    /**
     * 预约时间
     */
    private Long appointTime;
    /**
     * 客户的电话
     */
    private String passengerPhone;
    /**
     * 客户的姓名
     */
    private String passengerName;
    /**
     * 客户所需要的材料
     */
    private String passengerNeed;
    /**
     * 预约状态（1、预约中，2、达成签约失败，3、达成签约成功）
     */
    private Integer appointStatus;
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
