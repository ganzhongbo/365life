package cn.vpclub.sinotrans.sailor.admin.web;


import cn.vpclub.demo.common.model.core.enums.ReturnCodeEnum;
import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.sinotrans.sailor.admin.service.FollowRecordService;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.FollowRecord;
import cn.vpclub.sinotrans.sailor.feign.model.request.FollowRecordRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 客源/房源跟进记录表 前端控制器
 *
 * @author 南政
 * @since 2018-10-10
 */
@RestController
@RequestMapping("/followRecord")
public class FollowRecordController {

    @Resource
    private FollowRecordService followRecordService;

    /**
     * 跟进记录-分页列表
     *
     * @param param
     * @return
     */
    @PostMapping(value = "/getList")
    public BaseResponse<List<FollowRecord>> getList(@RequestBody FollowRecordRequest param) {
        BaseResponse<List<FollowRecord>> response = new BaseResponse<>();
        if (null == param.getFollowType()) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参跟进类型followType为空");
            return response;
        }
        if (null == param.getSourceId()) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参房源/客源sourceId为空");
            return response;
        }
        return followRecordService.getList(param);
    }


    /**
     * 跟进记录-新增修改
     *
     * @param followRecord
     * @return
     */
    @PostMapping(value = "/saveOrUpdate")
    public BaseResponse<Boolean> saveOrUpdate(@RequestBody FollowRecord followRecord) {
        BaseResponse<Boolean> response = new BaseResponse<>();
        if (null == followRecord.getSourceId()) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参房源/客源主键sourceId为空");
            return response;
        }
        if (null == followRecord.getFollowType()) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参跟进类型followType为空");
            return response;
        }
        if (StringUtils.isBlank(followRecord.getDescription())) {
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("入参跟进描述description为空");
            return response;
        }
        return followRecordService.saveOrUpdate(followRecord);
    }

}

