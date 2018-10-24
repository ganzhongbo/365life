package cn.vpclub.sinotrans.sailor.feign.domain.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;

@TableName("user_level")
@Data
public class UserLevelEntity implements Serializable {

    @TableId(value = "id",type = IdType.ID_WORKER)
    private Long id;
    private Integer personRequire ;//人员要求(合伙人)
    private String performanceRequire ;//业绩要求(合伙人)
    private Integer personRequireCom ;//人员要求(代理公司)
    private String performanceRequireCom ;//业绩要求(代理公司)

}
