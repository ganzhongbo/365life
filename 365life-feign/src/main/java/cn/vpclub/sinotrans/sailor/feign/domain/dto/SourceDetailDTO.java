package cn.vpclub.sinotrans.sailor.feign.domain.dto;

import cn.vpclub.sinotrans.sailor.feign.domain.entity.SourceDetail;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.UserResouceEntity;
import lombok.Data;

import java.util.List;

/**
 * @author 南政
 * @className SourceDetailDTO
 * @desc
 * @since 2018/10/15 17:34
 */
@Data
public class SourceDetailDTO extends SourceDetail {

    /**
     * 房屋照片集合
     */
    private List<UserResouceEntity> houseImg;

    /**
     * 物业交接文件集合
     */
    private List<UserResouceEntity> propertyDelivery;


}
