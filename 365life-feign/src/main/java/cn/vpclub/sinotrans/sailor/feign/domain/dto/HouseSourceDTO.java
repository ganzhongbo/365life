package cn.vpclub.sinotrans.sailor.feign.domain.dto;

import cn.vpclub.sinotrans.sailor.feign.domain.entity.*;
import lombok.Data;

import java.util.List;

/**
 * @author 南政
 * @className HouseSourceDTO
 * @desc
 * @since 2018/10/16 9:21
 */
@Data
public class HouseSourceDTO extends HouseSource {

    /**
     * 实勘信息集合
     */
    private SourceDetailDTO sourceDetailDTO;

    /**
     * 跟进信息集合
     */
    private List<FollowRecord> followRecords;

    /**
     * 带看信息集合
     */
    private List<BringRecord> bringRecords;

    /**
     * 签约信息集合
     */
    private List<AppointRecord> appointRecords;

    /**
     * 成交信息集合
     */
    private TradeRecordDTO tradeRecordDTO;

}
