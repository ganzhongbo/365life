package cn.vpclub.sinotrans.sailor.admin.web;

import cn.vpclub.demo.common.model.core.enums.ReturnCodeEnum;
import cn.vpclub.demo.common.model.core.model.response.BackResponseUtil;
import cn.vpclub.demo.common.model.core.model.response.BaseResponse;

import cn.vpclub.sinotrans.sailor.admin.service.UserService;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by zhanchenggui on 2018/7/13
 */
@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     *  根据id查询数据
     * @param userId
     * @return
     */
    @PostMapping(value = "/getUserById")
    public BaseResponse getUserById(@RequestParam("userId") Long userId){
        BaseResponse baseResponse =null;
        User user = userService.selectByUserId(userId);
        baseResponse= BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1000.getCode());
        baseResponse.setDataInfo(user);
        return baseResponse;
    }



}
