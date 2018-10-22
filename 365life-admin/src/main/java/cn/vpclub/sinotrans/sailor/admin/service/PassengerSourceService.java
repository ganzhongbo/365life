package cn.vpclub.sinotrans.sailor.admin.service;

import cn.vpclub.demo.common.model.core.enums.ReturnCodeEnum;
import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.demo.common.model.utils.common.JsonUtil;
import cn.vpclub.sinotrans.sailor.admin.client.PassengerSourceServerClient;
import cn.vpclub.sinotrans.sailor.feign.domain.dto.PassengerSourceDTO;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.PassengerSource;
import cn.vpclub.sinotrans.sailor.feign.model.request.PassengerSourceRequest;
import com.baomidou.mybatisplus.plugins.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * @author 南政
 * @className PassengerSourceService
 * @desc 客源信息服务类
 * @since 2018/10/10 11:09
 */
@Slf4j
@Service
public class PassengerSourceService extends BaseServicce {

    @Resource
    private PassengerSourceServerClient passengerSourceServerClient;

    /**
     * 客源信息-分页列表
     *
     * @param param
     * @return
     */
    public BaseResponse<Page<PassengerSource>> getPage(PassengerSourceRequest param) {
        return passengerSourceServerClient.getPage(param);
    }

    /**
     * 客源信息-新增修改
     *
     * @param entity
     * @return
     */
    public BaseResponse<Boolean> saveOrUpdate(PassengerSource entity) {
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
        //当前登陆用户id
        long userId = Long.valueOf(map.get("userId").toString());
        if (null == entity.getId()) {
            entity.setCreatedBy(userId);
            entity.setUserId(Long.valueOf(map.get("superior").toString()));
            entity.setCreatedName(map.get("realName").toString());
            entity.setCreatedTime(current);
        }
        entity.setUpdatedBy(userId);
        entity.setUpdatedTime(current);
        return passengerSourceServerClient.saveOrUpdate(entity);
    }

    /**
     * 客源信息-获取详情
     *
     * @param entity
     * @return
     */
    public BaseResponse<PassengerSourceDTO> getDetail(PassengerSource entity) {
        return passengerSourceServerClient.getDetail(entity);
    }

    /**
     * 个人中心-我的客源
     *
     * @return
     */
    public BaseResponse<Page<PassengerSource>> getMyPassenger() {
        BaseResponse<Page<PassengerSource>> response = new BaseResponse<>();
        Map<String, Object> map = JsonUtil.jsonToMap(getUser());
        if (MapUtils.isEmpty(map)) {
            response.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
            response.setMessage("获取登陆用户信息失败");
            return response;
        }
        PassengerSource entity = new PassengerSource();
        entity.setUserId(Long.valueOf(map.get("userId").toString()));

        return null;
    }
}
