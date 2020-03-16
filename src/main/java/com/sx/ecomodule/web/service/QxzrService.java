package com.sx.ecomodule.web.service;

import com.alibaba.fastjson.JSONObject;
import com.sx.ecomodule.dao.QxzrMapper;
import com.sx.ecomodule.dao.qxzMapper;
import com.sx.ecomodule.entity.Qxzr;
import com.sx.ecomodule.entity.qxz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QxzrService {
    @Autowired
    private QxzrMapper qxzrmapper;
    public JSONObject selectBypage(int page, int limit){
        int start = (page-1) * limit+1;
        int end = start + limit;
        List<qxz> qxzrs = qxzrmapper.selectBypage(start,end);
        int len = qxzrmapper.selectCounts();
        JSONObject jo = new JSONObject();
        jo.put("code",0);
        jo.put("message","");
        jo.put("count",len);
        jo.put("data",qxzrs);
        return jo;
    }
//    public int updateByPrimaryKey(Qxzr record){
//        return qxzrmapper.updateByPrimaryKey(record);
//    }
    public int insert(Qxzr record){
        return qxzrmapper.insert(record);
    }
//    public int deleteByPrimaryKey(String qxaa01a010){
//        return qxzrmapper.deleteByPrimaryKey(qxaa01a010);
//    }
    public JSONObject selectByfuzzy(String fuzzy){
        JSONObject jo = new JSONObject();
        jo.put("code",0);
        jo.put("message","");
        List<qxz> qxzs = qxzrmapper.selectByfuzzy(fuzzy);
        jo.put("count",qxzs.size());
        jo.put("data",qxzs);
        return jo;
    }
}
