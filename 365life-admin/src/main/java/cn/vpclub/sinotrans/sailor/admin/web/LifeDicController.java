package cn.vpclub.sinotrans.sailor.admin.web;

import cn.vpclub.demo.common.model.core.enums.ReturnCodeEnum;
import cn.vpclub.demo.common.model.core.model.response.BackResponseUtil;
import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.demo.common.model.core.model.response.PageResponse;
import cn.vpclub.sinotrans.sailor.admin.service.LifeDicService;
import cn.vpclub.sinotrans.sailor.admin.service.UserService;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.LifeDicEntity;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.User;
import cn.vpclub.sinotrans.sailor.feign.model.request.LifeDicRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
    @PostMapping(value = "/save")
    BaseResponse save(@RequestBody LifeDicEntity lifeDicEntity){

     return lifeDicService.save(lifeDicEntity);
    }


    /***
     * 字典类修改方法
     * @param lifeDicEntity
     * @return
     */
    @PostMapping(value = "/update")
    BaseResponse update(@RequestBody LifeDicEntity lifeDicEntity){

        return lifeDicService.update(lifeDicEntity);
    }


    /***
     * 字典类删除方法
     * @param lifeDicEntity
     * @return
     */
    @PostMapping(value = "/delete")
    BaseResponse delete(@RequestBody LifeDicEntity lifeDicEntity){

        return lifeDicService.delete(lifeDicEntity);
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



}
