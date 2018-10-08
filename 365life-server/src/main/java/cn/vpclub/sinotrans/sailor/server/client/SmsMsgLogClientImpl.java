package cn.vpclub.sinotrans.sailor.server.client;

import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.SmsMsgLogEntity;
import cn.vpclub.sinotrans.sailor.feign.feignClient.SmsMsgLogClient;
import cn.vpclub.sinotrans.sailor.server.service.SmsMsgLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mingchong on 2018/9/19.
 */
@RestController
public class SmsMsgLogClientImpl implements SmsMsgLogClient {
    @Autowired
    private SmsMsgLogService smsMsgLogService;

    @Override
    public BaseResponse smsLogAdd(@RequestBody SmsMsgLogEntity smsMsgLogDO) {
        return smsMsgLogService.smsLogAdd(smsMsgLogDO);
    }
}
