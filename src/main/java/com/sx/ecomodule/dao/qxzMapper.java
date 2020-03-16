package com.sx.ecomodule.dao;

import com.sx.ecomodule.entity.qxz;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface qxzMapper {
    int deleteByPrimaryKey(String qxaa01a010);

    int insert(qxz record);

    int insertSelective(qxz record);

    qxz selectByPrimaryKey(String qxaa01a010);

    int updateByPrimaryKeySelective(qxz record);

    int updateByPrimaryKey(qxz record);

    List<qxz> selectBypage(@Param("start") int start,@Param("end") int end);

    int selectCounts();

    List<qxz> selectByfuzzy(@Param("fuzzy") String fuzzy);
}