package cn.vpclub.sinotrans.sailor.feign.feignClient;


import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.demo.common.model.core.model.response.PageResponse;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.LifeDicEntity;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.User;
import cn.vpclub.sinotrans.sailor.feign.model.request.LifeDicRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by chentao on 2018/6/6.
 */
@RequestMapping("/userclient")
public interface LifeDicClient {


    /***
     * 字典类新增方法
     * @param lifeDicEntity
     * @return
     */
    @RequestMapping(value = "/saveDic",  method = RequestMethod.POST ,consumes = MediaType.APPLICATION_JSON_VALUE)
    BaseResponse saveDic(@RequestBody LifeDicEntity lifeDicEntity);


    /***
     * 字典类修改方法
     * @param lifeDicEntity
     * @return
     */
    @RequestMapping(value = "/updateDic",  method = RequestMethod.POST ,consumes = MediaType.APPLICATION_JSON_VALUE)
    BaseResponse updateDic(@RequestBody LifeDicEntity lifeDicEntity);


    /***
     * 字典类删除方法
     * @param lifeDicEntity
     * @return
     */
    @RequestMapping(value = "/deleteDic",  method = RequestMethod.POST ,consumes = MediaType.APPLICATION_JSON_VALUE)
    BaseResponse deleteDic(@RequestBody LifeDicEntity lifeDicEntity);


    /**
     * 查询详情
     * @param lifeDicEntity
     * @return
     */
    @RequestMapping(value = "/getById",  method = RequestMethod.POST ,consumes = MediaType.APPLICATION_JSON_VALUE)
    BaseResponse<LifeDicEntity> getById(@RequestBody LifeDicEntity lifeDicEntity);


    /***
     * 数据字典数据分页查询
     * @param
     * @return
     */
    @RequestMapping(value = "/dicdatapage",  method = RequestMethod.POST ,consumes = MediaType.APPLICATION_JSON_VALUE)
    PageResponse<LifeDicEntity> dicdatapage(@RequestBody LifeDicRequest request);


}
