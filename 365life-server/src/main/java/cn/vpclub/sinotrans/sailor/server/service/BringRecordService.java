package cn.vpclub.sinotrans.sailor.server.service;

import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.BringRecord;
import cn.vpclub.sinotrans.sailor.feign.model.request.BringRecordRequest;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * 房源带看记录表 服务类
 *
 * @author 南政
 * @since 2018-10-16
 */
public interface BringRecordService extends IService<BringRecord> {

    /**
     * 带看记录-分页列表
     *
     * @param param
     * @return
     */
    BaseResponse<List<BringRecord>> getList(BringRecordRequest param);

    /**
     * 带看记录-带看新增
     *
     * @param bringRecord
     * @return
     */
    BaseResponse<Boolean> save(BringRecord bringRecord);
}
