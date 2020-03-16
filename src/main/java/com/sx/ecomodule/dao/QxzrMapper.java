package com.sx.ecomodule.dao;

import com.sx.ecomodule.entity.Qxzr;
import com.sx.ecomodule.entity.qxz;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QxzrMapper {
    int insert(Qxzr record);

    int insertSelective(Qxzr record);

    List<qxz> selectBypage(@Param("start") int start, @Param("end") int end);

    int selectCounts();

    List<qxz> selectByfuzzy(@Param("fuzzy") String fuzzy);
}