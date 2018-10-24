package cn.vpclub.sinotrans.sailor.admin.client;

import cn.vpclub.sinotrans.sailor.feign.feignClient.UserLevelClient;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient("${feign-client.life-server}")
public interface UserLevelServerClient extends UserLevelClient {
}
