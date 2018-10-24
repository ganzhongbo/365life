package cn.vpclub.sinotrans.sailor.server.service.impl;

import cn.vpclub.demo.common.model.core.enums.ReturnCodeEnum;
import cn.vpclub.demo.common.model.core.model.response.BaseResponse;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.UserLevelEntity;
import cn.vpclub.sinotrans.sailor.server.dao.UserLevelDao;
import cn.vpclub.sinotrans.sailor.server.service.UserLevelServise;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Created by chentao on 2018/7/19.
 */
@Service
@Slf4j
public class UserLevelServiceImpl extends ServiceImpl<UserLevelDao, UserLevelEntity> implements UserLevelServise {

    /**
     * 保存用户的等级配置
     * @param entity
     * @return
     */
    @Override
    public BaseResponse saveUserLevel(UserLevelEntity entity) {
        log.info("字典修改接口请求数据 {} :",entity.toString());
        BaseResponse response = new BaseResponse<>();
        boolean isRight = this.insert(entity) ;
        if(isRight){
            response.setReturnCode(ReturnCodeEnum.CODE_1000.getCode());
            response.setMessage("成功");
        }else{
            response.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
            response.setMessage("失败");
        }
        return  response;
    }

    /**
     * 修改用户等级配置
     * @param entity
     * @return
     */
    @Override
    public BaseResponse updateUserLevel(UserLevelEntity entity) {
        log.info("字典修改接口请求数据 {} :",entity.toString());
        BaseResponse response = new BaseResponse<>();
        boolean isRight = this.updateById(entity);
        if(isRight){
            response.setReturnCode(ReturnCodeEnum.CODE_1000.getCode());
            response.setMessage("成功");
        }else{
            response.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
            response.setMessage("失败");
        }
        return  response;
    }

    /**
     * 查询用户等级配置
     * @return
     */
    @Override
    public BaseResponse getUserLevel() {
        BaseResponse response = new BaseResponse<>();
        UserLevelEntity userLevelEntity = baseMapper.getUserLevel();
        response.setDataInfo(userLevelEntity);
        response.setReturnCode(ReturnCodeEnum.CODE_1000.getCode());
        response.setMessage("成功");
        return response;
    }
}
