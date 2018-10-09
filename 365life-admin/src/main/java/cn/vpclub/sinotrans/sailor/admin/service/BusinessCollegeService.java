package cn.vpclub.sinotrans.sailor.admin.service;

import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.demo.common.model.core.model.response.PageResponse;
import cn.vpclub.sinotrans.sailor.admin.client.BusinessCollegeServerClient;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.BusinessCollegeEntity;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.LifeDicEntity;
import cn.vpclub.sinotrans.sailor.feign.model.request.BusinessCollegeRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by chentao on 2018/6/6.
 */
@Service("businessCollegeService")
@Slf4j
public class BusinessCollegeService {
    @Autowired
    private BusinessCollegeServerClient businessCollegeServerClient;


    /***
     * 新增方法
     * @param businessCollegeEntity
     * @return
     */
   public BaseResponse<BusinessCollegeEntity> saveBusinessCollege(BusinessCollegeEntity businessCollegeEntity){
      return   businessCollegeServerClient.saveBusinessCollege(businessCollegeEntity);
    }


    /***
     * 修改方法
     * @param
     * @return
     */
    public BaseResponse<BusinessCollegeEntity> updateBusinessCollege(BusinessCollegeEntity businessCollegeEntity){
        return businessCollegeServerClient.updateBusinessCollege(businessCollegeEntity);
    }


    /***
     * 删除方法
     * @param
     * @return
     */
    public BaseResponse deleteBusinessCollege(BusinessCollegeEntity businessCollegeEntity){
        return businessCollegeServerClient.deleteBusinessCollege(businessCollegeEntity);
    }
    /**
     * 查询详情
     * @param
     * @return
     */
    public BaseResponse<BusinessCollegeEntity> getById(BusinessCollegeEntity businessCollegeEntity){

        return businessCollegeServerClient.getById(businessCollegeEntity);

    }
    /***
     * 数据分页查询
     * @param
     * @return
     */
    public  PageResponse<BusinessCollegeEntity> businessCollegedatapage(BusinessCollegeRequest request){
        return businessCollegeServerClient.businessCollegedatapage(request);
    }

}
