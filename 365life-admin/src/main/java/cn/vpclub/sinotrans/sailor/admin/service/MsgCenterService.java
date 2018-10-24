package cn.vpclub.sinotrans.sailor.admin.service;


import cn.vpclub.demo.common.model.core.enums.ReturnCodeEnum;
import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.demo.common.model.utils.common.JsonUtil;
import cn.vpclub.sinotrans.sailor.admin.client.MsgCenterServerClient;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.MsgCenter;
import cn.vpclub.sinotrans.sailor.feign.model.request.MsgCenterRequest;
import com.baomidou.mybatisplus.plugins.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author 南政
 * @fileName MsgCenterService.java
 * @desc 消息中心
 * @since 2018/10/22 11:32
 */
@Slf4j
@Service
public class MsgCenterService extends BaseServicce {

    @Resource
    private MsgCenterServerClient msgCenterServerClient;

    /**
     * 消息中心-分页列表
     *
     * @param param
     * @return
     */
    public BaseResponse<Page<MsgCenter>> getPage(MsgCenterRequest param) {
        BaseResponse<Page<MsgCenter>> response = new BaseResponse<>();
        Map<String, Object> map = JsonUtil.jsonToMap(getUser());
        if (MapUtils.isEmpty(map)) {
            response.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
            response.setMessage("获取用户登录信息失败");
            return response;
        }
        param.setReceiverId(Long.valueOf(map.get("userId").toString()));
        return msgCenterServerClient.getPage(param);
    }
}
