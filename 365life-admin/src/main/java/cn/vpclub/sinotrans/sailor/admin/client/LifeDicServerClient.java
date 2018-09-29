package cn.vpclub.sinotrans.sailor.admin.client;

import cn.vpclub.sinotrans.sailor.feign.feignClient.LifeDicClient;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * Created by chentao on 2018/7/25.
 */
@FeignClient("${feign-client.life-server}")
public interface LifeDicServerClient extends LifeDicClient {
}
