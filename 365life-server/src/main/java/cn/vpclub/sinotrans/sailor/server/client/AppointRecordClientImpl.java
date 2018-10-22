package cn.vpclub.sinotrans.sailor.server.client;


import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.AppointRecord;
import cn.vpclub.sinotrans.sailor.feign.feignClient.AppointRecordClient;
import cn.vpclub.sinotrans.sailor.feign.model.request.AppointRecordRequest;
import cn.vpclub.sinotrans.sailor.server.service.AppointRecordService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 南政
 * @className AppointRecordClientImpl
 * @desc
 * @since 2018/10/16 15:25
 */
@RestController
public class AppointRecordClientImpl implements AppointRecordClient {

    @Resource
    private AppointRecordService appointRecordService;

    /**
     * 预约记录-分页列表
     *
     * @param param
     * @return
     */
    @Override
    public BaseResponse<List<AppointRecord>> getList(@RequestBody AppointRecordRequest param) {
        return appointRecordService.getList(param);
    }

    /**
     * 预约信息-预约新增
     *
     * @param appointRecord
     * @return
     */
    @Override
    public BaseResponse<Boolean> save(@RequestBody AppointRecord appointRecord) {
        return appointRecordService.save(appointRecord);
    }

    /**
     * 预约信息-修改状态
     *
     * @param appointRecord
     * @return
     */
    @Override
    public BaseResponse<Boolean> updateStatus(@RequestBody AppointRecord appointRecord) {
        return appointRecordService.updateStatus(appointRecord);
    }
}
