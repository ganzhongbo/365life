package cn.vpclub.sinotrans.sailor.server.service.impl;

import cn.vpclub.demo.common.model.core.enums.ReturnCodeEnum;
import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.demo.common.model.core.model.response.PageResponse;
import cn.vpclub.demo.common.model.utils.common.Encodes;
import cn.vpclub.demo.common.model.utils.common.JsonUtil;
import cn.vpclub.demo.common.model.utils.common.StringUtil;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.User;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.UserResouceEntity;
import cn.vpclub.sinotrans.sailor.feign.model.request.UserDataRequest;
import cn.vpclub.sinotrans.sailor.feign.model.request.UserRequest;
import cn.vpclub.sinotrans.sailor.server.dao.UserDao;
import cn.vpclub.sinotrans.sailor.server.service.UserResouceServise;
import cn.vpclub.sinotrans.sailor.server.service.UserService;
import cn.vpclub.sinotrans.sailor.server.utils.TestDate;
import cn.vpclub.wuhan.redis.utils.RedisUtils;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.omg.CORBA.BAD_CONTEXT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
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
    @Transactional
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
            UserResouceEntity userResouceEntityDo = new UserResouceEntity();
            userResouceEntityDo.setResouseId(user.getUserId());
            userResouceEntityDo.setType(1);
            boolean istrue = baseMapper.batchDelete(userResouceEntityDo);
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
                    userResouceEntity.setType(1);
                    userResouceServise.save(userResouceEntity);
                }
            }
            baseResponse.setDataInfo(user);
            //将用户的信息加入到缓存中
            String redisUserCode = "userInfo:user"+user.getUserId();
            //先删除
            redis.delete(redisUserCode);
            //redis存储用户信息(永久有效)
            redis.set(redisUserCode,user,-1);
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
                UserResouceEntity userResouceEntity = new UserResouceEntity();
                userResouceEntity.setResouseId(user.getUserId());
                userResouceEntity.setType(1);
                boolean istrue = baseMapper.batchDelete(userResouceEntity);
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
        if(request.getPostLevel()!=null){
            ew.eq("post_level",request.getPostLevel());
        }

        List<User> userList = baseMapper.selectPage(page,ew);
        if(userList.size()>0){
            Integer total = baseMapper.selectCount(ew);
            pageResponse.setRecords(userList);
            pageResponse.setTotal(total);
            pageResponse.setSize(page.getSize());
            pageResponse.setCurrent(page.getCurrent());
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
                //将用户的信息加入到缓存中
                String redisCode = "userInfo:user"+userInfo.getUserId();
                //先删除
                redis.delete(redisCode);
                //redis存储用户信息(永久有效)
                redis.set(redisCode,userInfo,-1);

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
                    //将用户的信息加入到缓存中
                    String redisUserCode = "userInfo:user"+userInfo.getUserId();
                    //先删除
                    redis.delete(redisUserCode);
                    //redis存储用户信息(永久有效)
                    redis.set(redisUserCode,userInfo,-1);
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
        String redisCode = "SMS_MSG_SEND:"+"TYPE_"+1+":MOBILE_"+user.getTellphone();
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

           //将用户的信息加入到缓存中
           String redisUserCode = "userInfo:user"+user.getUserId();
           //先删除
           redis.delete(redisUserCode);
           //redis存储用户信息(永久有效)
           redis.set(redisUserCode,user,-1);

           baseResponse.setDataInfo(user);
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

    /***
     * 绑定经济人和选择上级,查询用户
     * @param
     * @return
     */
    @Override
    public PageResponse<User> selectUser(UserRequest request) {
        PageResponse pageResponse = new PageResponse();
        Page<User> page = new Page<User>();
        page.setCurrent(request.getPageNumber() == null ? 1 : request.getPageNumber());
        page.setSize(request.getPageSize() == null ? 10: request.getPageSize());

        EntityWrapper ew=new EntityWrapper();
        ew.like(StringUtils.isNotEmpty(request.getRealName()),"real_name",request.getRealName());
        Object role[] = {1,2,3};
        ew.in("user_role",role);
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

    /**
     * 个人数据
     * @param userDataRequest
     * @return
     */
    @Override
    public BaseResponse getUserData( UserDataRequest userDataRequest){
        log.info("用户详情接口请求数据 {} :",userDataRequest.toString());
        BaseResponse baseResponse = new BaseResponse();
        //客源数据
        if(userDataRequest.getType()==1){//今天
            //租房
            userDataRequest.setSelectType(1);
            Date startTime = TestDate.getTimesmorning();
            Date endTime = TestDate.getTimesnight();
            userDataRequest.setStratTime(startTime.getTime());
            userDataRequest.setEndTime(endTime.getTime());
            //今天的获取的租客数
           Integer rentingCount =  baseMapper.getRentingOrTenantCount(userDataRequest);
           //今天获取的出租的房源数
           Integer rentingHoseCount =  baseMapper.getRentingOrTenantHouseCount(userDataRequest);

            //出售
            userDataRequest.setSelectType(2);
            //今天的获取卖房的客户数
            Integer tenantCount =  baseMapper.getRentingOrTenantCount(userDataRequest);
            //今天获取出售的房源数
            Integer tenantHoseCount =  baseMapper.getRentingOrTenantHouseCount(userDataRequest);

            //组装当天的客源数据
            userDataRequest.setRentingerSource(rentingCount);//当天获取出租的客源
            userDataRequest.setBuyerSource(tenantCount);//当天获取出售的客源
            userDataRequest.setGuestSourceTotal(rentingCount+tenantCount);

            //组装当天的房源数据
            userDataRequest.setRentingItem(rentingHoseCount);//当天获取出租的房源
            userDataRequest.setSellHoseItem(tenantHoseCount);//当天获取出售的房源
            userDataRequest.setHouseTotal(rentingHoseCount+tenantHoseCount);


        }else if(userDataRequest.getType()==2){//本周
            //租房
            userDataRequest.setSelectType(1);
            Date startTime = TestDate.getTimesWeekmorning();//本周第一天0点
            Date endTime = new Date();//当前时间
            userDataRequest.setStratTime(startTime.getTime());
            userDataRequest.setEndTime(endTime.getTime());
            //今天的获取的租客数
            Integer rentingCount =  baseMapper.getRentingOrTenantCount(userDataRequest);
            //今天获取的出租的房源数
            Integer rentingHoseCount =  baseMapper.getRentingOrTenantHouseCount(userDataRequest);

            //出售
            userDataRequest.setSelectType(2);
            //今天的获取卖房的客户数
            Integer tenantCount =  baseMapper.getRentingOrTenantCount(userDataRequest);
            //今天获取出售的房源数
            Integer tenantHoseCount =  baseMapper.getRentingOrTenantHouseCount(userDataRequest);

            //组装当天的客源数据
            userDataRequest.setRentingerSource(rentingCount);//当天获取出租的客源
            userDataRequest.setBuyerSource(tenantCount);//当天获取出售的客源
            userDataRequest.setGuestSourceTotal(rentingCount+tenantCount);

            //组装当天的房源数据
            userDataRequest.setRentingItem(rentingHoseCount);//当天获取出租的房源
            userDataRequest.setSellHoseItem(tenantHoseCount);//当天获取出售的房源
            userDataRequest.setHouseTotal(rentingHoseCount+tenantHoseCount);


        }else{//本月

            //租房
            userDataRequest.setSelectType(1);
            Date startTime = TestDate.getTimesMonthmorning();//本月第一天0点
            Date endTime = new Date();//当前时间
            userDataRequest.setStratTime(startTime.getTime());
            userDataRequest.setEndTime(endTime.getTime());
            //今天的获取的租客数
            Integer rentingCount =  baseMapper.getRentingOrTenantCount(userDataRequest);
            //今天获取的出租的房源数
            Integer rentingHoseCount =  baseMapper.getRentingOrTenantHouseCount(userDataRequest);

            //出售
            userDataRequest.setSelectType(2);
            //今天的获取卖房的客户数
            Integer tenantCount =  baseMapper.getRentingOrTenantCount(userDataRequest);
            //今天获取出售的房源数
            Integer tenantHoseCount =  baseMapper.getRentingOrTenantHouseCount(userDataRequest);

            //组装当天的客源数据
            userDataRequest.setRentingerSource(rentingCount);//当天获取出租的客源
            userDataRequest.setBuyerSource(tenantCount);//当天获取出售的客源
            userDataRequest.setGuestSourceTotal(rentingCount+tenantCount);

            //组装当天的房源数据
            userDataRequest.setRentingItem(rentingHoseCount);//当天获取出租的房源
            userDataRequest.setSellHoseItem(tenantHoseCount);//当天获取出售的房源
            userDataRequest.setHouseTotal(rentingHoseCount+tenantHoseCount);


        }
        baseResponse.setDataInfo(userDataRequest);
        baseResponse.setReturnCode(ReturnCodeEnum.CODE_1000.getCode());
        baseResponse.setMessage("成功");
        return baseResponse;
    }

    /**
     * 经营报表
     * @param userDataRequest
     * @return
     */
    @Override
    public BaseResponse businessReport(UserDataRequest userDataRequest) {
        log.info("用户详情接口请求数据 {} :",userDataRequest.toString());
        BaseResponse baseResponse = new BaseResponse();
        if(userDataRequest.getType()==1){//今天

            Date startTime = TestDate.getTimesmorning();
            Date endTime = TestDate.getTimesnight();
            userDataRequest.setStratTime(startTime.getTime());
            userDataRequest.setEndTime(endTime.getTime());
            //当天录入的房源(信息员)
            Integer addHoseCount =  baseMapper.getAddHoseCount(userDataRequest);
            //当天实勘的次数(经纪人)
            Integer realExplorationCount =  baseMapper.getRealExplorationCount(userDataRequest);
            //当天带看的次数(经纪人)
            Integer takeLookCount =  baseMapper.getTakeLookCount(userDataRequest);
            //当天录入的客源数量(信息员)
            Integer addGuestsCount =  baseMapper.getAddGuestsCount(userDataRequest);
            //当天获取的客源数量(经纪人)
            Integer receiveGuestsCount = baseMapper.getReceiveGuestsCount(userDataRequest);

            userDataRequest.setAddHoseCount(addHoseCount);//当天录入的房源
            userDataRequest.setRealExplorationCount(realExplorationCount);//当天实勘的次数
            userDataRequest.setTakeLookCount(takeLookCount);//当天带看的次数
            userDataRequest.setAddGuestsCount(addGuestsCount);//当天录入的客源数量
            userDataRequest.setReceiveGuestsCount(receiveGuestsCount);//当天获取的客源数量


        }else if(userDataRequest.getType()==2){//本周

            Date startTime = TestDate.getTimesWeekmorning();//本周第一天0点
            Date endTime = new Date();//当前时间
            userDataRequest.setStratTime(startTime.getTime());
            userDataRequest.setEndTime(endTime.getTime());
            //本周录入的房源(信息员)
            Integer addHoseCount =  baseMapper.getAddHoseCount(userDataRequest);
            //本周实勘的次数(经纪人)
            Integer realExplorationCount =  baseMapper.getRealExplorationCount(userDataRequest);
            //本周带看的次数(经纪人)
            Integer takeLookCount =  baseMapper.getTakeLookCount(userDataRequest);
            //本周录入的客源数量(信息员)
            Integer addGuestsCount =  baseMapper.getAddGuestsCount(userDataRequest);
            //本周获取的客源数量(经纪人)
            Integer receiveGuestsCount = baseMapper.getReceiveGuestsCount(userDataRequest);

            userDataRequest.setAddHoseCount(addHoseCount);//本周录入的房源
            userDataRequest.setRealExplorationCount(realExplorationCount);//本周实勘的次数
            userDataRequest.setTakeLookCount(takeLookCount);//本周带看的次数
            userDataRequest.setAddGuestsCount(addGuestsCount);//本周录入的客源数量
            userDataRequest.setReceiveGuestsCount(receiveGuestsCount);//本周获取的客源数量

        }else{//本月

            Date startTime = TestDate.getTimesMonthmorning();//本月第一天0点
            Date endTime = new Date();//当前时间
            userDataRequest.setStratTime(startTime.getTime());
            userDataRequest.setEndTime(endTime.getTime());
            //本月录入的房源(信息员)
            Integer addHoseCount =  baseMapper.getAddHoseCount(userDataRequest);
            //本月实勘的次数(经纪人)
            Integer realExplorationCount =  baseMapper.getRealExplorationCount(userDataRequest);
            //本月带看的次数(经纪人)
            Integer takeLookCount =  baseMapper.getTakeLookCount(userDataRequest);
            //本月录入的客源数量(信息员)
            Integer addGuestsCount =  baseMapper.getAddGuestsCount(userDataRequest);
            //本月获取的客源数量(经纪人)
            Integer receiveGuestsCount = baseMapper.getReceiveGuestsCount(userDataRequest);

            userDataRequest.setAddHoseCount(addHoseCount);//本月录入的房源
            userDataRequest.setRealExplorationCount(realExplorationCount);//本月实勘的次数
            userDataRequest.setTakeLookCount(takeLookCount);//本月带看的次数
            userDataRequest.setAddGuestsCount(addGuestsCount);//本月录入的客源数量
            userDataRequest.setReceiveGuestsCount(receiveGuestsCount);//本月获取的客源数量
        }
        baseResponse.setDataInfo(userDataRequest);
        baseResponse.setReturnCode(ReturnCodeEnum.CODE_1000.getCode());
        baseResponse.setMessage("成功");
        return baseResponse;

    }


}
