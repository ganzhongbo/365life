package cn.vpclub.sinotrans.sailor.server.service.impl;

import cn.vpclub.demo.common.model.core.enums.ReturnCodeEnum;
import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.demo.common.model.core.model.response.PageResponse;
import cn.vpclub.demo.common.model.utils.common.JsonUtil;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.LifeDicEntity;
import cn.vpclub.sinotrans.sailor.feign.model.request.LifeDicRequest;
import cn.vpclub.sinotrans.sailor.server.dao.LifeDicDao;
import cn.vpclub.sinotrans.sailor.server.service.LifeDicServise;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
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
public class LifeDicServiceImpl extends ServiceImpl<LifeDicDao, LifeDicEntity> implements LifeDicServise {

    @Resource
    private  LifeDicDao lifeDicDao;

    @Override
    public BaseResponse saveDic(LifeDicEntity lifeDicEntity) {
        log.info("字典保存接口请求数据 {} :",lifeDicEntity.toString());
        BaseResponse baseResponse = new BaseResponse();

        if(null == lifeDicEntity){
            baseResponse.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            baseResponse.setMessage("入参实体为空");
            return baseResponse;
        }
        lifeDicEntity.setCreatedTime(System.currentTimeMillis());
        boolean isright =  this.insert(lifeDicEntity);
        if(isright){
            baseResponse.setDataInfo(lifeDicEntity);
            baseResponse.setReturnCode(ReturnCodeEnum.CODE_1000.getCode());
            baseResponse.setMessage("成功");
        }else{
            baseResponse.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
            baseResponse.setMessage("失败");
        }
        return baseResponse;
    }

    @Override
    public BaseResponse updateDic(LifeDicEntity lifeDicEntity) {
        log.info("字典修改接口请求数据 {} :",lifeDicEntity.toString());
        BaseResponse baseResponse = new BaseResponse();

        if(null == lifeDicEntity){
            baseResponse.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            baseResponse.setMessage("入参实体为空");
            return baseResponse;
        }
        boolean isright =  this.updateById(lifeDicEntity);
        if(isright){
            baseResponse.setDataInfo(lifeDicEntity);
            baseResponse.setReturnCode(ReturnCodeEnum.CODE_1000.getCode());
            baseResponse.setMessage("成功");
        }else{
            baseResponse.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
            baseResponse.setMessage("失败");
        }
        return baseResponse;
    }

    @Override
    public BaseResponse deleteDic(LifeDicEntity lifeDicEntity) {

        log.info("字典删除接口请求数据 {} :",lifeDicEntity.toString());
        BaseResponse baseResponse = new BaseResponse();

        if(null == lifeDicEntity){
            baseResponse.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            baseResponse.setMessage("入参实体为空");
            return baseResponse;
        }
        lifeDicEntity.setDeleted(2);
        boolean isright =  this.updateById(lifeDicEntity);
        if(isright){
            baseResponse.setDataInfo(lifeDicEntity);
            baseResponse.setReturnCode(ReturnCodeEnum.CODE_1000.getCode());
            baseResponse.setMessage("成功");
        }else{
            baseResponse.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
            baseResponse.setMessage("失败");
        }
        return baseResponse;
    }

