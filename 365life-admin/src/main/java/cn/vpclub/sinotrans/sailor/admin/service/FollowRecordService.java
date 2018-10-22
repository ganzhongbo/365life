package cn.vpclub.sinotrans.sailor.admin.service;

import cn.vpclub.demo.common.model.core.enums.ReturnCodeEnum;
import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.demo.common.model.utils.common.JsonUtil;
import cn.vpclub.sinotrans.sailor.admin.client.FollowRecordServerClient;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.FollowRecord;
import cn.vpclub.sinotrans.sailor.feign.model.request.FollowRecordRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 南政
 * @className FollowRecordService
 * @desc
 * @since 2018/10/10 17:12
 */
@Slf4j
@Service
public class FollowRecordService extends BaseServicce {

    @Resource
    private FollowRecordServerClient followRecordServerClient;


    /**
     * 跟进记录-分页列表
     *
     * @param param
     * @return
     */
    public BaseResponse<List<FollowRecord>> getList(FollowRecordRequest param) {
        return followRecordServerClient.getList(param);
    }

    /**
     * 跟进记录-新增修改
     *
     * @param followRecord
     * @return
     */
    public BaseResponse<Boolean> saveOrUpdate(FollowRecord followRecord) {
        BaseResponse<Boolean> response = new BaseResponse<>();
        Map<String, Object> map = JsonUtil.jsonToMap(getUser());
        if (MapUtils.isEmpty(map)) {
            response.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
            response.setMessage("获取用户信息失败");
            return response;
        }
        long current = new Date().getTime();
        long userId = Long.valueOf(map.get("userId").toString());
        if (null == followRecord.getId()) {
            followRecord.setCreatedBy(userId);
            followRecord.setCreatedName(map.get("realName").toString());
            followRecord.setCreatedTime(current);
        }
        followRecord.setUpdatedBy(userId);
        followRecord.setUpdatedTime(current);
        return followRecordServerClient.saveOrUpdate(followRecord);
    }
}
