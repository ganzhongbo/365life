package cn.vpclub.sinotrans.sailor.admin.service;


import cn.vpclub.demo.common.model.core.enums.ReturnCodeEnum;
import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.demo.common.model.utils.common.JsonUtil;
import cn.vpclub.sinotrans.sailor.admin.client.AppointRecordServerClient;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.AppointRecord;
import cn.vpclub.sinotrans.sailor.feign.model.request.AppointRecordRequest;
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
 * @className AppointRecordService
 * @desc
 * @since 2018/10/10 17:12
 */
@Slf4j
@Service
public class AppointRecordService extends BaseServicce {

    @Resource
    private AppointRecordServerClient appointRecordServerClient;

    /**
     * 预约记录-分页列表
     *
     * @param param
     * @return
     */
    public BaseResponse<List<AppointRecord>> getList(AppointRecordRequest param) {
        return appointRecordServerClient.getList(param);
    }

    /**
     * 预约记录-预约新增
     *
     * @param appointRecord
     * @return
     */
    public BaseResponse<Boolean> save(AppointRecord appointRecord) {
        BaseResponse<Boolean> response = new BaseResponse<>();
        //缓存获取用户信息
        Map<String, Object> map = JsonUtil.jsonToMap(getUser());
        if (MapUtils.isEmpty(map)) {
            response.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
            response.setMessage("获取用户登陆信息为空");
            return response;
        }
        Long userId = Long.valueOf(map.get("userId").toString());
        Long current = new Date().getTime();
        String realName = map.get("realName").toString();
        //预约人id
        appointRecord.setAppointId(userId);
        //预约人工号
        appointRecord.setAppointCode(map.get("userCode").toString());
        //预约人姓名
        appointRecord.setAppointName(realName);
        //创建人
        appointRecord.setCreatedBy(userId);
        //创建人名
        appointRecord.setCreatedName(realName);
        //创建时间
        appointRecord.setCreatedTime(current);
        //修改人
        appointRecord.setUpdatedBy(userId);
        //修改时间
        appointRecord.setUpdatedTime(current);
        return appointRecordServerClient.save(appointRecord);
    }

    /**
     * 预约信息-修改状态
     *
     * @param appointRecord
     * @return
     */
    public BaseResponse<Boolean> updateStatus(AppointRecord appointRecord) {
        BaseResponse<Boolean> response = new BaseResponse<>();
        Map<String, Object> map = JsonUtil.jsonToMap(getUser());
        if (MapUtils.isEmpty(map)) {
            response.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
            response.setMessage("获取登陆用户信息失败");
            return response;
        }
        appointRecord.setUpdatedBy(Long.valueOf(map.get("userId").toString()));
        appointRecord.setUpdatedTime(new Date().getTime());
        return appointRecordServerClient.updateStatus(appointRecord);
    }
}
