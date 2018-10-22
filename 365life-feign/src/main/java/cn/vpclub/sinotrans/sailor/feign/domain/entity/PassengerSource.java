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
 * 客源信息记录表
 *
 * @author 南政
 * @since 2018-10-17
 */
@Data
@TableName(value = "passenger_source")
public class PassengerSource implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.ID_WORKER)
    private Long id;
    /**
     * 所属经纪人id，可以为空
     */
    private Long userId;
    /**
     * 所属经纪人姓名，可以为空
     */
    private String userName;
    /**
     * 客户姓名
     */
    private String passengerName;
    /**
     * 客户性别，默认为1（1、男，2、女）
     */
    private Integer passengerSex;
    /**
     * 客户电话
     */
    private String passengerPhone;
    /**
     * 需求类型，默认为1（1、租房，2、买房）
     */
    private Integer requireType;
    /**
     * 需求地段
     */
    private String requireLocation;
    /**
     * 需求户型
     */
    private String requireModel;
    /**
     * 需求预算（单位分）
     */
    private Long requireBudget;
    /**
     * 偏好描述
     */
    private String preferenceDesc;
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
