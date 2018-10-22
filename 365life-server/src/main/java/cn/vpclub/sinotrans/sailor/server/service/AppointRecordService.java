package cn.vpclub.sinotrans.sailor.server.service;


import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.AppointRecord;
import cn.vpclub.sinotrans.sailor.feign.model.request.AppointRecordRequest;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * 房源预约记录表 服务类
 *
 * @author 南政
 * @since 2018-10-16
 */
public interface AppointRecordService extends IService<AppointRecord> {

    /**
     * 预约记录-分页列表
     *
     * @param param
     * @return
     */
    BaseResponse<List<AppointRecord>> getList(AppointRecordRequest param);

    /**
     * 预约信息-预约新增
     *
     * @param appointRecord
     * @return
     */
    BaseResponse<Boolean> save(AppointRecord appointRecord);

    /**
     * 预约信息-修改状态
     *
     * @param appointRecord
     * @return
     */
    BaseResponse<Boolean> updateStatus(AppointRecord appointRecord);
}
