package cn.vpclub.sinotrans.sailor.feign.feignClient;

import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.sinotrans.sailor.feign.domain.dto.HouseSourceDTO;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.HouseSource;
import cn.vpclub.sinotrans.sailor.feign.model.request.HouseSourceRequest;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author 南政
 * @className HouseSourceClient
 * @desc
 * @since 2018/10/15 10:40
 */
@RequestMapping(value = "/houseSourceClient")
public interface HouseSourceClient {

    /**
     * 房源信息-分页列表
     * @param param
     * @return
     */
    @RequestMapping(value = "/getPage", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    BaseResponse<Page<HouseSourceDTO>> getPage(HouseSourceRequest param);

    /**
     * 房源信息-新增修改
     * @param houseSource
     * @return
     */
    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    BaseResponse<Boolean> saveOrUpdate(HouseSource houseSource);

    /**
     * 房源信息-获取详情
     * @param houseSource
     * @return
     */
    @RequestMapping(value = "/getDetail", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    BaseResponse<HouseSourceDTO> getDetail(HouseSource houseSource);
}
