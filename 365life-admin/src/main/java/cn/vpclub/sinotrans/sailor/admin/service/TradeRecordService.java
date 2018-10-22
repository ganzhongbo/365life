package cn.vpclub.sinotrans.sailor.admin.service;


import cn.vpclub.demo.common.model.core.enums.ReturnCodeEnum;
import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.demo.common.model.utils.common.JsonUtil;
import cn.vpclub.sinotrans.sailor.admin.client.TradeRecordServerClient;
import cn.vpclub.sinotrans.sailor.feign.domain.dto.TradeRecordDTO;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.TradeRecord;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * @author 南政
 * @className TradeRecordService
 * @desc
 * @since 2018/10/10 17:12
 */
@Slf4j
@Service
public class TradeRecordService extends BaseServicce {

    @Resource
    private TradeRecordServerClient tradeRecordServerClient;

    /**
     * 成交记录-获取成交记录
     *
     * @param tradeRecord
     * @return
     */
    public BaseResponse<TradeRecordDTO> getTradeRecord(TradeRecord tradeRecord) {
        return tradeRecordServerClient.getTradeRecord(tradeRecord);
    }

    /**
     * 成交记录-新增成交记录
     *
     * @param tradeRecordDTO
     * @return
     */
    public BaseResponse<Boolean> save(TradeRecordDTO tradeRecordDTO) {
        BaseResponse<Boolean> response = new BaseResponse<>();
        Map<String, Object> map = JsonUtil.jsonToMap(getUser());
        if (MapUtils.isEmpty(map)) {
            response.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
            response.setMessage("获取登录用户信息失败");
            return response;
        }
        tradeRecordDTO.setCreatedName(map.get("realName").toString());
        tradeRecordDTO.setCreatedBy(Long.valueOf(map.get("userId").toString()));
        tradeRecordDTO.setCreatedTime(new Date().getTime());
        tradeRecordDTO.setUpdatedBy(Long.valueOf(map.get("userId").toString()));
        tradeRecordDTO.setUpdatedTime(new Date().getTime());
        return tradeRecordServerClient.save(tradeRecordDTO);
    }
}
