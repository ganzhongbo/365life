package cn.vpclub.sinotrans.sailor.feign.domain.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

/**
 * 消息中心表
 *
 * @author 南政
 * @since 2018-10-22
 */
@Data
@TableName(value = "msg_center")
public class MsgCenter implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.ID_WORKER)
    private Long id;
    /**
     * 消息类型（1、房源类型，预留其他，可增加）
     */
    private Integer msgType;
    /**
     * 消息所属，默认为1（1、私有消息，2、公有消息）
     */
    private Integer msgBelong;
    /**
     * 如果消息所属为私有，那么此字段不为空
     */
    private Long receiverId;
    /**
     * 发布者主键
     */
    private Long sendId;
    /**
     * 发布者姓名
     */
    private String sendName;
    /**
     * 发布者头像
     */
    private String sendImage;
    /**
     * 业务id（房源id或者其他关联id）
     */
    private Long businessId;
    /**
     * 商圈主键
     */
    private Long tradeAreaId;
    /**
     * 商圈名字
     */
    private String tradeAreaName;
    /**
     * 消息状态（1、未处理，2、已处理）
     */
    private Integer msgStatus;
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
