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
    private Long resouseId ;//资源关联的主键
    private String resouseUrl ;//资源地址

    private Integer type ;//文件所属类型1:个人的信息2:商学院文件

    private Integer deleted;//删除标记
}
