package cn.vpclub.sinotrans.sailor.server.service;

import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.demo.common.model.core.model.response.PageResponse;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.BusinessCollegeEntity;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.LifeDicEntity;
import cn.vpclub.sinotrans.sailor.feign.model.request.BusinessCollegeRequest;
import cn.vpclub.sinotrans.sailor.feign.model.request.LifeDicRequest;
import com.baomidou.mybatisplus.service.IService;

public interface BusinessCollegeServise extends IService<BusinessCollegeEntity> {

    /***
     * 字典类新增方法
     * @param businessCollegeEntity
     * @return
     */
    BaseResponse saveBusinessCollege(BusinessCollegeEntity businessCollegeEntity);


    /***
     * 字典类修改方法
     * @param businessCollegeEntity
     * @return
     */
    BaseResponse updateBusinessCollege(BusinessCollegeEntity businessCollegeEntity);


    /***
     * 字典类删除方法
     * @param businessCollegeEntity
     * @return
     */
    BaseResponse deleteBusinessCollege(BusinessCollegeEntity businessCollegeEntity);


    /**
     * 查询详情
     * @param businessCollegeEntity
     * @return
     */
    BaseResponse<BusinessCollegeEntity> getById(BusinessCollegeEntity businessCollegeEntity);


    /***
     * 数据字典数据分页查询
     * @param request
     * @return
     */
    PageResponse<BusinessCollegeEntity> businessCollegedatapage(BusinessCollegeRequest request);


}
