package cn.vpclub.sinotrans.sailor.admin.service;


import cn.vpclub.demo.common.model.core.enums.ReturnCodeEnum;
import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.demo.common.model.utils.common.JsonUtil;
import cn.vpclub.sinotrans.sailor.admin.client.HouseSourceServerClient;
import cn.vpclub.sinotrans.sailor.feign.domain.dto.HouseSourceDTO;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.HouseSource;
import cn.vpclub.sinotrans.sailor.feign.model.request.HouseSourceRequest;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * @author 南政
 * @className HouseSourceService
 * @desc
 * @since 2018/10/15 10:37
 */
@Service
public class HouseSourceService extends BaseServicce {

    @Resource
    private HouseSourceServerClient houseSourceServerClient;


    /**
     * 房源信息-分页列表
     *
     * @param param
     * @return
     */
    public BaseResponse<Page<HouseSourceDTO>> getPage(HouseSourceRequest param) {
        return houseSourceServerClient.getPage(param);
    }

    /**
     * 房源信息-新增修改
     *
     * @param houseSourceDTO
     * @return
     */
    public BaseResponse<Boolean> saveOrUpdate(HouseSourceDTO houseSourceDTO) {
        BaseResponse<Boolean> response = new BaseResponse<>();
        //从缓存中获取用户信息
        Map<String, Object> map = JsonUtil.jsonToMap(getUser());
        if (MapUtils.isEmpty(map)) {
            response.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
            response.setMessage("获取登陆用户信息失败");
            return response;
        }
        //当前时间戳
        long current = new Date().getTime();
        if (null == houseSourceDTO.getId()) {
            houseSourceDTO.setUserName(null == map.get("agentName") ? null : map.get("agentName").toString());
            houseSourceDTO.setCreatedBy(Long.valueOf(map.get("userId").toString()));
            houseSourceDTO.setUserId(null == map.get("agentId") ? null : Long.valueOf(map.get("agentId").toString()));
            houseSourceDTO.setCreatedName(map.get("realName").toString());
            houseSourceDTO.setCreatedTime(current);
            houseSourceDTO.setHeadImg(null == map.get("headImg") ? "无" : map.get("headImg").toString());
        }
        houseSourceDTO.setUpdatedBy(Long.valueOf(map.get("userId").toString()));
        houseSourceDTO.setUpdatedTime(current);
        return houseSourceServerClient.saveOrUpdate(houseSourceDTO);
    }

    /**
     * 房源详情-获取详情
     *
     * @param houseSource
     * @return
     */
    public BaseResponse<HouseSourceDTO> getDetail(HouseSource houseSource) {
        return houseSourceServerClient.getDetail(houseSource);
    }

    /**
     * 房源信息-房源抢单
     *
     * @param param
     * @return
     */
    public BaseResponse<Boolean> grabSource(HouseSourceRequest param) {
        BaseResponse<Boolean> response = new BaseResponse<>();
        //从缓存中获取用户信息
        Map<String, Object> map = JsonUtil.jsonToMap(getUser());
        if (MapUtils.isEmpty(map)) {
            response.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
            response.setMessage("获取登陆用户信息失败");
            return response;
        }
        param.setUserId(Long.valueOf(map.get("userId").toString()));
        param.setUserName(map.get("realName").toString());
        return houseSourceServerClient.grabSource(param);
    }
}
