package cn.vpclub.sinotrans.sailor.feign.domain.dto;

import cn.vpclub.sinotrans.sailor.feign.domain.entity.TradeRecord;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.UserResouceEntity;
import lombok.Data;

import java.util.List;

/**
 * @author 南政
 * @className TradeRecordDTO
 * @desc
 * @since 2018/10/17 14:45
 */
@Data
public class TradeRecordDTO extends TradeRecord {
    /**
     * 合同文件集合
     */
    private List<UserResouceEntity> files;
}
