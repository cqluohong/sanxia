package com.sx.ecomodule.web.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sx.ecomodule.dao.ShuiweiMapper;
import com.sx.ecomodule.entity.Shuiwei;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShuiweiService {
    @Autowired
    private ShuiweiMapper shuiweiMapper;
    public JSONObject selectBypage(int page, int limit){
        int start = (page-1) * limit+1;
        int end = start + limit;
        List<Shuiwei> shuiweis = shuiweiMapper.selectBypage(start,end);
        int len = shuiweiMapper.selectCounts();
        JSONObject jo = new JSONObject();
        jo.put("code",0);
        jo.put("message","");
        jo.put("count",len);
        jo.put("data",shuiweis);
        return jo;
    }
    public JSONObject getSwChartSerarch(int page,int limit, String zhandian,String starttime, String endtime){
        int start = (page-1) * limit+1;
        int end = start + limit;
        List<Shuiwei> shuiweis = shuiweiMapper.getSwChartSerarch(start,end,zhandian,starttime,endtime);
        int len = shuiweiMapper.swChartCount(zhandian,starttime,endtime);
        JSONObject jo = new JSONObject();
        jo.put("code",0);
        jo.put("message","");
        jo.put("count",len);
        jo.put("data",shuiweis);
        return jo;
    }
    public JSONObject getSwChartBynopage(String zhandian,String starttime, String endtime){
        List<Shuiwei> ts = shuiweiMapper.getSwChartBynopage(zhandian,starttime,endtime);
        return getSWChartData((JSONArray)JSONArray.toJSON(ts));
    }
    private JSONObject getSWChartData(JSONArray jsonArray) {
        JSONObject result=new JSONObject();
        JSONArray xData=new JSONArray();
        JSONArray seriesDataSWZDZ=new JSONArray();
        JSONArray seriesDataPJZ=new JSONArray();
        JSONArray seriesDataZDZ=new JSONArray();
        JSONArray seriesDataZXZ=new JSONArray();
        JSONArray seriesDataZDZSJ=new JSONArray();
        JSONArray seriesDataZXZSJ=new JSONArray();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject object = (JSONObject)JSONObject.toJSON(jsonArray.get(i));
            xData.add(object.get("xtsw01a020"));
            seriesDataSWZDZ.add(object.get("xtsw01a030"));
            seriesDataPJZ.add(object.get("xtsw01a040"));
            seriesDataZDZ.add(object.get("xtsw01a050"));
            seriesDataZXZ.add(object.get("xtsw01a070"));
            String[] zdzsj=((String) object.get("xtsw01a060")).split(" ");
            String[] zxzsj=((String) object.get("xtsw01a080")).split(" ");
            String[] zdzsj1=zdzsj[1].split(":");
            String[] zxzsj1=zxzsj[1].split(":");
            seriesDataZDZSJ.add(zdzsj1[0]);
            seriesDataZXZSJ.add(zxzsj1[0]);
        }
        result.put("xData", xData);
        result.put("seriesDataSWZDZ", seriesDataSWZDZ);
        result.put("seriesDataPJZ", seriesDataPJZ);
        result.put("seriesDataZDZ", seriesDataZDZ);
        result.put("seriesDataZXZ", seriesDataZXZ);
        result.put("seriesDataZDZSJ", seriesDataZDZSJ);
        result.put("seriesDataZXZSJ", seriesDataZXZSJ);
        return result;
    }
    public Double querySwBytime(String time){
        List<Shuiwei> sw = shuiweiMapper.querySwBytime(time);
        if(sw!=null){
            BigDecimal r =new BigDecimal(0);
            for(Shuiwei item:sw) {
                r = r.add(item.getXtsw01a030());
            }
            return r.doubleValue()/3;
        }
        else return null;
    }
}
