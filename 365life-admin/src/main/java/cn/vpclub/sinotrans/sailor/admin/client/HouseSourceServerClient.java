package cn.vpclub.sinotrans.sailor.admin.client;

import cn.vpclub.sinotrans.sailor.feign.feignClient.HouseSourceClient;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @author 南政
 * @className HouseSourceServerClient
 * @desc
 * @since 2018/10/15 10:38
 */
@FeignClient("${feign-client.life-server}")
public interface HouseSourceServerClient extends HouseSourceClient {
}
