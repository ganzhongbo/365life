package cn.vpclub.sinotrans.sailor.admin.client;

import cn.vpclub.sinotrans.sailor.feign.feignClient.PassengerSourceClient;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @author 南政
 * @fileName PassengerSourceServerClient.java
 * @desc 客源信息client
 * @since 2018/10/10 11:11
 */
@FeignClient("${feign-client.life-server}")
public interface PassengerSourceServerClient extends PassengerSourceClient {
}
