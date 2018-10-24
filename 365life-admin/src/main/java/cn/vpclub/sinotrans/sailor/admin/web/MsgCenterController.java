package cn.vpclub.sinotrans.sailor.admin.web;


import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.sinotrans.sailor.admin.service.MsgCenterService;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.MsgCenter;
import cn.vpclub.sinotrans.sailor.feign.model.request.MsgCenterRequest;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 消息中心表 前端控制器
 *
 * @author 南政
 * @since 2018-10-22
 */
@RestController
@RequestMapping("/msgCenter")
public class MsgCenterController {

    @Resource
    private MsgCenterService msgCenterService;

    /**
     * 消息中心-分页列表
     * @param param
     * @return
     */
    @PostMapping(value = "/getPage")
    public BaseResponse<Page<MsgCenter>> getPage(@RequestBody MsgCenterRequest param){
        return msgCenterService.getPage(param);
    }


}

