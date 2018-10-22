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
     * 所属经纪人id，可以为空
     */
    private Long userId;

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
