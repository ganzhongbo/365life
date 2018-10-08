package cn.vpclub.sinotrans.sailor.admin.service;

import cn.vpclub.sinotrans.sailor.feign.feignClient.SmsMsgLogClient;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * Created by mingchong on 2018/7/10.
 */
@FeignClient("${feign-client.life-server}")
public interface SmsMsgLogService extends SmsMsgLogClient {
}
