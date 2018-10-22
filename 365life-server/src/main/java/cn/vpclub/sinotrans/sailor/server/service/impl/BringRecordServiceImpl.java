package cn.vpclub.sinotrans.sailor.server.service.impl;


import cn.vpclub.demo.common.model.core.enums.ReturnCodeEnum;
import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.sinotrans.sailor.feign.domain.constants.HouseSourceConstants;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.BringRecord;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.HouseSource;
import cn.vpclub.sinotrans.sailor.feign.model.request.BringRecordRequest;
import cn.vpclub.sinotrans.sailor.server.dao.BringRecordDao;
import cn.vpclub.sinotrans.sailor.server.service.BringRecordService;
import cn.vpclub.sinotrans.sailor.server.service.HouseSourceService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 房源带看记录表 服务实现类
 *
 * @author 南政
 * @since 2018-10-16
 */
@Service
public class BringRecordServiceImpl extends ServiceImpl<BringRecordDao, BringRecord> implements BringRecordService {

    @Resource
    private HouseSourceService houseSourceService;

    /**
     * 带看记录-分页列表
     *
     * @param param
     * @return
     */
    @Override
    public BaseResponse<List<BringRecord>> getList(BringRecordRequest param) {
        BaseResponse<List<BringRecord>> response = new BaseResponse<>();
        EntityWrapper<BringRecord> wrapper = new EntityWrapper<>();
        //时间倒序
        wrapper.orderBy("created_time", false);
        //按照房源id查询
        wrapper.eq("source_id", param.getSourceId());
        //查询
        List<BringRecord> list = selectList(wrapper);
        response.setReturnCode(ReturnCodeEnum.CODE_1000.getCode());
        response.setMessage("操作成功");
        response.setDataInfo(list);
        return response;
    }

    /**
     * 带看记录-带看新增
     *
     * @param bringRecord
     * @return
     */
    @Override
    public BaseResponse<Boolean> save(BringRecord bringRecord) {
        BaseResponse<Boolean> response = new BaseResponse<>();
        //先判断是否有此房源
        HouseSource houseSource = houseSourceService.selectById(bringRecord.getSourceId());
        if (null == houseSource) {
            response.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
            response.setMessage("无此房源信息");
            return response;
        }
        //是否实勘
        if (houseSource.getHouseStatus() < HouseSourceConstants.HAVE_SOLID) {
            response.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
            response.setMessage("此房源未实勘");
            return response;
        }
        //执行保存
        boolean success = insert(bringRecord);
        response.setDataInfo(success);
        if (!success) {
            response.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
            response.setMessage("新增失败");
            return response;
        }
        response.setReturnCode(ReturnCodeEnum.CODE_1000.getCode());
        response.setMessage("新增成功");
        return response;
    }
}
