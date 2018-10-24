package cn.vpclub.sinotrans.sailor.admin.web;

import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.sinotrans.sailor.admin.service.UserLevelService;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.UserLevelEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
@RequestMapping("/userLevel")
public class UserLevelController {

    @Autowired
    private UserLevelService userLevelService;

    /**
     * 保存
     */
    @RequestMapping(value = "/saveUserLevel")
    public BaseResponse saveUserLevel(@RequestBody UserLevelEntity userLevelEntity){
       return  userLevelService.saveUserLevel(userLevelEntity);
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/updateUserLevel")
    public  BaseResponse  updateUserLevel(@RequestBody UserLevelEntity userLevelEntity){
        return  userLevelService.updateUserLevel(userLevelEntity);
    }


    /**
     * 查询
     * @return
     */
    @RequestMapping(value = "/getUserLevel")
    public BaseResponse getUserLevel(){
        return  userLevelService.getUserLevel();
    }





}
