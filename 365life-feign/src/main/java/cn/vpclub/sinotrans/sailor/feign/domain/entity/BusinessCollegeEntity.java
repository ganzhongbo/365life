package cn.vpclub.sinotrans.sailor.feign.domain.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@TableName("business_college")
@Data
public class BusinessCollegeEntity implements Serializable {

    @TableId(value = "id",type = IdType.ID_WORKER)
    private Long id;
    private String fileName ;//文件名称
    private String remark ;//备注
    private Integer deleted ;//备注
    private Long createdBy ;//创建人
    private Long createdTime;//创建时间

    private List<String>  resouseList ;//上传的文件集合


}
