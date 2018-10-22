package cn.vpclub.sinotrans.sailor.admin.service;

import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.demo.common.model.core.model.response.PageResponse;
import cn.vpclub.sinotrans.sailor.admin.client.LifeDicServerClient;
import cn.vpclub.sinotrans.sailor.admin.client.UserServerClient;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.LifeDicEntity;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.User;
import cn.vpclub.sinotrans.sailor.feign.model.request.LifeDicRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Created by chentao on 2018/6/6.
 */
@Service("lifeDicService")
@Slf4j
public class LifeDicService {
    @Autowired
    private LifeDicServerClient lifeDicServerClient;


    /***
     * 字典类新增方法
     * @param lifeDicEntity
     * @return
     */
   public BaseResponse saveDic(LifeDicEntity lifeDicEntity){
      return   lifeDicServerClient.saveDic(lifeDicEntity);
    }


    /***
     * 字典类修改方法
     * @param lifeDicEntity
     * @return
     */
    public BaseResponse updateDic(LifeDicEntity lifeDicEntity){
        return lifeDicServerClient.updateDic(lifeDicEntity);
    }


    /***
     * 字典类删除方法
     * @param lifeDicEntity
     * @return
     */
    public BaseResponse deleteDic(LifeDicEntity lifeDicEntity){
        return lifeDicServerClient.deleteDic(lifeDicEntity);
    }


    /**
     * 查询详情
     * @param lifeDicEntity
     * @return
     */
    public BaseResponse<LifeDicEntity> getById(LifeDicEntity lifeDicEntity){

        return lifeDicServerClient.getById(lifeDicEntity);

    }


    /***
     * 数据字典数据分页查询
     * @param
     * @return
     */
    public  PageResponse<LifeDicEntity> dicdatapage(LifeDicRequest request){
        return lifeDicServerClient.dicdatapage(request);
    }


    public BaseResponse<List<LifeDicEntity>> selectDicByType(LifeDicRequest request){
        return lifeDicServerClient.selectDicByType(request);
    }
    /**
     * 根据户型图名称,分组(此接口只针对数据字典中的户型图)
     * @param
     * @return
     */
    public BaseResponse<List<LifeDicEntity>> selectGroupName(){
        return lifeDicServerClient.selectGroupName();
    }

    /**
     * 根据户型图名称查询相关的户型图(此接口只针对数据字典中的户型图)
     * @param request
     * @return
     */
    public BaseResponse<List<LifeDicEntity>> selectDicByName( LifeDicRequest request){
        return lifeDicServerClient.selectDicByName(request);

    }


}
