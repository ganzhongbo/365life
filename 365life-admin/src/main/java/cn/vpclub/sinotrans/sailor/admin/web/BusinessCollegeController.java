package cn.vpclub.sinotrans.sailor.admin.web;

import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.demo.common.model.core.model.response.PageResponse;
import cn.vpclub.sinotrans.sailor.admin.service.BusinessCollegeService;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.BusinessCollegeEntity;
import cn.vpclub.sinotrans.sailor.feign.model.request.BusinessCollegeRequest;
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
@RequestMapping("/businessCollege")
public class BusinessCollegeController {
    @Autowired
    private BusinessCollegeService businessCollegeService;

    /***
     * 新增方法
     * @param businessCollegeEntity
     * @return
     */
    @PostMapping(value = "/saveBusinessCollege")
    BaseResponse saveBusinessCollege(@RequestBody BusinessCollegeEntity businessCollegeEntity){

     return businessCollegeService.saveBusinessCollege(businessCollegeEntity);
    }


    /***
     * 修改方法
     * @param
     * @return
     */
    @PostMapping(value = "/updateBusinessCollege")
    BaseResponse updateBusinessCollege(@RequestBody BusinessCollegeEntity businessCollegeEntity){

        return businessCollegeService.updateBusinessCollege(businessCollegeEntity);
    }


    /***
     * 删除方法
     * @param
     * @return
     */
    @PostMapping(value = "/deleteBusinessCollege")
    BaseResponse deleteBusinessCollege(@RequestBody BusinessCollegeEntity businessCollegeEntity){

        return businessCollegeService.deleteBusinessCollege(businessCollegeEntity);
    }


    /**
     * 查询详情
     * @param
     * @return
     */
    @PostMapping(value = "/getById")
    BaseResponse<BusinessCollegeEntity> getById(@RequestBody BusinessCollegeEntity businessCollegeEntity){
        return businessCollegeService.getById(businessCollegeEntity);
    }


    /***
     * 数据分页查询
     * @param
     * @return
     */
    @PostMapping(value = "/businessCollegedatapage")
    PageResponse<BusinessCollegeEntity> businessCollegedatapage(@RequestBody BusinessCollegeRequest request){

        return businessCollegeService.businessCollegedatapage(request);
    }



}
