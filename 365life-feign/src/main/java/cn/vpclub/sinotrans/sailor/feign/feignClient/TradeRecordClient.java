package cn.vpclub.sinotrans.sailor.feign.feignClient;


import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.sinotrans.sailor.feign.domain.dto.TradeRecordDTO;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.BringRecord;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.TradeRecord;
import cn.vpclub.sinotrans.sailor.feign.model.request.BringRecordRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author 南政
 * @className TradeRecordClient
 * @desc 成交记录client
 * @since 2018/10/10 17:15
 */
@RequestMapping(value = "/tradeRecordClient")
public interface TradeRecordClient {

    /**
     * 成交记录-获取成交记录
     *
     * @param tradeRecord
     * @return
     */
    @RequestMapping(value = "/getTradeRecord", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    BaseResponse<TradeRecordDTO> getTradeRecord(TradeRecord tradeRecord);

    /**
     * 成交记录-新增成交记录
     *
     * @param tradeRecordDTO
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    BaseResponse<Boolean> save(TradeRecordDTO tradeRecordDTO);
}

