package com.sx.ecomodule.dao;

import com.sx.ecomodule.entity.Fbaj01a;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface Fbaj01aMapper {
    int insert(Fbaj01a record);

    int insertSelective(Fbaj01a record);

    List<Fbaj01a> queryHavingPanju(@Param("id") String id);
}