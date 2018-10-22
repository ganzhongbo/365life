package cn.vpclub.sinotrans.sailor.admin.service;


import cn.vpclub.demo.common.model.core.enums.ReturnCodeEnum;
import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.demo.common.model.utils.common.JsonUtil;
import cn.vpclub.sinotrans.sailor.admin.client.SourceDetailServerClient;
import cn.vpclub.sinotrans.sailor.feign.domain.dto.SourceDetailDTO;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * @author 南政
 * @className SourceDetailService
 * @desc
 * @since 2018/10/15 10:37
 */
@Service
public class SourceDetailService extends BaseServicce {

    @Resource
    private SourceDetailServerClient sourceDetailServerClient;

    /**
     * 房源实勘信息-新增修改
     *
     * @param sourceDetailDTO
     * @return
     */
    public BaseResponse<Boolean> saveOrUpdate(SourceDetailDTO sourceDetailDTO) {
        BaseResponse<Boolean> response = new BaseResponse<>();
        Map<String, Object> map = JsonUtil.jsonToMap(getUser());
        if (MapUtils.isEmpty(map)) {
            response.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
            response.setMessage("获取用户信息失败");
            return response;
        }
        if (null == sourceDetailDTO.getId()) {
            sourceDetailDTO.setCreatedName(map.get("realName").toString());
            sourceDetailDTO.setCreatedBy(Long.valueOf(map.get("userId").toString()));
            sourceDetailDTO.setCreatedTime(new Date().getTime());
        }
        sourceDetailDTO.setUpdatedBy(Long.valueOf(map.get("userId").toString()));
        sourceDetailDTO.setUpdatedTime(new Date().getTime());
        return sourceDetailServerClient.saveOrUpdate(sourceDetailDTO);
    }
}
