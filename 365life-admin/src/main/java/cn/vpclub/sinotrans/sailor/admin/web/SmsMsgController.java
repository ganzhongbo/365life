package cn.vpclub.sinotrans.sailor.admin.web;

import cn.vpclub.demo.common.model.core.enums.ReturnCodeEnum;
import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.demo.common.model.utils.common.JsonUtil;
import cn.vpclub.demo.common.model.utils.common.StringUtil;
import cn.vpclub.sinotrans.sailor.admin.service.SmsMsgLogService;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.SmsMsgLogEntity;
import cn.vpclub.sinotrans.sailor.feign.model.request.SmsMsgSendRequest;
import cn.vpclub.wuhan.redis.utils.RedisUtils;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

import static cn.vpclub.sinotrans.sailor.admin.utils.SmsDemoUtils.*;


/**
 * Created by mingchong on 2018/9/21.(短信发送接口)
 */
@RestController
@RequestMapping(value = "/smsMsg")
@Slf4j
public class SmsMsgController {
    @Value("${sinotrans.smsservice.lifeTemplateCode}")
    private String shipownerTemplateCode;

    @Value("${life.smsservice.lifeMessege}")
    private String lifeMessge;

    @Autowired
    private RedisUtils redis;

    @Autowired
    private SmsMsgLogService smsMsgLogService;


    /**
     * 给用户发送短信验证码
     * @author migchong
     */
    @RequestMapping(value = "/smsMsgSend")
    public BaseResponse smsMsgSend(@RequestBody SmsMsgSendRequest smsMsgSendDTO) throws ClientException, InterruptedException{
        BaseResponse baseResponse = new BaseResponse();
        log.info("smsMsgSendDTO :{}", JsonUtil.objectToJson(smsMsgSendDTO));

        if(StringUtil.isEmpty(smsMsgSendDTO)){
            baseResponse.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            baseResponse.setMessage("短信发送入参实体 smsMsgSendDTO 为空");
            log.info("smsMsgSend baseResponse : {}", JsonUtil.objectToJson(baseResponse));
            return baseResponse;
        }
        if(StringUtil.isEmpty(smsMsgSendDTO.getMobile())){
            baseResponse.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            baseResponse.setMessage("短信发送入参手机号 mobile 为空");
            log.info("smsMsgSend baseResponse : {}", JsonUtil.objectToJson(baseResponse));
            return baseResponse;
        }
        if(StringUtil.isEmpty(smsMsgSendDTO.getType())){
            baseResponse.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            baseResponse.setMessage("短信发送入参 类型 type 为空");
            log.info("smsMsgSend baseResponse : {}", JsonUtil.objectToJson(baseResponse));
            return baseResponse;
        }


        //根据类型拼接redisCode
        String redisCode = null;
        Boolean isRight = false;
        //查询数据字典已经有的短信type值
        redisCode = "SMS_MSG_SEND:"+"TYPE_"+smsMsgSendDTO.getType()+":MOBILE_"+smsMsgSendDTO.getMobile();
        //删除对应redis中的手机号验证码
        redis.delete(redisCode);
        //生成6位随机验证码
        String smcCode = getRandNum();
        //redis存储验证码
        redis.set(redisCode,smcCode,300);

        //给用户手机号发送验证码
        SendSmsResponse response = null;
        try {
            response = sendSms(smcCode, smsMsgSendDTO.getMobile(), lifeMessge);
            log.info("response : {}", JsonUtil.objectToJson(response));
        } catch (ClientException e) {
            e.printStackTrace();
        }

        log.info("短信接口返回的数据----------------");
        log.info("Code=" + response.getCode());
        log.info("Message=" + response.getMessage());
        log.info("RequestId=" + response.getRequestId());
        log.info("BizId=" + response.getBizId());

        Thread.sleep(3000L);
        //短信发送验证码用表记录下来
        SmsMsgLogEntity smsMsgLogDO = new SmsMsgLogEntity();
        smsMsgLogDO.setType(smsMsgSendDTO.getType());
        smsMsgLogDO.setSmsCode(smcCode);
        smsMsgLogDO.setSmsResponse(JsonUtil.objectToJson(response));
        smsMsgLogDO.setMobile(smsMsgSendDTO.getMobile());
        smsMsgLogDO.setCreatedTime(new Date().getTime());
        smsMsgLogDO.setUpdatedTime(new Date().getTime());
        smsMsgLogDO.setDeleted(1);
        BaseResponse smsResponse = smsMsgLogService.smsLogAdd(smsMsgLogDO);

        //查明细
        if(response.getCode() != null && response.getCode().equals("OK")) {
            QuerySendDetailsResponse querySendDetailsResponse = querySendDetails(response.getBizId());
            log.info("短信明细查询接口返回数据----------------");
            log.info("Code=" + querySendDetailsResponse.getCode());
            log.info("Message=" + querySendDetailsResponse.getMessage());
            int i = 0;
            for(QuerySendDetailsResponse.SmsSendDetailDTO smsSendDetailDTO : querySendDetailsResponse.getSmsSendDetailDTOs())
            {
                log.info("SmsSendDetailDTO["+i+"]:");
                log.info("Content=" + smsSendDetailDTO.getContent());
                log.info("ErrCode=" + smsSendDetailDTO.getErrCode());
                log.info("OutId=" + smsSendDetailDTO.getOutId());
                log.info("PhoneNum=" + smsSendDetailDTO.getPhoneNum());
                log.info("ReceiveDate=" + smsSendDetailDTO.getReceiveDate());
                log.info("SendDate=" + smsSendDetailDTO.getSendDate());
                log.info("SendStatus=" + smsSendDetailDTO.getSendStatus());
                log.info("Template=" + smsSendDetailDTO.getTemplateCode());
            }
            log.info("TotalCount=" + querySendDetailsResponse.getTotalCount());
            log.info("RequestId=" + querySendDetailsResponse.getRequestId());
        }

        baseResponse.setReturnCode(ReturnCodeEnum.CODE_1000.getCode());
        baseResponse.setMessage("success");
        log.info("smsMsgSend baseResponse : {}", JsonUtil.objectToJson(baseResponse));
        return baseResponse;
    }

}
