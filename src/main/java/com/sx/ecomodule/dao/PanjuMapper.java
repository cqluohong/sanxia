package com.sx.ecomodule.dao;

import com.sx.ecomodule.entity.Panju;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PanjuMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(Panju record);

    int insertSelective(Panju record);

    Panju selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(Panju record);

    int updateByPrimaryKey(Panju record);

    List<Panju> selectBypage(@Param("start") int start, @Param("end") int end);

    int selectCounts();

    List<Panju> selectByfuzzy(@Param("fuzzy") String fuzzy);
}