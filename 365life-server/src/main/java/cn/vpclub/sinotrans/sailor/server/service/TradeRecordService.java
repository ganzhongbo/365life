package cn.vpclub.sinotrans.sailor.server.service;


import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.sinotrans.sailor.feign.domain.dto.TradeRecordDTO;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.TradeRecord;
import com.baomidou.mybatisplus.service.IService;

/**
 * 房源成交记录表 服务类
 *
 * @author 南政
 * @since 2018-10-17
 */
public interface TradeRecordService extends IService<TradeRecord> {

    /**
     * 成交记录-获取成交记录
     *
     * @param tradeRecord
     * @return
     */
    BaseResponse<TradeRecordDTO> getTradeRecord(TradeRecord tradeRecord);

    /**
     * 成交记录-新增成交记录
     *
     * @param tradeRecordDTO
     * @return
     */
    BaseResponse<Boolean> save(TradeRecordDTO tradeRecordDTO);
}
