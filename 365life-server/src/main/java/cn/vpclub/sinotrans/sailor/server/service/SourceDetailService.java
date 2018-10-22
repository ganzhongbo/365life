package cn.vpclub.sinotrans.sailor.server.service;


import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.sinotrans.sailor.feign.domain.dto.SourceDetailDTO;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.SourceDetail;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * 房源实勘详细信息表 服务类
 *
 * @author 南政
 * @since 2018-10-15
 */
public interface SourceDetailService extends IService<SourceDetail> {

    /**
     * 房源实勘信息-新增修改
     *
     * @param sourceDetailDTO
     * @return
     */
    BaseResponse<Boolean> saveOrUpdate(SourceDetailDTO sourceDetailDTO);

    /**
     * 通过房源id获取实勘信息集合（包括图片）
     *
     * @param sourceIds
     * @return
     */
    List<SourceDetailDTO> getDetailByIds(List<Long> sourceIds);
}
