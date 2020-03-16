package com.sx.ecomodule.dao;

import com.sx.ecomodule.entity.Raindata;
import com.sx.ecomodule.model.RainModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RaindataMapper {
    int insert(Raindata record);

    int insertSelective(Raindata record);

    Double getInversedistanceSum(String time , double x , double y);

    Double getIdwValue(double inversedistanceSum, String time , double x , double y);

    List<RainModel> getzhandianRainBytime(@Param("time") String time);
}