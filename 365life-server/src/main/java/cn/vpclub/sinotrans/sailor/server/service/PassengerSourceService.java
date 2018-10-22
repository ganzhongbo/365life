package cn.vpclub.sinotrans.sailor.server.service;


import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.sinotrans.sailor.feign.domain.dto.PassengerSourceDTO;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.PassengerSource;
import cn.vpclub.sinotrans.sailor.feign.model.request.PassengerSourceRequest;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * @author 南政
 * @fileName PassengerSourceService.java
 * @desc 客源信息记录表 服务类
 * @since 2018/10/10 11:07
 */
public interface PassengerSourceService extends IService<PassengerSource> {

    /**
     * 客源信息-分页列表
     *
     * @param param
     * @return
     */
    BaseResponse<Page<PassengerSource>> getPage(PassengerSourceRequest param);

    /**
     * 客源信息-新增修改
     *
     * @param entity
     * @return
     */
    BaseResponse<Boolean> saveOrUpdate(PassengerSource entity);

    /**
     * 客源信息-获取详情
     *
     * @param entity
     * @return
     */
    BaseResponse<PassengerSourceDTO> getDetail(PassengerSource entity);
}
