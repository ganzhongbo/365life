package cn.vpclub.sinotrans.sailor.server.client;



import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.demo.common.model.core.model.response.PageResponse;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.BusinessCollegeEntity;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.LifeDicEntity;
import cn.vpclub.sinotrans.sailor.feign.feignClient.BusinessCollegeClient;
import cn.vpclub.sinotrans.sailor.feign.feignClient.LifeDicClient;
import cn.vpclub.sinotrans.sailor.feign.model.request.BusinessCollegeRequest;
import cn.vpclub.sinotrans.sailor.feign.model.request.LifeDicRequest;
import cn.vpclub.sinotrans.sailor.server.service.BusinessCollegeServise;
import cn.vpclub.sinotrans.sailor.server.service.LifeDicServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chentao on 2018/6/6.
 */
@RestController
public class BusinessCollegeClientImpl implements BusinessCollegeClient {

    @Autowired
    private BusinessCollegeServise businessCollegeServise;



    @Override
    public BaseResponse saveBusinessCollege(@RequestBody BusinessCollegeEntity businessCollegeEntity) {
        return businessCollegeServise.saveBusinessCollege(businessCollegeEntity);
    }

    @Override
    public BaseResponse updateBusinessCollege(@RequestBody BusinessCollegeEntity businessCollegeEntity) {
        return businessCollegeServise.updateBusinessCollege(businessCollegeEntity);
    }

    @Override
    public BaseResponse deleteBusinessCollege(@RequestBody BusinessCollegeEntity businessCollegeEntity) {
        return businessCollegeServise.deleteBusinessCollege(businessCollegeEntity);
    }

    @Override
    public BaseResponse<BusinessCollegeEntity> getById(@RequestBody BusinessCollegeEntity businessCollegeEntity) {
        return businessCollegeServise.getById(businessCollegeEntity);
    }

    @Override
    public PageResponse<BusinessCollegeEntity> businessCollegedatapage(@RequestBody BusinessCollegeRequest request) {
        return businessCollegeServise.businessCollegedatapage(request);
    }
}
