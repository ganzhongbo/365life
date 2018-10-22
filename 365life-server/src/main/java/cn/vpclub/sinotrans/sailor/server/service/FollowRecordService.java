package cn.vpclub.sinotrans.sailor.server.service;


import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.FollowRecord;
import cn.vpclub.sinotrans.sailor.feign.model.request.FollowRecordRequest;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * 客源/房源跟进记录表 服务类
 *
 * @author 南政
 * @since 2018-10-10
 */
public interface FollowRecordService extends IService<FollowRecord> {

    /**
     * 跟进记录-分页列表
     *
     * @param param
     * @return
     */
    BaseResponse<List<FollowRecord>> getList(FollowRecordRequest param);

    /**
     * 跟进记录-新增修改
     *
     * @param followRecord
     * @return
     */
    BaseResponse<Boolean> saveOrUpdate(FollowRecord followRecord);
}
