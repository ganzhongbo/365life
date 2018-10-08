package cn.vpclub.sinotrans.sailor.server.service.impl;

import cn.vpclub.demo.common.model.core.enums.ReturnCodeEnum;
import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.demo.common.model.utils.common.IdWorker;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.SmsMsgLogEntity;
import cn.vpclub.sinotrans.sailor.server.dao.SmsMsgLogDao;
import cn.vpclub.sinotrans.sailor.server.service.SmsMsgLogService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Created by mingchong on 2018/9/19.
 */
@Service
@Slf4j
public class SmsMsgLogServiceImpl extends ServiceImpl<SmsMsgLogDao, SmsMsgLogEntity> implements SmsMsgLogService {

    @Override
    public BaseResponse smsLogAdd(SmsMsgLogEntity smsMsgLogDO) {
        BaseResponse baseResponse = new BaseResponse();
        Long id = IdWorker.getId();
        smsMsgLogDO.setId(id);
        this.insert(smsMsgLogDO);
        baseResponse.setReturnCode(ReturnCodeEnum.CODE_1000.getCode());
        baseResponse.setMessage("success");
        baseResponse.setDataInfo(smsMsgLogDO);
        return baseResponse;

    }
}
