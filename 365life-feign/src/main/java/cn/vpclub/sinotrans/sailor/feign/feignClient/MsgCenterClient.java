package cn.vpclub.sinotrans.sailor.feign.feignClient;


import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.MsgCenter;
import cn.vpclub.sinotrans.sailor.feign.model.request.MsgCenterRequest;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author 南政
 * @fileName MsgCenterClient.java
 * @desc 消息中心client
 * @since 2018/10/22 11:35
 */
@RequestMapping(value = "/msgCenterClient")
public interface MsgCenterClient {

    @RequestMapping(value = "/getPage",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    BaseResponse<Page<MsgCenter>> getPage(MsgCenterRequest param);
}
