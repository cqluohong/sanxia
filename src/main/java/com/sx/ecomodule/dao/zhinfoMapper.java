package com.sx.ecomodule.dao;

import com.sx.ecomodule.entity.zhinfo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface zhinfoMapper {
    int insert(zhinfo record);

    int insertSelective(zhinfo record);

    List<zhinfo> queryByAll();
    @Select("select * from ZHBB01A where ZHBB01A010 = #{id}")
    zhinfo queryById(String id);
}