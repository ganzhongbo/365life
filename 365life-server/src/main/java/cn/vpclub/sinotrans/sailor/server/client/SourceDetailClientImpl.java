package cn.vpclub.sinotrans.sailor.server.client;


import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.sinotrans.sailor.feign.domain.dto.SourceDetailDTO;
import cn.vpclub.sinotrans.sailor.feign.feignClient.SourceDetailClient;
import cn.vpclub.sinotrans.sailor.server.service.SourceDetailService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 南政
 * @className SourceDetailClientImpl
 * @desc
 * @since 2018/10/15 10:41
 */
@RestController
public class SourceDetailClientImpl implements SourceDetailClient {

    @Resource
    private SourceDetailService sourceDetailService;

    /**
     * 房源实勘信息-新增修改
     *
     * @param sourceDetailDTO
     * @return
     */
    @Override
    public BaseResponse<Boolean> saveOrUpdate(@RequestBody SourceDetailDTO sourceDetailDTO) {
        return sourceDetailService.saveOrUpdate(sourceDetailDTO);
    }
}
