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
 * 房源基本信息表
 *
 * @author 南政
 * @since 2018-10-17
 */
@Data
@TableName(value = "house_source")
public class HouseSource implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.ID_WORKER)
    private Long id;
    /**
     * 业务类型，默认为1（1、出租，2、出售）
     */
    private Integer businessType;
    /**
     * 所属经纪人id，可以为空
     */
    private Long userId;
    /**
     * 所属经纪人姓名，可以为空
     */
    private String userName;
    /**
     * 商圈id
     */
    private Long tradeAreaId;
    /**
     * 商圈名字
     */
    private String tradeAreaName;
    /**
     * 小区名称
     */
    private String communityName;
    /**
     * 业主电话
     */
    private String ownerPhone;
    /**
     * 具体地址
     */
    private String detailAddress;
    /**
     * 栋号
     */
    private String buildingNumber;
    /**
     * 单元号
     */
    private String unitNumber;
    /**
     * 门室号
     */
    private String roomNumber;
    /**
     * 户型名称（对应数据字典户型名称）
     */
    private String modelName;
    /**
     * 房型
     */
    private String houseModel;
    /**
     * 房屋类型（1、新房，2、二手房，3、其他）
     */
    private Integer houseType;
    /**
     * 出租面积/产权面积
     */
    private String acreage;
    /**
     * 期望售价（单位分）
     */
    private Long expectPrice;
    /**
     * 房源状态（1、已实勘，2、已预约，3、已成交）
     */
    private Integer houseStatus;
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
