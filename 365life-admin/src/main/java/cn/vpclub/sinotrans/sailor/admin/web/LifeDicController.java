package cn.vpclub.sinotrans.sailor.admin.web;

import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.demo.common.model.core.model.response.PageResponse;
import cn.vpclub.sinotrans.sailor.admin.service.LifeDicService;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.LifeDicEntity;
import cn.vpclub.sinotrans.sailor.feign.model.request.LifeDicRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * Created by zhanchenggui on 2018/7/13
 */
@RestController
@Slf4j
@RequestMapping("/lifeDic")
public class LifeDicController {
    @Autowired
    private LifeDicService lifeDicService;

    /***
     * 字典类新增方法
     * @param lifeDicEntity
     * @return
     */
    @PostMapping(value = "/saveDic")
    BaseResponse saveDic(@RequestBody LifeDicEntity lifeDicEntity){

     return lifeDicService.saveDic(lifeDicEntity);
    }


    /***
     * 字典类修改方法
     * @param lifeDicEntity
     * @return
     */
    @PostMapping(value = "/updateDic")
    BaseResponse updateDic(@RequestBody LifeDicEntity lifeDicEntity){

        return lifeDicService.updateDic(lifeDicEntity);
    }


    /***
     * 字典类删除方法
     * @param lifeDicEntity
     * @return
     */
    @PostMapping(value = "/deleteDic")
    BaseResponse deleteDic(@RequestBody LifeDicEntity lifeDicEntity){

        return lifeDicService.deleteDic(lifeDicEntity);
    }


    /**
     * 查询详情
     * @param lifeDicEntity
     * @return
     */
    @PostMapping(value = "/getById")
    BaseResponse<LifeDicEntity> getById(@RequestBody LifeDicEntity lifeDicEntity){
        return lifeDicService.getById(lifeDicEntity);
    }


    /***
     * 数据字典数据分页查询
     * @param
     * @return
     */
    @PostMapping(value = "/dicdatapage")
    PageResponse<LifeDicEntity> dicdatapage(@RequestBody LifeDicRequest request){

        return lifeDicService.dicdatapage(request);
    }

    /**
     * 查询详情
     * @param request
     * @return
     */
    @PostMapping(value = "/selectDicByType")
    BaseResponse<List<LifeDicEntity>> selectDicByType(@RequestBody LifeDicRequest request){
        return lifeDicService.selectDicByType(request);
    }

    /**
     * 根据户型图名称,分组(此接口只针对数据字典中的户型图)
     * @param
     * @return
     */
    @PostMapping(value = "/selectGroupName")
    BaseResponse<List<LifeDicEntity>> selectGroupName(){
        return lifeDicService.selectGroupName();
    }

    /**
     * 根据户型图名称查询相关的户型图(此接口只针对数据字典中的户型图)
     * @param request
     * @return
     */
    @PostMapping(value = "/selectDicGroupName")
    BaseResponse<List<LifeDicEntity>> selectDicByName(@RequestBody LifeDicRequest request){
        return lifeDicService.selectDicByName(request);
    }


}
