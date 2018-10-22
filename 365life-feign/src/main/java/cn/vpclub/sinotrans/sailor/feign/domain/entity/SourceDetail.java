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
 * <p>
 * 房源实勘详细信息表
 * </p>
 *
 * @author 南政123
 * @since 2018-10-17
 */
@Data
@TableName(value = "source_detail")
public class SourceDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.ID_WORKER)
    private Long id;
    /**
     * 房源基本信息主表id
     */
    private Long sourceId;
    /**
     * 房屋朝向
     */
    private String orientation;
    /**
     * 房屋户型id（对应数据字典配置）
     */
    private Long dictId;
    /**
     * 房屋内部结构（1、复式，2、错层）
     */
    private Integer innerStructure;
    /**
     * 装修请况（1、豪华，2、高档，3、一般，4、毛坯）
     */
    private Integer decoration;
    /**
     * 物业权属（1、商品房，2、房改房，3、安置房，4、经济适用房，5、其他）
     */
    private Integer propertyBelong;
    /**
     * 物业用途（1、普通住宅，2、商业类，3、别墅，4、其他）
     */
    private Integer propertyUse;
    /**
     * 权证情况（1、产权证，2、土地证）
     */
    private Integer warrant;
    /**
     * 土地性质（1、出让地，2、划拨地）
     */
    private Integer landStatus;
    /**
     * 房龄（1、两年内，2、2-5年，3、5-10年，4、10年以上）
     */
    private Integer houseAge;
    /**
     * 产权年限
     */
    private String serviceLife;
    /**
     * 建筑结构(1、混合，2、框架，3、其他)
     */
    private Integer buildingStructure;
    /**
     * 房屋当前楼层
     */
    private Integer currentFloor;
    /**
     * 房屋总楼层
     */
    private Integer totalFloor;
    /**
     * 使用情况（1、空置，2、自住，3、出租）
     */
    private Integer useCondition;
    /**
     * 装修时间（年）
     */
    private String decorationTime;
    /**
     * 贷款情况（1、有，2、无）
     */
    private Integer loanCondition;
    /**
     * 物业名称
     */
    private String propertyName;
    /**
     * 物业费（单位分）
     */
    private Long propertyFee;
    /**
     * 车库面积（单位㎡）
     */
    private String garageArea;
    /**
     * 建筑面积（单位㎡）
     */
    private Integer buildingArea;
    /**
     * 使用面积（单位㎡）
     */
    private Integer usedArea;
    /**
     * 公摊面积（单位㎡）
     */
    private Integer pooledArea;
    /**
     * 多个逗号拼接（1、管道煤气，2、宽带，3、有线电视，4、停车场，5、电梯）
     */
    private String supportFacility;
    /**
     * 核心卖点
     */
    private String coreSellPoint;
    /**
     * 业主心态
     */
    private String ownerMentality;
    /**
     * 小区配套
     */
    private String communitySupport;
    /**
     * 服务介绍
     */
    private String serviceIntro;
    /**
     * 是否拿到钥匙（1、否，2、是）
     */
    private Integer isKey;
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
