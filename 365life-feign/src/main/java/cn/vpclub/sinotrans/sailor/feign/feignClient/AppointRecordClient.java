package cn.vpclub.sinotrans.sailor.feign.feignClient;


import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.AppointRecord;
import cn.vpclub.sinotrans.sailor.feign.model.request.AppointRecordRequest;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author 南政
 * @className AppointRecordClient
 * @desc 预约记录client
 * @since 2018/10/10 17:15
 */
@RequestMapping(value = "/appointRecordClient")
public interface AppointRecordClient {

    /**
     * 预约记录-分页列表
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/getList", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    BaseResponse<List<AppointRecord>> getList(AppointRecordRequest param);

    /**
     * 预约信息-预约新增
     *
     * @param appointRecord
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    BaseResponse<Boolean> save(AppointRecord appointRecord);

    /**
     * 预约信息-修改状态
     *
     * @param appointRecord
     * @return
     */
    @RequestMapping(value = "/updateStatus", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    BaseResponse<Boolean> updateStatus(AppointRecord appointRecord);
}
