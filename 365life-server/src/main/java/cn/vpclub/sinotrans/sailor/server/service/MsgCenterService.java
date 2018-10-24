package cn.vpclub.sinotrans.sailor.server.service;


import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.MsgCenter;
import cn.vpclub.sinotrans.sailor.feign.model.request.MsgCenterRequest;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * 消息中心表 服务类
 *
 * @author 南政
 * @since 2018-10-22
 */
public interface MsgCenterService extends IService<MsgCenter> {

    /**
     * 消息中心-分页列表
     *
     * @param param
     * @return
     */
    BaseResponse<Page<MsgCenter>> getPage(MsgCenterRequest param);

    /**
     * 消息中心-新增消息
     *
     * @param msgCenter
     * @return
     */
    BaseResponse<Boolean> save(MsgCenter msgCenter);
}
