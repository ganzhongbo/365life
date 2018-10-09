package cn.vpclub.sinotrans.sailor.feign.feignClient;


import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.demo.common.model.core.model.response.PageResponse;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.BusinessCollegeEntity;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.LifeDicEntity;
import cn.vpclub.sinotrans.sailor.feign.model.request.BusinessCollegeRequest;
import cn.vpclub.sinotrans.sailor.feign.model.request.LifeDicRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by chentao on 2018/6/6.
 */
@RequestMapping("/businessCollegeClient")
public interface BusinessCollegeClient {


    /***
     * 字典类新增方法
     * @param
     * @return
     */
    @RequestMapping(value = "/saveBusinessCollege",  method = RequestMethod.POST ,consumes = MediaType.APPLICATION_JSON_VALUE)
    BaseResponse saveBusinessCollege(@RequestBody BusinessCollegeEntity businessCollegeEntity);


    /***
     * 字典类修改方法
     * @param
     * @return
     */
    @RequestMapping(value = "/updateBusinessCollege",  method = RequestMethod.POST ,consumes = MediaType.APPLICATION_JSON_VALUE)
    BaseResponse updateBusinessCollege(@RequestBody BusinessCollegeEntity businessCollegeEntity);


    /***
     * 字典类删除方法
     * @param
     * @return
     */
    @RequestMapping(value = "/deleteBusinessCollege",  method = RequestMethod.POST ,consumes = MediaType.APPLICATION_JSON_VALUE)
    BaseResponse deleteBusinessCollege(@RequestBody BusinessCollegeEntity businessCollegeEntity);


    /**
     * 查询详情
     * @param
     * @return
     */
    @RequestMapping(value = "/getById",  method = RequestMethod.POST ,consumes = MediaType.APPLICATION_JSON_VALUE)
    BaseResponse<BusinessCollegeEntity> getById(@RequestBody BusinessCollegeEntity businessCollegeEntity);


    /***
     * 数据分页查询
     * @param
     * @return
     */
    @RequestMapping(value = "/businessCollegedatapage",  method = RequestMethod.POST ,consumes = MediaType.APPLICATION_JSON_VALUE)
    PageResponse<BusinessCollegeEntity> businessCollegedatapage(@RequestBody BusinessCollegeRequest request);


}
