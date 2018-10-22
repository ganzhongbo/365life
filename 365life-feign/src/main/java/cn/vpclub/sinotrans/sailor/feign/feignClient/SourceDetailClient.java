package cn.vpclub.sinotrans.sailor.feign.feignClient;


import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.sinotrans.sailor.feign.domain.dto.SourceDetailDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * @author 南政
 * @className SourceDetailClient
 * @desc
 * @since 2018/10/15 10:40
 */
@RequestMapping(value = "/sourceDetailClient")
public interface SourceDetailClient {

    /**
     * 房源实勘信息-新增修改
     *
     * @param sourceDetailDTO
     * @return
     */
    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    BaseResponse<Boolean> saveOrUpdate(SourceDetailDTO sourceDetailDTO);
}
