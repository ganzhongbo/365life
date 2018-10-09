package cn.vpclub.sinotrans.sailor.server.dao;


import cn.vpclub.sinotrans.sailor.feign.domain.entity.BusinessCollegeEntity;
import cn.vpclub.sinotrans.sailor.feign.domain.entity.LifeDicEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 */
public interface BusinessCollegeDao extends BaseMapper<BusinessCollegeEntity> {

    public List<BusinessCollegeEntity> selectAllBusinessCollege(Pagination page, @Param("paramsMap") Map paramsMap);

}
