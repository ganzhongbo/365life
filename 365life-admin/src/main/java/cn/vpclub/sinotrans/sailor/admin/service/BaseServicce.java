package cn.vpclub.sinotrans.sailor.admin.service;

import cn.vpclub.wuhan.redis.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 公共服务逻辑处理类
 * Created by chentao on 2018/8/3.
 */
@Service
@Slf4j
public class BaseServicce {

    @Autowired
    private RedisUtils redis;

    protected String getUser() {
        HttpServletRequest request = getRequest();
        String userId = request.getHeader("userId");
        String redisCode = "userInfo:user"+userId;
        String userInfo = redis.get(redisCode);
        return userInfo;
    }


    /**
     * 得到request对象
     *
     * @return
     */
    public HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }


}
