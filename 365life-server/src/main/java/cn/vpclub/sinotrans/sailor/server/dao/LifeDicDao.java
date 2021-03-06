package cn.vpclub.sinotrans.sailor.server.dao;


import cn.vpclub.sinotrans.sailor.feign.domain.entity.LifeDicEntity;
import cn.vpclub.sinotrans.sailor.feign.model.request.LifeDicRequest;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 */
public interface LifeDicDao extends BaseMapper<LifeDicEntity> {

    public List<LifeDicEntity> selectAllDic(Pagination page, @Param("paramsMap") Map paramsMap);

    public List<LifeDicEntity> selectDicByType( @Param("paramsMap") Map paramsMap);


    public List<LifeDicEntity> selectDicGroupName();


    public List<LifeDicEntity> selectDicByName(LifeDicRequest request) ;
}
