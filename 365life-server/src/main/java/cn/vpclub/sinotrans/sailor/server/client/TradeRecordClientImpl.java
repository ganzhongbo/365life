package cn.vpclub.sinotrans.sailor.server.client;


import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.sinotrans.sailor.feign.domain.dto.TradeRecordDTO;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.TradeRecord;
import cn.vpclub.sinotrans.sailor.feign.feignClient.TradeRecordClient;
import cn.vpclub.sinotrans.sailor.server.service.TradeRecordService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 南政
 * @className AppointRecordClientImpl
 * @desc
 * @since 2018/10/16 15:25
 */
@RestController
public class TradeRecordClientImpl implements TradeRecordClient {

    @Resource
    private TradeRecordService tradeRecordService;

    /**
     * 成交记录-获取成交记录
     *
     * @param tradeRecord
     * @return
     */
    @Override
    public BaseResponse<TradeRecordDTO> getTradeRecord(@RequestBody TradeRecord tradeRecord) {
        return tradeRecordService.getTradeRecord(tradeRecord);
    }

    /**
     * 成交记录-新增成交记录
     *
     * @param tradeRecordDTO
     * @return
     */
    @Override
    public BaseResponse<Boolean> save(@RequestBody TradeRecordDTO tradeRecordDTO) {
        return tradeRecordService.save(tradeRecordDTO);
    }
}
