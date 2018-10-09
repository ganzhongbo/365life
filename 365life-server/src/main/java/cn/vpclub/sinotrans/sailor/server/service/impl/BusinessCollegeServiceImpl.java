package cn.vpclub.sinotrans.sailor.server.service.impl;

import cn.vpclub.demo.common.model.core.enums.ReturnCodeEnum;
import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.demo.common.model.core.model.response.PageResponse;
import cn.vpclub.demo.common.model.utils.common.JsonUtil;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.BusinessCollegeEntity;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.LifeDicEntity;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.UserResouceEntity;
import cn.vpclub.sinotrans.sailor.feign.model.request.BusinessCollegeRequest;
import cn.vpclub.sinotrans.sailor.feign.model.request.LifeDicRequest;
import cn.vpclub.sinotrans.sailor.server.dao.BusinessCollegeDao;
import cn.vpclub.sinotrans.sailor.server.dao.LifeDicDao;
import cn.vpclub.sinotrans.sailor.server.dao.UserResouceDao;
import cn.vpclub.sinotrans.sailor.server.service.BusinessCollegeServise;
import cn.vpclub.sinotrans.sailor.server.service.LifeDicServise;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chentao on 2018/7/19.
 */
@Service
@Slf4j
public class BusinessCollegeServiceImpl extends ServiceImpl<BusinessCollegeDao, BusinessCollegeEntity> implements BusinessCollegeServise {

    @Resource
    private UserResouceDao userResouceDao;


    @Override
    public BaseResponse<BusinessCollegeEntity> saveBusinessCollege(BusinessCollegeEntity businessCollegeEntity) {
        log.info("保存接口请求数据 {} :",businessCollegeEntity.toString());
        BaseResponse baseResponse = new BaseResponse();

        if(null == businessCollegeEntity){
            baseResponse.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            baseResponse.setMessage("入参实体为空");
            return baseResponse;
        }
        businessCollegeEntity.setCreatedTime(System.currentTimeMillis());
        boolean isright =  this.insert(businessCollegeEntity);
        if(isright){
            baseResponse.setDataInfo(businessCollegeEntity);
            baseResponse.setReturnCode(ReturnCodeEnum.CODE_1000.getCode());
            baseResponse.setMessage("成功");
        }else{
            baseResponse.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
            baseResponse.setMessage("失败");
        }
        return baseResponse;
    }

    @Override
    public BaseResponse<BusinessCollegeEntity> updateBusinessCollege(BusinessCollegeEntity businessCollegeEntity) {
        log.info("修改接口请求数据 {} :",businessCollegeEntity.toString());
        BaseResponse baseResponse = new BaseResponse();

        if(null == businessCollegeEntity){
            baseResponse.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            baseResponse.setMessage("入参实体为空");
            return baseResponse;
        }
        boolean isright =  this.updateById(businessCollegeEntity);
        if(isright){
            baseResponse.setDataInfo(businessCollegeEntity);
            baseResponse.setReturnCode(ReturnCodeEnum.CODE_1000.getCode());
            baseResponse.setMessage("成功");
        }else{
            baseResponse.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
            baseResponse.setMessage("失败");
        }
        return baseResponse;
    }

    @Override
    public BaseResponse deleteBusinessCollege(BusinessCollegeEntity businessCollegeEntity) {

        log.info("删除接口请求数据 {} :",businessCollegeEntity.toString());
        BaseResponse baseResponse = new BaseResponse();

        if(null == businessCollegeEntity){
            baseResponse.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            baseResponse.setMessage("入参实体为空");
            return baseResponse;
        }
        businessCollegeEntity.setDeleted(2);
        boolean isright =  this.updateById(businessCollegeEntity);
        if(isright){
            baseResponse.setDataInfo(businessCollegeEntity);
            baseResponse.setReturnCode(ReturnCodeEnum.CODE_1000.getCode());
            baseResponse.setMessage("成功");
        }else{
            baseResponse.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
            baseResponse.setMessage("失败");
        }
        return baseResponse;
    }

    @Override
    public BaseResponse<BusinessCollegeEntity> getById(BusinessCollegeEntity businessCollegeDo) {
        log.info("字典修改接口请求数据 {} :",businessCollegeDo.toString());
        BaseResponse baseResponse = new BaseResponse();
        if(null == businessCollegeDo){
            baseResponse.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            baseResponse.setMessage("入参实体为空");
            return baseResponse;
        }
        BusinessCollegeEntity businessCollegeEntity =  this.selectById(businessCollegeDo.getId());
        if(businessCollegeEntity!=null){
            UserResouceEntity userResouceEntity = new UserResouceEntity();
            userResouceEntity.setResouseId(businessCollegeDo.getId());
            List<String> userResouceList =  userResouceDao.selectAll(userResouceEntity);
            businessCollegeEntity.setResouseList(userResouceList);

            baseResponse.setDataInfo(businessCollegeEntity);
            baseResponse.setReturnCode(ReturnCodeEnum.CODE_1000.getCode());
            baseResponse.setMessage("成功");
        }else{
            baseResponse.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
            baseResponse.setMessage("失败");
        }
        return baseResponse;
    }

    /**
     * 分页查询数据字典的数据
     * @param request
     * @return
     */
    @Override
    public PageResponse<BusinessCollegeEntity> businessCollegedatapage(BusinessCollegeRequest request) {
        PageResponse pageResponse = new PageResponse();
        log.info(" 分页查询数据字典的数据请求参数：" + JSONObject.toJSONString(request));
        Page<BusinessCollegeEntity> page = new Page<BusinessCollegeEntity>();
        page.setCurrent(request.getPageNumber() == null ? 1 : request.getPageNumber());
        page.setSize(request.getPageSize() == null ? 10: request.getPageSize());

        //设置查询条件
        Map paramMap=new HashMap();
        paramMap.put("fileName",request.getFileName());//类型
        paramMap.put("createdBy",request.getCreatedBy());//名称
        List<BusinessCollegeEntity> businessCollegeEntityList = baseMapper.selectAllBusinessCollege(page, paramMap);
        if(businessCollegeEntityList!=null&&businessCollegeEntityList.size()>0){
            page.setRecords(businessCollegeEntityList);
            pageResponse.setTotal(page.getTotal());
            pageResponse.setSize(page.getSize());
            pageResponse.setRecords(page.getRecords());
            pageResponse.setReturnCode(ReturnCodeEnum.CODE_1000.getCode());
            pageResponse.setMessage(ReturnCodeEnum.CODE_1000.getValue());

            log.info("分页查询的数据出参:{}", JsonUtil.objectToJson(pageResponse));
        }else{
            pageResponse.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
            pageResponse.setMessage("暂无数据");
        }
        return pageResponse;

    }
}
