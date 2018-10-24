package cn.vpclub.sinotrans.sailor.server.client;


import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.MsgCenter;
import cn.vpclub.sinotrans.sailor.feign.feignClient.MsgCenterClient;
import cn.vpclub.sinotrans.sailor.feign.model.request.MsgCenterRequest;
import cn.vpclub.sinotrans.sailor.server.service.MsgCenterService;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 南政
 * @fileName MsgCenterClientImpl.java
 * @desc 消息中心client实现类
 * @since 2018/10/22 11:37
 */
@RestController
public class MsgCenterClientImpl implements MsgCenterClient {

    @Resource
    private MsgCenterService msgCenterService;


    @Override
    public BaseResponse<Page<MsgCenter>> getPage(@RequestBody MsgCenterRequest param) {
        return msgCenterService.getPage(param);
    }
}