    @Override
    public BaseResponse<LifeDicEntity> getById(LifeDicEntity lifeDicEntity) {
        log.info("字典修改接口请求数据 {} :",lifeDicEntity.toString());
        BaseResponse baseResponse = new BaseResponse();
        if(null == lifeDicEntity){
            baseResponse.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            baseResponse.setMessage("入参实体为空");
            return baseResponse;
        }
        LifeDicEntity lifeDicEntityDo =  this.selectById(lifeDicEntity.getId());
        if(lifeDicEntityDo!=null){
            baseResponse.setDataInfo(lifeDicEntityDo);
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
     * @param lifeDicEntity
     * @return
     */
    @Override
    public PageResponse<LifeDicEntity> dicdatapage(LifeDicRequest lifeDicEntity) {
        PageResponse pageResponse = new PageResponse();
        log.info(" 分页查询数据字典的数据请求参数：" + JSONObject.toJSONString(lifeDicEntity));
        Page<LifeDicEntity> page = new Page<LifeDicEntity>();
        page.setCurrent(lifeDicEntity.getPageNumber() == null ? 1 : lifeDicEntity.getPageNumber());
        page.setSize(lifeDicEntity.getPageSize() == null ? 3: lifeDicEntity.getPageSize());

        //设置查询条件
        Map paramMap=new HashMap();
        paramMap.put("dicType",lifeDicEntity.getDicType());//类型
        paramMap.put("dicName",lifeDicEntity.getDicName());//名称
        paramMap.put("dicCode",lifeDicEntity.getDicCode());//编号
        paramMap.put("dicStatus",lifeDicEntity.getDicStatus());//状态
        List<LifeDicEntity> lifeDicEntityList = baseMapper.selectAllDic(page, paramMap);
        if(lifeDicEntityList!=null&&lifeDicEntityList.size()>0){
            page.setRecords(lifeDicEntityList);
            pageResponse.setTotal(page.getTotal());
            pageResponse.setSize(page.getSize());
            pageResponse.setRecords(page.getRecords());
            pageResponse.setReturnCode(ReturnCodeEnum.CODE_1000.getCode());
            pageResponse.setMessage(ReturnCodeEnum.CODE_1000.getValue());

            log.info("分页查询数据字典的数据出参:{}", JsonUtil.objectToJson(pageResponse));
        }else{
            pageResponse.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
            pageResponse.setMessage("暂无数据");
        }
        return pageResponse;

    }

    /**
     * 根据类型查询数据字典数据
     * @param request
     * @return
     */
    @Override
    public BaseResponse<List<LifeDicEntity>> selectDicByType(LifeDicRequest request) {
        log.info(" 分页查询数据字典的数据请求参数：" + JSONObject.toJSONString(request));
        BaseResponse baseResponse = new BaseResponse();
        Map paramMap=new HashMap();
        paramMap.put("dicType",request.getDicType());//类型
        paramMap.put("dicName",request.getDicName());//名称
        paramMap.put("dicCode",request.getDicCode());//编号
        paramMap.put("dicStatus",request.getDicStatus());//状态
        List<LifeDicEntity> lifeDicEntityList = baseMapper.selectDicByType(paramMap);
        if(lifeDicEntityList!=null&&lifeDicEntityList.size()>0){
            baseResponse.setDataInfo(lifeDicEntityList);
            baseResponse.setReturnCode(ReturnCodeEnum.CODE_1000.getCode());
            baseResponse.setMessage("成功");
        }else{
            baseResponse.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
            baseResponse.setMessage("暂无数据");
        }
        return baseResponse;
    }

    /**
     * 根据户型图名称,分组(此接口只针对数据字典中的户型图)
     * @param
     * @return
     */
    @Override
    public BaseResponse<List<LifeDicEntity>> selectGroupName() {
        BaseResponse baseResponse = new BaseResponse();
        List<LifeDicEntity> lifeDicEntityList = baseMapper.selectDicGroupName();
        if(lifeDicEntityList!=null&&lifeDicEntityList.size()>0){
            baseResponse.setDataInfo(lifeDicEntityList);
            baseResponse.setReturnCode(ReturnCodeEnum.CODE_1000.getCode());
            baseResponse.setMessage("成功");
        }else{
            baseResponse.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
            baseResponse.setMessage("暂无数据");
        }
        return baseResponse;
    }
    /**
     * 根据户型图名称查询相关的户型图(此接口只针对数据字典中的户型图)
     * @param request
     * @return
     */
    @Override
    public BaseResponse<List<LifeDicEntity>> selectDicByName(LifeDicRequest request) {
        log.info(" 分页查询数据字典的数据请求参数：" + JSONObject.toJSONString(request));
        BaseResponse baseResponse = new BaseResponse();
        List<LifeDicEntity> lifeDicEntityList = baseMapper.selectDicByName(request);
        if(lifeDicEntityList!=null&&lifeDicEntityList.size()>0){
            baseResponse.setDataInfo(lifeDicEntityList);
            baseResponse.setReturnCode(ReturnCodeEnum.CODE_1000.getCode());
            baseResponse.setMessage("成功");
        }else{
            baseResponse.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
            baseResponse.setMessage("暂无数据");
        }
        return baseResponse;
    }
}
