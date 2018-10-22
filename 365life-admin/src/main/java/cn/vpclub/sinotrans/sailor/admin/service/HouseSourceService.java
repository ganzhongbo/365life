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
     * @param houseSource
     * @return
     */
    public BaseResponse<Boolean> saveOrUpdate(HouseSource houseSource) {
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
        if (null == houseSource.getId()) {
            houseSource.setCreatedBy(Long.valueOf(map.get("userId").toString()));
            houseSource.setUserId(Long.valueOf(map.get("superior").toString()));
            houseSource.setCreatedName(map.get("realName").toString());
            houseSource.setCreatedTime(current);
        }
        houseSource.setUpdatedBy(Long.valueOf(map.get("userId").toString()));
        houseSource.setUpdatedTime(current);
        return houseSourceServerClient.saveOrUpdate(houseSource);
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
}
