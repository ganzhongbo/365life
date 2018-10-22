package cn.vpclub.sinotrans.sailor.admin.web;


import cn.vpclub.demo.common.model.core.enums.ReturnCodeEnum;
import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.sinotrans.sailor.admin.service.TradeRecordService;
import cn.vpclub.sinotrans.sailor.feign.domain.dto.TradeRecordDTO;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.TradeRecord;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 房源成交记录表 前端控制器
 *
 * @author 南政
 * @since 2018-10-17
 */
@RestController
@RequestMapping("/tradeRecord")
public class TradeRecordController {

    @Resource
    private TradeRecordService tradeRecordService;

    /**
     * 成交记录-获取成交记录
     *
     * @param tradeRecord
     * @return
     */
    @PostMapping(value = "/getTradeRecord")
    public BaseResponse<TradeRecordDTO> getTradeRecord(@RequestBody TradeRecord tradeRecord) {
        BaseResponse<TradeRecordDTO> response = new BaseResponse<>();
        if (null == tradeRecord.getSourceId()) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参房源主键sourceId为空");
            return response;
        }
        return tradeRecordService.getTradeRecord(tradeRecord);
    }

    /**
     * 成交记录-新增成交记录
     *
     * @param tradeRecordDTO
     * @return
     */
    @PostMapping(value = "/save")
    public BaseResponse<Boolean> save(@RequestBody TradeRecordDTO tradeRecordDTO) {
        BaseResponse<Boolean> response = new BaseResponse<>();
        if (null == tradeRecordDTO.getSourceId()) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参房源主键sourceId为空");
            return response;
        }
        if (null == tradeRecordDTO.getTradeAmount()) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参成交金额tradeAmount为空");
            return response;
        }
        if (null == tradeRecordDTO.getCommissionAmount()) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参佣金金额commissionAmount为空");
            return response;
        }
        if (CollectionUtils.isEmpty(tradeRecordDTO.getFiles())) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参合同文件集合files为空");
            return response;
        }
        return tradeRecordService.save(tradeRecordDTO);
    }

}

