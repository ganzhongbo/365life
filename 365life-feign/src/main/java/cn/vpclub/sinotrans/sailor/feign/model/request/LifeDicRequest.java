package cn.vpclub.sinotrans.sailor.feign.model.request;

import cn.vpclub.demo.common.model.core.model.request.PageBaseSearchParam;
import lombok.Data;

@Data
public class LifeDicRequest extends PageBaseSearchParam {

    private Long id;
    private String dicName ;//字典名称
    private String dicValue ;//字典值
    private String dicCode ;//字典编码
    private String dicType;//字典类型1:商圈2:物业合作中心3:房屋户型
    private String dicStatus;//是否启用0启用1不启用
    private String remarks ;//备注
    private Long createdTime;//创建时间
    private String createdBy;//创建人
    private Long updatedTime;//更新人
    private String updatedBy;//更新人
    private Integer deleted;//删除标识(1:在线; 2:删除)


}
