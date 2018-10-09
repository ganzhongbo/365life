package cn.vpclub.sinotrans.sailor.server.service.impl;

import cn.vpclub.demo.common.model.core.enums.ReturnCodeEnum;
import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.demo.common.model.core.model.response.PageResponse;
import cn.vpclub.demo.common.model.utils.common.Encodes;
import cn.vpclub.demo.common.model.utils.common.JsonUtil;
import cn.vpclub.demo.common.model.utils.common.StringUtil;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.User;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.UserResouceEntity;
import cn.vpclub.sinotrans.sailor.feign.model.request.UserRequest;
import cn.vpclub.sinotrans.sailor.server.dao.UserDao;
import cn.vpclub.sinotrans.sailor.server.service.UserResouceServise;
import cn.vpclub.sinotrans.sailor.server.service.UserService;
import cn.vpclub.wuhan.redis.utils.RedisUtils;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.omg.CORBA.BAD_CONTEXT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by chentao on 2018/7/19.
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

    @Autowired
    private UserResouceServise userResouceServise;

    @Autowired
    private RedisUtils redis;

    @Override
    public BaseResponse saveUser(User user) {
        log.info("用户保存接口请求数据 {} :",user.toString());
        BaseResponse baseResponse = new BaseResponse();

        if(null == user){
            baseResponse.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            baseResponse.setMessage("入参实体为空");
            return baseResponse;
        }
        //校验验证码是否失效（300秒有效）
        String redisCode = "SMS_MSG_SEND:"+"TYPE_"+1+":MOBILE_"+user.getTellphone();
        String smsCode = redis.get(redisCode);
        if(StringUtil.isEmpty(smsCode)){
            baseResponse.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            baseResponse.setMessage("短信验证码已过期，请重新发送");
            log.info("user register baseResponse : {}",JsonUtil.objectToJson(baseResponse));
            return baseResponse;
        }

        if(StringUtil.isEmpty(user.getUserName())){
            baseResponse.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            baseResponse.setMessage("用户名不能为空");
            log.info("user register baseResponse : {}",JsonUtil.objectToJson(baseResponse));
            return baseResponse;
        }else{
            User userEntity = baseMapper.selectByUserName(user);
            if(userEntity!=null){
                baseResponse.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
                baseResponse.setMessage("此用户名已经注册,请重新填写");
                log.info("user register baseResponse : {}",JsonUtil.objectToJson(baseResponse));
                return baseResponse;
            }
        }

        if(StringUtil.isEmpty(user.getUserIdcard())){
            baseResponse.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            baseResponse.setMessage("身份证不能为空");
            log.info("user register baseResponse : {}",JsonUtil.objectToJson(baseResponse));
            return baseResponse;
        }

        if(StringUtil.isEmpty(user.getPassword())){
            baseResponse.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            baseResponse.setMessage("密码不能为空");
            log.info("user register baseResponse : {}",JsonUtil.objectToJson(baseResponse));
            return baseResponse;
        }else{
            if(!user.getPassword().equals(user.getRePassword())){
                baseResponse.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
                baseResponse.setMessage("两次输入的密码不一样");
                log.info("user register baseResponse : {}",JsonUtil.objectToJson(baseResponse));
                return baseResponse;
            }
        }

        if(StringUtil.isEmpty(user.getTellphone())){
            baseResponse.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            baseResponse.setMessage("电话号码不能为空");
            log.info("user register baseResponse : {}",JsonUtil.objectToJson(baseResponse));
            return baseResponse;
        }

        if(StringUtil.isEmpty(user.getIdentifyingCode())){
            baseResponse.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            baseResponse.setMessage("验证码不能为空");
            log.info("user register baseResponse : {}",JsonUtil.objectToJson(baseResponse));
            return baseResponse;
        }

        //判断电话是否唯一
        if(!StringUtil.isEmpty(user.getTellphone())){
            User userInfo = baseMapper.selectByTell(user);
            if(userInfo!=null){
                baseResponse.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
                baseResponse.setMessage("该电话已经注册过");
                log.info("user register baseResponse : {}",JsonUtil.objectToJson(baseResponse));
                return baseResponse;
            }
        }
        if(user.getIdentifyingCode().equals(smsCode)){
            log.info("短信验证码验证成功");
        }else{
            baseResponse.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            baseResponse.setMessage("短信验证码与输入验证码不一致，请重新发送");
            log.info("user register baseResponse : {}",JsonUtil.objectToJson(baseResponse));
            return baseResponse;
        }
        user.setCreatedTime(System.currentTimeMillis());
        //生成唯一id
        String code= UUID.randomUUID().toString();
        //替换uuid中的"-"
        user.setUserCode(code.replace("-", ""));//员工编号(自动生成)
        String encodePassword = Encodes.encodeToMD5(user.getPassword());
        user.setPassword(encodePassword);
        boolean isright =  this.insert(user);

        if(isright){
            baseResponse.setDataInfo(user);
            baseResponse.setReturnCode(ReturnCodeEnum.CODE_1000.getCode());
            baseResponse.setMessage("成功");
        }else{
            baseResponse.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
            baseResponse.setMessage("失败");
        }
        return baseResponse;
    }

    @Override
    public BaseResponse updateUser(User user) {
        log.info("用户修改接口请求数据 {} :",user.toString());
        BaseResponse baseResponse = new BaseResponse();

        if(null == user){
            baseResponse.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            baseResponse.setMessage("入参实体为空");
            return baseResponse;
        }
        user.setUpdatedTime(System.currentTimeMillis());
        boolean isright =  this.updateById(user);
        if(isright){
            //首先删除之前的关联的资源
            boolean istrue = baseMapper.batchDelete(user);
            if(!istrue){
                baseResponse.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
                baseResponse.setMessage("删除关联的资源抛异常");
                return baseResponse;
            }

            //保存提交的最新的资源
            List<String> resouceList = user.getResouceList();
            if(resouceList!=null&&resouceList.size()>0){
                for (int i=0;i<resouceList.size();i++){
                    //循环插入用户资源
                    UserResouceEntity userResouceEntity = new UserResouceEntity();
                    userResouceEntity.setResouseUrl(resouceList.get(i));
                    userResouceEntity.setResouseId(user.getUserId());
                    userResouceServise.save(userResouceEntity);
                }
            }
            baseResponse.setDataInfo(user);
            baseResponse.setReturnCode(ReturnCodeEnum.CODE_1000.getCode());
            baseResponse.setMessage("成功");
        }else{
            baseResponse.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
            baseResponse.setMessage("失败");
        }
        return baseResponse;
    }

    @Override
    public BaseResponse deleteUser(User user) {
        log.info("用户删除接口请求数据 {} :",user.toString());
        BaseResponse baseResponse = new BaseResponse();

        if(null == user){
            baseResponse.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            baseResponse.setMessage("入参实体为空");
            return baseResponse;
        }

        User userEntity =   this.selectById(user.getUserId());
        if(userEntity!=null){
            userEntity.setDeleted(2);
          boolean isRight =   this.updateById(userEntity);
            if(isRight){
                boolean istrue = baseMapper.batchDelete(user);
                if(!istrue){
                    baseResponse.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
                    baseResponse.setMessage("删除关联的资源抛异常");
                    return baseResponse;
                }
                baseResponse.setReturnCode(ReturnCodeEnum.CODE_1000.getCode());
                baseResponse.setMessage("成功");
            }else {
                baseResponse.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
                baseResponse.setMessage("删除失败");
            }
        }else{
            baseResponse.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
            baseResponse.setMessage("用户不存在");
        }
        return baseResponse;
    }

    @Override
    public PageResponse<User> userdatapage(UserRequest request) {
        log.info("用户分页查询接口请求数据 {} :",request.toString());
        PageResponse pageResponse = new PageResponse();
        log.info(" 分页查询数据字典的数据请求参数：" + JSONObject.toJSONString(request));
        Page<User> page = new Page<User>();
        page.setCurrent(request.getPageNumber() == null ? 1 : request.getPageNumber());
        page.setSize(request.getPageSize() == null ? 10: request.getPageSize());

        EntityWrapper ew=new EntityWrapper();
        ew.like(StringUtils.isNotEmpty(request.getRealName()),"real_name",request.getRealName());
        ew.eq(StringUtils.isNotEmpty(request.getUserCode()),"user_code",request.getUserCode());
        ew.eq(request.getPostLevel()!=0,"post_level",request.getPostLevel());

        List<User> userList = baseMapper.selectPage(page,ew);
        if(userList.size()>0){
            Integer total = baseMapper.selectCount(ew);
            pageResponse.setRecords(userList);
            pageResponse.setTotal(total);
            pageResponse.setSize(request.getPageSize());
            pageResponse.setCurrent(request.getPageNumber());
            pageResponse.setReturnCode(ReturnCodeEnum.CODE_1000.getCode());
            pageResponse.setMessage(ReturnCodeEnum.CODE_1000.getValue());

            log.info("用户分页查询数据出参:{}", JsonUtil.objectToJson(pageResponse));

        }else{
            pageResponse.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
            pageResponse.setMessage("暂无数据");
        }

        return pageResponse;
    }

    @Override
    public BaseResponse selectByUserId(User user) {

        log.info("用户详情接口请求数据 {} :",user.toString());
        BaseResponse baseResponse = new BaseResponse();

        if(null == user){
            baseResponse.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            baseResponse.setMessage("入参实体为空");
            return baseResponse;
        }
        User userEntity =   this.selectById(user.getUserId());
        if(userEntity!=null){
            List<String > urlList = new ArrayList<>();
            List<UserResouceEntity> userResouceEntityList = userResouceServise.getByUserId(userEntity.getUserId().toString());
            for(int i=0;i<userResouceEntityList.size();i++ ){
                urlList.add(userResouceEntityList.get(i).getResouseUrl());
            }
            userEntity.setResouceList(urlList);
            baseResponse.setDataInfo(userEntity);
            baseResponse.setReturnCode(ReturnCodeEnum.CODE_1000.getCode());
            baseResponse.setMessage("成功");
        }else{
            baseResponse.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
            baseResponse.setMessage("用户不存在");
        }
        return baseResponse;
    }

    /**
     * 账号密码或者手机号登录
     * @param user
     * @return
     */
    @Override
    public BaseResponse login(User user) {
        log.info("用户详情接口请求数据 {} :",user.toString());
        BaseResponse baseResponse = new BaseResponse();

        if(user.getTellphone()==null){//账号密码登录
            if(StringUtil.isEmpty(user.getUserName())){
                baseResponse.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
                baseResponse.setMessage("账号不能为空");
                return baseResponse;
            }
            if(StringUtil.isEmpty(user.getPassword())){
                baseResponse.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
                baseResponse.setMessage("密码不能为空");
                return baseResponse;
            }

            //加密
            user.setPassword(Encodes.encodeToMD5(user.getPassword()));
            User userInfo = baseMapper.selectByUser(user);

            if(null == userInfo){
                baseResponse.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
                baseResponse.setMessage("账号或密码错误");
                return baseResponse;
            }else{
                baseResponse.setDataInfo(userInfo);
                baseResponse.setReturnCode(ReturnCodeEnum.CODE_1000.getCode());
                baseResponse.setMessage("成功");
            }
        }else{
            //手机号登陆
            //校验验证码是否失效（300秒有效）
            String redisCode = "SMS_MSG_SEND:"+"TYPE_"+1+":MOBILE_"+user.getTellphone();
            String smsCode = redis.get(redisCode);
            User userInfo = baseMapper.selectByTell(user);

            if(userInfo!=null){
                if(user.getIdentifyingCode().equals(smsCode)){
                    baseResponse.setDataInfo(userInfo);
                    baseResponse.setReturnCode(ReturnCodeEnum.CODE_1000.getCode());
                    baseResponse.setMessage("成功");
                }else{
                    baseResponse.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
                    baseResponse.setMessage("验证码错误");
                }
            }else{
                baseResponse.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
                baseResponse.setMessage("用户不存在");
            }
        }
        return baseResponse;
    }

    /**
     * 重置密码
     * @param user
     * @return
     */
    @Override
    public BaseResponse reSetPassword(User user) {
        log.info("用户详情接口请求数据 {} :",user.toString());
        BaseResponse baseResponse = new BaseResponse();
        if(StringUtil.isEmpty(user.getUserName())){
            baseResponse.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            baseResponse.setMessage("账号不能为空");
            return baseResponse;
        }else{
            User userEntity = baseMapper.selectByUserName(user);
            if(userEntity==null){
                baseResponse.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
                baseResponse.setMessage("此用户名不存在");
                return baseResponse;
            }
        }
        if(StringUtil.isEmpty(user.getTellphone())){
            baseResponse.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            baseResponse.setMessage("电话号码不能为空");
            log.info("user register baseResponse : {}",JsonUtil.objectToJson(baseResponse));
            return baseResponse;
        }
        if(StringUtil.isEmpty(user.getIdentifyingCode())){
            baseResponse.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            baseResponse.setMessage("验证码不能为空");
            log.info("user register baseResponse : {}",JsonUtil.objectToJson(baseResponse));
            return baseResponse;
        }
        //判断电话是否唯一
        if(!StringUtil.isEmpty(user.getTellphone())){
            User userInfo = baseMapper.selectByTell(user);
            if(userInfo==null){
                baseResponse.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
                baseResponse.setMessage("该电话没有注册过");
                log.info("user register baseResponse : {}",JsonUtil.objectToJson(baseResponse));
                return baseResponse;
            }
        }
        if(StringUtil.isEmpty(user.getPassword())){
            baseResponse.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            baseResponse.setMessage("密码不能为空");
            log.info("user register baseResponse : {}",JsonUtil.objectToJson(baseResponse));
            return baseResponse;
        }else{
            if(!user.getPassword().equals(user.getRePassword())){
                baseResponse.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
                baseResponse.setMessage("两次输入的密码不一样");
                log.info("user register baseResponse : {}",JsonUtil.objectToJson(baseResponse));
                return baseResponse;
            }
        }
        //校验验证码是否失效（300秒有效）
        String redisCode = "SMS_MSG_SEND:"+"TYPE_"+3+":MOBILE_"+user.getTellphone();
        String smsCode = redis.get(redisCode);
        if(StringUtil.isEmpty(smsCode)){
            baseResponse.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            baseResponse.setMessage("短信验证码已过期，请重新发送");
            log.info("user register baseResponse : {}",JsonUtil.objectToJson(baseResponse));
            return baseResponse;
        }

        if(user.getIdentifyingCode().equals(smsCode)){
            log.info("短信验证码验证成功");
        }else{
            baseResponse.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            baseResponse.setMessage("短信验证码与输入验证码不一致，请重新发送");
            log.info("user register baseResponse : {}",JsonUtil.objectToJson(baseResponse));
            return baseResponse;
        }
        //加密密码
        user.setPassword(Encodes.encodeToMD5(user.getPassword()));
       boolean isRigth = baseMapper.updatePassword(user);
       if(isRigth){
           baseResponse.setReturnCode(ReturnCodeEnum.CODE_1000.getCode());
           baseResponse.setMessage("成功");
           log.info("user register baseResponse : {}",JsonUtil.objectToJson(baseResponse));
           return baseResponse;
       }else{
           baseResponse.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
           baseResponse.setMessage("失败");
           log.info("user register baseResponse : {}",JsonUtil.objectToJson(baseResponse));
           return baseResponse;
       }
    }
}
