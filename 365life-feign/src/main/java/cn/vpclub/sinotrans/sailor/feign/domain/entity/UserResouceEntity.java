package cn.vpclub.sinotrans.sailor.feign.domain.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

@TableName("user_resouse")
@Data
public class UserResouceEntity implements Serializable {

    @TableId
    private Long id;
    private Long userId ;//用户主键
    private String resouseUrl ;//资源地址

    private Integer deleted;//删除标记
}
