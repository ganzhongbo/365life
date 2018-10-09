package cn.vpclub.sinotrans.sailor.feign.model.request;

import cn.vpclub.demo.common.model.core.model.request.PageBaseSearchParam;
import com.baomidou.mybatisplus.annotations.TableId;
import lombok.Data;

@Data
public class BusinessCollegeRequest extends PageBaseSearchParam {

    private Long id;
    private String fileName ;//文件名称
    private String remark ;//备注
    private Integer deleted ;//备注
    private Long createdBy ;//创建人
    private Long createdTime;//创建时间


}
