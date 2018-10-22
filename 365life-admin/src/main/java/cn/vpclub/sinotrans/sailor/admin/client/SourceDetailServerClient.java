package cn.vpclub.sinotrans.sailor.admin.client;


import cn.vpclub.sinotrans.sailor.feign.feignClient.SourceDetailClient;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @author 南政
 * @className SourceDetailServerClient
 * @desc
 * @since 2018/10/15 10:38
 */
@FeignClient("${feign-client.life-server}")
public interface SourceDetailServerClient extends SourceDetailClient {
}
