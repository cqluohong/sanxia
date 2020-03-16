package com.sx.ecomodule.dao;

import com.sx.ecomodule.entity.Shuiwei;
import com.sx.ecomodule.entity.qxz;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShuiweiMapper {
    int insert(Shuiwei record);

    int insertSelective(Shuiwei record);

    List<Shuiwei> selectBypage(@Param("start") int start, @Param("end") int end);

    int selectCounts();

    List<Shuiwei> getSwChartSerarch(@Param("start") int start, @Param("end") int end,
                                    @Param("zhandian")String zhandian,@Param("starttime")String starttime,
                                    @Param("endtime")String endtime);
    int swChartCount(@Param("zhandian")String zhandian,@Param("starttime")String starttime,
                     @Param("endtime")String endtime);
    List<Shuiwei> getSwChartBynopage(@Param("zhandian")String zhandian,@Param("starttime")String starttime,
                      @Param("endtime")String endtime);
    List<Shuiwei> querySwBytime(@Param("time") String time);
}