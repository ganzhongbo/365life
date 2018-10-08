package cn.vpclub.sinotrans.sailor.server.client;



import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.demo.common.model.core.model.response.PageResponse;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.LifeDicEntity;
import cn.vpclub.sinotrans.sailor.feign.feignClient.LifeDicClient;
import cn.vpclub.sinotrans.sailor.feign.model.request.LifeDicRequest;
import cn.vpclub.sinotrans.sailor.server.service.LifeDicServise;
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
    public BaseResponse saveDic(@RequestBody  LifeDicEntity lifeDicEntity) {
        return lifeDicServise.saveDic(lifeDicEntity);
    }

    @Override
    public BaseResponse updateDic(@RequestBody LifeDicEntity lifeDicEntity) {
        return lifeDicServise.updateDic(lifeDicEntity);
    }

    @Override
    public BaseResponse deleteDic(@RequestBody LifeDicEntity lifeDicEntity) {
        return lifeDicServise.deleteDic(lifeDicEntity);
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
