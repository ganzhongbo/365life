package cn.vpclub.sinotrans.sailor.feign.domain.dto;

import cn.vpclub.sinotrans.sailor.feign.domain.entity.FollowRecord;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.PassengerSource;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.User;
import lombok.Data;

import java.util.List;

/**
 * @author 南政
 * @className PassengerSourceDTO
 * @desc
 * @since 2018/10/10 15:48
 */
@Data
public class PassengerSourceDTO extends PassengerSource {

    /**
     * 客源经纪人信息
     */
    private User user;

    /**
     * 跟进记录分页对象
     */
    private List<FollowRecord> followRecords;

}
