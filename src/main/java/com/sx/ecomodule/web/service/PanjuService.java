package com.sx.ecomodule.web.service;

import com.alibaba.fastjson.JSONObject;
import com.sx.ecomodule.dao.PanjuMapper;
import com.sx.ecomodule.dao.qxzMapper;
import com.sx.ecomodule.entity.Panju;
import com.sx.ecomodule.entity.qxz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PanjuService {
    @Autowired
    private PanjuMapper panjuMapper;
    public JSONObject selectBypage(int page, int limit){
        int start = (page-1) * limit+1;
        int end = start + limit;
        List<Panju> panjus = panjuMapper.selectBypage(start,end);
        int len = panjuMapper.selectCounts();
        JSONObject jo = new JSONObject();
        jo.put("code",0);
        jo.put("message","");
        jo.put("count",len);
        jo.put("data",panjus);
        return jo;
    }
}
