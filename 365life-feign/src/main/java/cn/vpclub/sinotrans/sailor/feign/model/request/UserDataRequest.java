package cn.vpclub.sinotrans.sailor.feign.model.request;

import cn.vpclub.demo.common.model.core.model.request.PageBaseSearchParam;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.User;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
@Data
public class UserDataRequest extends PageBaseSearchParam {


    private Long userId;//用户主键
    private String userName;//用户名
    private String realName;//姓名
    private String userCode;//用户编号
    private Integer userRole;//用户角色

    private Integer type;//1:今天2:本周3:本月
    private Integer selectType;//1:租房2:售房

    private Integer rentingerSource;//租房客源(获取的)
    private Integer buyerSource ;//买房客源(获取的)
    private Integer guestSourceTotal;//客源合计

    private Integer rentingItem;//租房量(获取的)
    private Integer sellHoseItem;//售房量(获取的)
    private Integer houseTotal;//房源合计

    private Integer renting;//租房(成交单)
    private BigDecimal rentingAchievement;//租房业绩

    private Integer sellHose;//售房(单)
    private BigDecimal sellHoseAchievement;//售房业绩

    private BigDecimal teamAchievement;//团队业绩
    private BigDecimal totalAchievement;//业绩合计


    private Integer addHoseCount;//录入房源数量
    private Integer realExplorationCount;//实勘
    private Integer takeLookCount;//带看
    private Integer publicCount;//发布
    private Integer clickCount;//点击
    private Integer addGuestsCount;//录客
    private Integer receiveGuestsCount;//获客

    private Long createdTime;//创建时间
    private Long stratTime;//开始时间
    private Long endTime;//结束时间

}
