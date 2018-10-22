package cn.vpclub.sinotrans.sailor.server.client;


import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.FollowRecord;
import cn.vpclub.sinotrans.sailor.feign.feignClient.FollowRecordClient;
import cn.vpclub.sinotrans.sailor.feign.model.request.FollowRecordRequest;
import cn.vpclub.sinotrans.sailor.server.service.FollowRecordService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 南政
 * @className PassengerSourceClientImpl
 * @desc 客源信息client实现类
 * @since 2018/10/10 11:15
 */
@RestController
public class FollowRecordClientImpl implements FollowRecordClient {

    @Resource
    private FollowRecordService followRecordService;

    /**
     * 跟进记录-分页列表
     *
     * @param param
     * @return
     */
    @Override
    public BaseResponse<List<FollowRecord>> getList(@RequestBody FollowRecordRequest param) {
        return followRecordService.getList(param);
    }

    /**
     * 跟进记录-新增修改
     *
     * @param followRecord
     * @return
     */
    @Override
    public BaseResponse<Boolean> saveOrUpdate(@RequestBody FollowRecord followRecord) {
        return followRecordService.saveOrUpdate(followRecord);
    }
}
