package com.sx.ecomodule.web.service;

import com.alibaba.fastjson.JSONObject;
import com.sx.ecomodule.dao.qxzMapper;
import com.sx.ecomodule.entity.qxz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class qxzService {
    @Autowired
    private qxzMapper qxzmapper;
    public JSONObject selectBypage(int page, int limit){
        int start = (page-1) * limit+1;
        int end = start + limit;
        List<qxz> qxzs = qxzmapper.selectBypage(start,end);
        int len = qxzmapper.selectCounts();
        JSONObject jo = new JSONObject();
        jo.put("code",0);
        jo.put("message","");
        jo.put("count",len);
        jo.put("data",qxzs);
        return jo;
    }
    public int updateByPrimaryKey(qxz record){
        return qxzmapper.updateByPrimaryKey(record);
    }
    public int insert(qxz record){
        return qxzmapper.insert(record);
    }
    public int deleteByPrimaryKey(String qxaa01a010){
        return qxzmapper.deleteByPrimaryKey(qxaa01a010);
    }
    public JSONObject selectByfuzzy(String fuzzy){
        JSONObject jo = new JSONObject();
        jo.put("code",0);
        jo.put("message","");
        List<qxz> qxzs = qxzmapper.selectByfuzzy(fuzzy);
        jo.put("count",qxzs.size());
        jo.put("data",qxzs);
        return jo;
    }
}
