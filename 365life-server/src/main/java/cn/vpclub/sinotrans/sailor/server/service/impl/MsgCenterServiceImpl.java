package cn.vpclub.sinotrans.sailor.server.service.impl;


import cn.vpclub.demo.common.model.core.enums.ReturnCodeEnum;
import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.sinotrans.sailor.feign.domain.constants.MsgCenterConstant;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.MsgCenter;
import cn.vpclub.sinotrans.sailor.feign.model.request.MsgCenterRequest;
import cn.vpclub.sinotrans.sailor.server.dao.MsgCenterDao;
import cn.vpclub.sinotrans.sailor.server.service.MsgCenterService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 消息中心表 服务实现类
 *
 * @author 南政
 * @since 2018-10-22
 */
@Service
public class MsgCenterServiceImpl extends ServiceImpl<MsgCenterDao, MsgCenter> implements MsgCenterService {

    /**
     * 消息中心-分页列表
     *
     * @param param
     * @return
     */
    @Override
    public BaseResponse<Page<MsgCenter>> getPage(MsgCenterRequest param) {
        BaseResponse<Page<MsgCenter>> response = new BaseResponse<>();
        EntityWrapper<MsgCenter> wrapper = new EntityWrapper<>();
        //时间倒序
        wrapper.orderBy("created_time", false);
        //消息接受者
        wrapper.eq("receiver_id", param.getReceiverId());
        //消息所属
        wrapper.or("msg_belong={0}", MsgCenterConstant.PUBLIC_MESSAGE);
        Page<MsgCenter> page = new Page<>();
        //设值当前页
        page.setCurrent(null == param.getPageNumber() ? 1 : param.getPageNumber());
        //设值页面大小
        page.setSize(null == param.getPageSize() ? 10 : param.getPageSize());

        page = selectPage(page, wrapper);
        response.setReturnCode(ReturnCodeEnum.CODE_1000.getCode());
        response.setMessage("请求成功");
        response.setDataInfo(page);
        return response;
    }

    /**
     * 消息中心-新增消息
     *
     * @param msgCenter
     * @return
     */
    @Override
    public BaseResponse<Boolean> save(MsgCenter msgCenter) {
        BaseResponse<Boolean> response = new BaseResponse<>();
        msgCenter.setCreatedTime(new Date().getTime());
        msgCenter.setUpdatedTime(new Date().getTime());
        msgCenter.setCreatedBy(msgCenter.getSendId());
        msgCenter.setUpdatedBy(msgCenter.getSendId());
        //执行保存
        boolean success = insert(msgCenter);
        response.setDataInfo(success);
        if (!success) {
            response.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
            response.setMessage("保存失败");
            return response;
        }
        response.setReturnCode(ReturnCodeEnum.CODE_1000.getCode());
        response.setMessage("保存成功");
        return response;
    }
}
