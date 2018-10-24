package cn.vpclub.sinotrans.sailor.feign.feignClient;

import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.UserLevelEntity;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.UserMenuEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@RequestMapping("/userLevelClient")
public interface UserLevelClient {


    /**
     * 保存
     */
    @RequestMapping(value = "/saveUserLevel",  method = RequestMethod.POST ,consumes = MediaType.APPLICATION_JSON_VALUE)
    public  BaseResponse  saveUserLevel(@RequestBody UserLevelEntity userLevelEntity);

    /**
     * 修改
     */
    @RequestMapping(value = "/updateUserLevel",  method = RequestMethod.POST ,consumes = MediaType.APPLICATION_JSON_VALUE)
    public  BaseResponse  updateUserLevel(@RequestBody UserLevelEntity userLevelEntity);

    /**
     * 查询
     */
    @RequestMapping(value = "/getUserLevel",  method = RequestMethod.POST ,consumes = MediaType.APPLICATION_JSON_VALUE)
    public  BaseResponse  getUserLevel();

}
