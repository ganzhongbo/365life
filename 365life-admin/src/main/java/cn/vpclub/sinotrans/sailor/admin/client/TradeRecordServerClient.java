package cn.vpclub.sinotrans.sailor.admin.client;


import cn.vpclub.sinotrans.sailor.feign.feignClient.TradeRecordClient;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @author 南政
 * @className TradeRecordServerClient
 * @desc 成交记录client
 * @since 2018/10/10 17:12
 */
@FeignClient("${feign-client.life-server}")
public interface TradeRecordServerClient extends TradeRecordClient {


}
