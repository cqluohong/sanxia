package com.sx.ecomodule.dao;

import com.sx.ecomodule.entity.jcdb24a;
import org.apache.ibatis.annotations.Param;

public interface jcdb24aMapper {
    int insert(jcdb24a record);

    int insertSelective(jcdb24a record);

    jcdb24a queryBytimeandloc(@Param("time") String time, @Param("loc") String loc);
}