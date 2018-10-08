package cn.vpclub.sinotrans.sailor.feign.feignClient;


import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.SmsMsgLogEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by mingchong on 2018/9/19.
 */
@RequestMapping("/smsMsgLogClient")
public interface SmsMsgLogClient {

    @RequestMapping(value = "/smsLogAdd", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    BaseResponse smsLogAdd(@RequestBody  SmsMsgLogEntity smsMsgLogDO);
}
