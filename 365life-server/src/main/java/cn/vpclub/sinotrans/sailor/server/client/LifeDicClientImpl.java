package cn.vpclub.sinotrans.sailor.server.client;



import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.demo.common.model.core.model.response.PageResponse;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.LifeDicEntity;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.User;
import cn.vpclub.sinotrans.sailor.feign.feignClient.LifeDicClient;
import cn.vpclub.sinotrans.sailor.feign.feignClient.UserClient;
import cn.vpclub.sinotrans.sailor.feign.model.request.LifeDicRequest;
import cn.vpclub.sinotrans.sailor.server.service.LifeDicServise;
import cn.vpclub.sinotrans.sailor.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chentao on 2018/6/6.
 */
@RestController
public class LifeDicClientImpl implements LifeDicClient {

    @Autowired
    private LifeDicServise lifeDicServise;



    @Override
    public BaseResponse save(@RequestBody  LifeDicEntity lifeDicEntity) {
        return lifeDicServise.save(lifeDicEntity);
    }

    @Override
    public BaseResponse update(@RequestBody LifeDicEntity lifeDicEntity) {
        return lifeDicServise.update(lifeDicEntity);
    }

    @Override
    public BaseResponse delete(@RequestBody LifeDicEntity lifeDicEntity) {
        return lifeDicServise.delete(lifeDicEntity);
    }

    @Override
    public BaseResponse<LifeDicEntity> getById(@RequestBody LifeDicEntity lifeDicEntity) {
        return lifeDicServise.getById(lifeDicEntity);
    }

    @Override
    public PageResponse<LifeDicEntity> dicdatapage(@RequestBody LifeDicRequest request) {
        return lifeDicServise.dicdatapage(request);
    }
}
