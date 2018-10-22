package cn.vpclub.sinotrans.sailor.feign.feignClient;


import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.BringRecord;
import cn.vpclub.sinotrans.sailor.feign.model.request.BringRecordRequest;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author 南政
 * @className BringRecordClient
 * @desc 带看记录client
 * @since 2018/10/10 17:15
 */
@RequestMapping(value = "/bringRecordClient")
public interface BringRecordClient {

    /**
     * 带看记录-分页列表
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/getList", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    BaseResponse<List<BringRecord>> getList(BringRecordRequest param);

    /**
     * 带看记录-带看新增
     *
     * @param bringRecord
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    BaseResponse<Boolean> save(BringRecord bringRecord);
}
