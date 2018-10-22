package cn.vpclub.sinotrans.sailor.server.client;

import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.sinotrans.sailor.feign.domain.dto.HouseSourceDTO;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.HouseSource;
import cn.vpclub.sinotrans.sailor.feign.feignClient.HouseSourceClient;
import cn.vpclub.sinotrans.sailor.feign.model.request.HouseSourceRequest;
import cn.vpclub.sinotrans.sailor.server.service.HouseSourceService;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 南政
 * @className HouseSourceClientImple
 * @desc
 * @since 2018/10/15 10:41
 */
@RestController
public class HouseSourceClientImpl implements HouseSourceClient {

    @Resource
    private HouseSourceService houseSourceService;

    /**
     * 房源信息-分页列表
     *
     * @param param
     * @return
     */
    @Override
    public BaseResponse<Page<HouseSourceDTO>> getPage(@RequestBody HouseSourceRequest param) {
        return houseSourceService.getPage(param);
    }

    /**
     * 房源信息-新增修改
     *
     * @param houseSource
     * @return
     */
    @Override
    public BaseResponse<Boolean> saveOrUpdate(@RequestBody HouseSource houseSource) {
        return houseSourceService.saveOrUpdate(houseSource);
    }

    /**
     * 房源信息-获取详情
     * @param houseSource
     * @return
     */
    @Override
    public BaseResponse<HouseSourceDTO> getDetail(@RequestBody HouseSource houseSource) {
        return houseSourceService.getDetail(houseSource);
    }
}
