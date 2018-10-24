package cn.vpclub.sinotrans.sailor.server.service.impl;

import cn.vpclub.demo.common.model.core.enums.ReturnCodeEnum;
import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.sinotrans.sailor.feign.domain.constants.FollowRecordConstant;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.FollowRecord;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.HouseSource;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.PassengerSource;
import cn.vpclub.sinotrans.sailor.feign.model.request.FollowRecordRequest;
import cn.vpclub.sinotrans.sailor.server.dao.FollowRecordDao;
import cn.vpclub.sinotrans.sailor.server.service.FollowRecordService;
import cn.vpclub.sinotrans.sailor.server.service.HouseSourceService;
import cn.vpclub.sinotrans.sailor.server.service.PassengerSourceService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 客源/房源跟进记录表 服务实现类
 *
 * @author 南政
 * @since 2018-10-10
 */
@Service
public class FollowRecordServiceImpl extends ServiceImpl<FollowRecordDao, FollowRecord> implements FollowRecordService {

    @Resource
    private HouseSourceService houseSourceService;

    @Resource
    private PassengerSourceService passengerSourceService;

    /**
     * 跟进记录-分页列表
     *
     * @param param
     * @return
     */
    @Override
    public BaseResponse<List<FollowRecord>> getList(FollowRecordRequest param) {
        BaseResponse<List<FollowRecord>> response = new BaseResponse<>();
        EntityWrapper<FollowRecord> wrapper = new EntityWrapper<>();
        //时间倒序
        wrapper.orderBy("created_time", false);
        //查询条件-跟进类型
        wrapper.eq("follow_type", param.getFollowType());
        //查询条件-房源/客源id
        wrapper.eq("source_id", param.getSourceId());

        //执行查询
        List<FollowRecord> followRecords = selectList(wrapper);

        response.setReturnCode(ReturnCodeEnum.CODE_1000.getCode());
        response.setMessage("请求成功");
        response.setDataInfo(followRecords);
        return response;
    }

    /**
     * 跟进记录-新增修改
     *
     * @param followRecord
     * @return
     */
    @Override
    public BaseResponse<Boolean> saveOrUpdate(FollowRecord followRecord) {
        BaseResponse<Boolean> response = new BaseResponse<>();
        if (followRecord.getFollowType() == FollowRecordConstant.HOUSE_SOURCE) {
            HouseSource houseSource = houseSourceService.selectById(followRecord.getSourceId());
            if (null == houseSource) {
                response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
                response.setMessage("跟进房源信息不存在");
                return response;
            }
        } else {
            PassengerSource passengerSource = passengerSourceService.selectById(followRecord.getSourceId());
            if (null == passengerSource) {
                response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
                response.setMessage("跟进客源信息不存在");
                return response;
            }
        }
        //执行保存
        boolean success = insertOrUpdate(followRecord);
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
}
