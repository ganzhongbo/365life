package cn.vpclub.sinotrans.sailor.server.client;

import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.sinotrans.sailor.feign.domain.dto.PassengerSourceDTO;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.PassengerSource;
import cn.vpclub.sinotrans.sailor.feign.feignClient.PassengerSourceClient;
import cn.vpclub.sinotrans.sailor.feign.model.request.PassengerSourceRequest;
import cn.vpclub.sinotrans.sailor.server.service.PassengerSourceService;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 南政
 * @className PassengerSourceClientImpl
 * @desc 客源信息client实现类
 * @since 2018/10/10 11:15
 */
@RestController
public class PassengerSourceClientImpl implements PassengerSourceClient {

    @Resource
    private PassengerSourceService passengerSourceService;

    /**
     * 客源信息-分页列表
     * @param param
     * @return
     */
    @Override
    public BaseResponse<Page<PassengerSource>> getPage(@RequestBody PassengerSourceRequest param) {
        return passengerSourceService.getPage(param);
    }

    /**
     * 客源信息-新增修改
     * @param entity
     * @return
     */
    @Override
    public BaseResponse<Boolean> saveOrUpdate(@RequestBody PassengerSource entity) {
        return passengerSourceService.saveOrUpdate(entity);
    }

    /**
     * 客源信息-获取详情
     *
     * @param entity
     * @return
     */
    @Override
    public BaseResponse<PassengerSourceDTO> getDetail(@RequestBody PassengerSource entity) {
        return passengerSourceService.getDetail(entity);
    }
}
