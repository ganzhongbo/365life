package cn.vpclub.sinotrans.sailor.server.service;

import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.sinotrans.sailor.feign.domain.dto.HouseSourceDTO;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.HouseSource;
import cn.vpclub.sinotrans.sailor.feign.model.request.HouseSourceRequest;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * 房源基本信息表 服务类
 *
 * @author 南政
 * @since 2018-10-15
 */
public interface HouseSourceService extends IService<HouseSource> {

    /**
     * 房源信息-分页列表
     *
     * @param param
     * @return
     */
    BaseResponse<Page<HouseSourceDTO>> getPage(HouseSourceRequest param);

    /**
     * 房源信息-新增修改
     * @param houseSource
     * @return
     */
    BaseResponse<Boolean> saveOrUpdate(HouseSource houseSource);

    /**
     * 房源信息-获取详情
     * @param houseSource
     * @return
     */
    BaseResponse<HouseSourceDTO> getDetail(HouseSource houseSource);
}
