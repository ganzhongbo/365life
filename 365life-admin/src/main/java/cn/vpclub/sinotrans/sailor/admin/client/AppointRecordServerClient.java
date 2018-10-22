package cn.vpclub.sinotrans.sailor.admin.client;


import cn.vpclub.sinotrans.sailor.feign.feignClient.AppointRecordClient;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @author 南政
 * @className AppointRecordServerClient
 * @desc 预约记录client
 * @since 2018/10/10 17:12
 */
@FeignClient("${feign-client.life-server}")
public interface AppointRecordServerClient extends AppointRecordClient {


}
