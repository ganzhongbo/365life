package cn.vpclub.sinotrans.sailor.admin.client;


import cn.vpclub.sinotrans.sailor.feign.feignClient.MsgCenterClient;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @author 南政
 * @className BringRecordServerClient
 * @desc 带看记录client
 * @since 2018/10/10 17:12
 */
@FeignClient("${feign-client.life-server}")
public interface MsgCenterServerClient extends MsgCenterClient {
}
