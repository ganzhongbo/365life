package cn.vpclub.sinotrans.sailor.feign.model.request;

import cn.vpclub.demo.common.model.core.model.request.PageBaseSearchParam;
import lombok.Data;

/**
 * @author 南政
 * @className HouseSourceRequest
 * @desc 房源信息请求实体类
 * @since 2018/10/10 11:30
 */
@Data
public class HouseSourceRequest extends PageBaseSearchParam {

    /**
     * 主键id
     */
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
     * 消息id
     */
    private Long msgId;
    /**
     * 是否抢单，1、抢单，2、不抢单
     */
    private Integer isGrab;

    /**
     * 商圈id
     */
    private Long tradeAreaId;

    /**
     * 房型
     */
    private String modelName;

    /**
     * 房源状态（1、已实勘，2、已预约，3、已成交）
     */
    private Integer houseStatus;

    /**
     * 房屋类型（1、新房，2、二手房，3、其他）
     */
    private Integer houseType;

    /**
     * 房屋属性（1、公盘，2、私盘）
     */
    private Integer houseNature;
}
