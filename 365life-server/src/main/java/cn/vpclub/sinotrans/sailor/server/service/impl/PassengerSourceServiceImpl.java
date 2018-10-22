package cn.vpclub.sinotrans.sailor.server.service.impl;


import cn.vpclub.demo.common.model.core.enums.ReturnCodeEnum;
import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.sinotrans.sailor.feign.domain.constants.FollowRecordConstant;
import cn.vpclub.sinotrans.sailor.feign.domain.dto.PassengerSourceDTO;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.FollowRecord;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.PassengerSource;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.User;
import cn.vpclub.sinotrans.sailor.feign.model.request.FollowRecordRequest;
import cn.vpclub.sinotrans.sailor.feign.model.request.PassengerSourceRequest;
import cn.vpclub.sinotrans.sailor.server.dao.PassengerSourceDao;
import cn.vpclub.sinotrans.sailor.server.service.FollowRecordService;
import cn.vpclub.sinotrans.sailor.server.service.PassengerSourceService;
import cn.vpclub.sinotrans.sailor.server.service.UserService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 南政
 * @fileName PassengerSourceServiceImpl.java
 * @desc 客源信息记录表 服务实现类
 * @since 2018/10/10 11:07
 */
@Service
public class PassengerSourceServiceImpl extends ServiceImpl<PassengerSourceDao, PassengerSource> implements PassengerSourceService {

    @Resource
    private UserService userService;

    @Resource
    private FollowRecordService followRecordService;

    /**
     * 客源信息-分页列表
     *
     * @param param
     * @return
     */
    @Override
    public BaseResponse<Page<PassengerSource>> getPage(PassengerSourceRequest param) {
        BaseResponse<Page<PassengerSource>> response = new BaseResponse<>();

        EntityWrapper<PassengerSource> wrapper = new EntityWrapper<>();
        //时间倒序
        wrapper.orderBy("created_time", false);
        //搜索条件-需求类型
        wrapper.eq(null != param.getRequireType(), "require_type", param.getRequireType());
        //搜索条件-最低需求预算
        wrapper.ge(null != param.getMinRequireBudget(), "require_budget", param.getMinRequireBudget());
        //搜索条件-最高需求预算
        wrapper.le(null != param.getMaxRequireBudget(), "require_budget", param.getMaxRequireBudget());
        //搜索条件-需求地段
        wrapper.like(StringUtils.isNotBlank(param.getRequireLocation()), "require_location", param.getRequireLocation());

        Page<PassengerSource> page = new Page<>();
        //设值当前页
        page.setCurrent(null == param.getPageNumber() ? 1 : param.getPageNumber());
        //设值当前页面大小
        page.setSize(null == param.getPageSize() ? 10 : param.getPageSize());
        //执行查询
        page = selectPage(page, wrapper);
        response.setReturnCode(ReturnCodeEnum.CODE_1000.getCode());
        response.setMessage(ReturnCodeEnum.CODE_1000.getValue());
        response.setDataInfo(page);
        return response;
    }

    /**
     * 客源信息-新增修改
     *
     * @param entity
     * @return
     */
    @Override
    public BaseResponse<Boolean> saveOrUpdate(PassengerSource entity) {
        BaseResponse<Boolean> response = new BaseResponse<>();

        EntityWrapper<PassengerSource> wrapper = new EntityWrapper<>();
        //查询是否有重复的客源
        wrapper.eq("passenger_name", entity.getPassengerName());
        wrapper.eq("passenger_phone", entity.getPassengerPhone());

        PassengerSource passengerSource = selectOne(wrapper);
        if (null != passengerSource) {
            if (null == entity.getId() || passengerSource.getId().compareTo(entity.getId()) != 0) {
                response.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
                response.setMessage("已存在相同的客源");
                return response;
            }
        }
        //执行新增或修改
        boolean success = insertOrUpdate(entity);
        response.setDataInfo(success);
        if (!success) {
            response.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
            response.setMessage("操作失败");
            return response;
        }
        response.setReturnCode(ReturnCodeEnum.CODE_1000.getCode());
        response.setMessage("操作成功");
        return response;
    }

    /**
     * 客源信息-获取详情
     *
     * @param entity
     * @return
     */
    @Override
    public BaseResponse<PassengerSourceDTO> getDetail(PassengerSource entity) {
        BaseResponse<PassengerSourceDTO> response = new BaseResponse<>();
        //通过id查询详情
        PassengerSource passengerSource = selectById(entity.getId());
        if (null == passengerSource) {
            response.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
            response.setMessage("数据不存在");
            return response;
        }
        PassengerSourceDTO passengerSourceDTO = new PassengerSourceDTO();
        //对象属性拷贝
        BeanUtils.copyProperties(passengerSource, passengerSourceDTO);
        //如果userId不为空，则设置字段不可见并查询出经纪人的相关信息
        if (null != passengerSourceDTO.getUserId()) {
            //通过id查询出用户的信息
            User user = userService.selectById(passengerSourceDTO.getUserId());
            if (null != user) {
                //取出其中需要的信息进行赋值并设值
                User newUser = new User();
                newUser.setUserName(user.getUserName());
                newUser.setTellphone(user.getTellphone());
                passengerSourceDTO.setUser(newUser);
            }
            passengerSourceDTO.setPassengerName(null);
            passengerSourceDTO.setPassengerSex(null);
            passengerSourceDTO.setPassengerPhone(null);
        }

        //查询出客源的跟进信息
        FollowRecordRequest recordRequest = new FollowRecordRequest();
        recordRequest.setFollowType(FollowRecordConstant.PASSENGER_SOURCE);
        recordRequest.setSourceId(entity.getId());
        //查询跟进记录分页列表
        BaseResponse<List<FollowRecord>> baseResponse = followRecordService.getList(recordRequest);

        passengerSourceDTO.setFollowRecords(baseResponse.getDataInfo());

        response.setReturnCode(ReturnCodeEnum.CODE_1000.getCode());
        response.setMessage("获取成功");
        response.setDataInfo(passengerSourceDTO);
        return response;
    }
}
