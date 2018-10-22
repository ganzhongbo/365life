package cn.vpclub.sinotrans.sailor.admin.client;

import cn.vpclub.sinotrans.sailor.feign.feignClient.FollowRecordClient;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @author 南政
 * @className FollowRecordServerClient
 * @desc 跟进记录client
 * @since 2018/10/10 17:12
 */
@FeignClient("${feign-client.life-server}")
public interface FollowRecordServerClient extends FollowRecordClient {
}
