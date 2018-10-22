package cn.vpclub.sinotrans.sailor.admin.service;


import cn.vpclub.demo.common.model.core.enums.ReturnCodeEnum;
import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.demo.common.model.utils.common.JsonUtil;
import cn.vpclub.sinotrans.sailor.admin.client.BringRecordServerClient;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.BringRecord;
import cn.vpclub.sinotrans.sailor.feign.model.request.BringRecordRequest;
import com.baomidou.mybatisplus.plugins.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 南政
 * @className BringRecordService
 * @desc
 * @since 2018/10/10 17:12
 */
@Slf4j
@Service
public class BringRecordService extends BaseServicce {

    @Resource
    private BringRecordServerClient bringRecordServerClient;

    /**
     * 带看记录-分页列表
     *
     * @param param
     * @return
     */
    public BaseResponse<List<BringRecord>> getList(BringRecordRequest param) {
        return bringRecordServerClient.getList(param);
    }

    /**
     * 带看记录-带看新增
     *
     * @param bringRecord
     * @return
     */
    public BaseResponse<Boolean> save(BringRecord bringRecord) {
        BaseResponse<Boolean> response = new BaseResponse<>();
        Map<String, Object> map = JsonUtil.jsonToMap(getUser());
        if (MapUtils.isEmpty(map)) {
            response.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
            response.setMessage("获取用户信息失败");
            return response;
        }
        bringRecord.setCreatedBy(Long.valueOf(map.get("userId").toString()));
        bringRecord.setCreatedName(map.get("realName").toString());
        bringRecord.setCreatedTime(new Date().getTime());
        bringRecord.setUpdatedBy(Long.valueOf(map.get("userId").toString()));
        bringRecord.setUpdatedTime(new Date().getTime());
        return bringRecordServerClient.save(bringRecord);
    }
}
