package cn.vpclub.sinotrans.sailor.admin.client;

import cn.vpclub.sinotrans.sailor.feign.feignClient.UserMenuClient;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient("${feign-client.life-server}")
public interface UserMenuServerClient extends UserMenuClient {
}
