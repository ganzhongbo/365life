package cn.vpclub.sinotrans.sailor.feign.feignClient;

import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.FollowRecord;
import cn.vpclub.sinotrans.sailor.feign.model.request.FollowRecordRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author 南政
 * @className FollowRecordClient
 * @desc 跟进记录client
 * @since 2018/10/10 17:15
 */
@RequestMapping(value = "/followRecordClient")
public interface FollowRecordClient {

    /**
     * 跟进记录-分页列表
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/getList", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    BaseResponse<List<FollowRecord>> getList(@RequestBody FollowRecordRequest param);

    /**
     * 跟进记录-新增修改
     *
     * @param followRecord
     * @return
     */
    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    BaseResponse<Boolean> saveOrUpdate(@RequestBody FollowRecord followRecord);
}
