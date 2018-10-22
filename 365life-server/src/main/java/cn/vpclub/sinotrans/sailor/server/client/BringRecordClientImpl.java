package cn.vpclub.sinotrans.sailor.server.client;

import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.BringRecord;
import cn.vpclub.sinotrans.sailor.feign.feignClient.BringRecordClient;
import cn.vpclub.sinotrans.sailor.feign.model.request.BringRecordRequest;
import cn.vpclub.sinotrans.sailor.server.service.BringRecordService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 南政
 * @className BringRecordClientImpl
 * @desc
 * @since 2018/10/16 15:25
 */
@RestController
public class BringRecordClientImpl implements BringRecordClient {

    @Resource
    private BringRecordService bringRecordService;

    /**
     * 带看记录-分页列表
     *
     * @param param
     * @return
     */
    @Override
    public BaseResponse<List<BringRecord>> getList(@RequestBody BringRecordRequest param) {
        return bringRecordService.getList(param);
    }

    /**
     * 带看记录-带看新增
     *
     * @param bringRecord
     * @return
     */
    @Override
    public BaseResponse<Boolean> save(@RequestBody BringRecord bringRecord) {
        return bringRecordService.save(bringRecord);
    }
}
