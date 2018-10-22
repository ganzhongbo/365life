package cn.vpclub.sinotrans.sailor.server.service;

import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.demo.common.model.core.model.response.PageResponse;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.LifeDicEntity;
import cn.vpclub.sinotrans.sailor.feign.model.request.LifeDicRequest;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

public interface LifeDicServise extends IService<LifeDicEntity> {

    /***
     * 字典类新增方法
     * @param lifeDicEntity
     * @return
     */
    BaseResponse saveDic(LifeDicEntity lifeDicEntity);


    /***
     * 字典类修改方法
     * @param lifeDicEntity
     * @return
     */
    BaseResponse updateDic(LifeDicEntity lifeDicEntity);


    /***
     * 字典类删除方法
     * @param lifeDicEntity
     * @return
     */
    BaseResponse deleteDic(LifeDicEntity lifeDicEntity);


    /**
     * 查询详情
     * @param lifeDicEntity
     * @return
     */
    BaseResponse<LifeDicEntity> getById(LifeDicEntity lifeDicEntity);


    /***
     * 数据字典数据分页查询
     * @param
     * @return
     */
    PageResponse<LifeDicEntity> dicdatapage(LifeDicRequest request);

    public BaseResponse<List<LifeDicEntity>> selectDicByType(LifeDicRequest request);

    public BaseResponse<List<LifeDicEntity>> selectGroupName();

    public BaseResponse<List<LifeDicEntity>> selectDicByName(LifeDicRequest request) ;
}
