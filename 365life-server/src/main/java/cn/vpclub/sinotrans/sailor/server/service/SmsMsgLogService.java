package cn.vpclub.sinotrans.sailor.server.service;

import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.SmsMsgLogEntity;
import com.baomidou.mybatisplus.service.IService;

/**
 * Created by mingchong on 2018/9/19.
 */
public interface SmsMsgLogService extends IService<SmsMsgLogEntity> {

    BaseResponse smsLogAdd(SmsMsgLogEntity smsMsgLogDO);

}
