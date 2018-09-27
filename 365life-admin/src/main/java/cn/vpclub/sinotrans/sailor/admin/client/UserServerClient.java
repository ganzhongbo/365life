package cn.vpclub.sinotrans.sailor.admin.client;

import cn.vpclub.sinotrans.sailor.feign.feignClient.UserClient;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * Created by chentao on 2018/7/25.
 */
@FeignClient("${feign-client.userinfo-server}")
public interface UserServerClient extends UserClient {
}
