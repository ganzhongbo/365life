package cn.vpclub.sinotrans.sailor.feign.feignClient;


import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.sinotrans.sailor.feign.domain.dto.PassengerSourceDTO;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.PassengerSource;
import cn.vpclub.sinotrans.sailor.feign.model.request.PassengerSourceRequest;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * @author 南政
 * @fileName PassengerSourceClient.java
 * @desc 客源信息client类
 * @since 2018/10/10 11:14
 */
@RequestMapping("/passengerSourceClient")
public interface PassengerSourceClient {

    /**
     * 客源信息-分页列表
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/getPage", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    BaseResponse<Page<PassengerSource>> getPage(PassengerSourceRequest param);

    /**
     * 客源信息-新增修改
     *
     * @param entity
     * @return
     */
    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    BaseResponse<Boolean> saveOrUpdate(PassengerSource entity);

    /**
     * 客源信息-获取详情
     *
     * @param entity
     * @return
     */
    @RequestMapping(value = "/getDetail", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    BaseResponse<PassengerSourceDTO> getDetail(PassengerSource entity);
}
