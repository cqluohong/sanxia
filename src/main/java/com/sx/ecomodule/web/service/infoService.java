package com.sx.ecomodule.web.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sx.ecomodule.dao.Fbaj01aMapper;
import com.sx.ecomodule.dao.RaindataMapper;
import com.sx.ecomodule.dao.jcdb24aMapper;
import com.sx.ecomodule.dao.zhinfoMapper;
import com.sx.ecomodule.entity.Fbaj01a;
import com.sx.ecomodule.entity.zhinfo;
import com.sx.ecomodule.model.RainModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sx.ecomodule.entity.jcdb24a;

import java.math.BigDecimal;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class
infoService {
    @Autowired
    private zhinfoMapper zhinfoDao;
    @Autowired
    private RainDataService rainDataService;
    @Autowired
    private jcdb24aMapper jcdb24aDao;
    @Autowired
    private Fbaj01aMapper fbaj01aDao;
    @Autowired
    private RaindataMapper RaindataDao;

//    public JSONArray getDisasterForWarning(String  time){
//        List<zhinfo> zhinfos = zhinfoDao.queryByAll();
//        JSONArray ans = new JSONArray();
//        for(zhinfo item:zhinfos){
//            List<Fbaj01a> panjus = fbaj01aDao.queryHavingPanju(item.getZhbb01a010());
//            if(panjus==null||panjus.size()<4)continue;
//            Double rainvalue = rainDataService.getXYraindata1(time,item.getZhbb01a110().doubleValue()
//                    ,item.getZhbb01a120().doubleValue());
//            if(rainvalue==null)continue;
//            for(Fbaj01a panju:panjus){
//                BigDecimal down = panju.getFbaj01a160();
//                BigDecimal up = panju.getFbaj01a150();
//                if(down!=null && up!=null){
//                    double raindown = down.doubleValue();
//                    double rainup = up.doubleValue();
//                    if(rainvalue>=raindown&&rainvalue<=rainup){
//                        String level = panju.getFbaj01a250();
//                        if(level.startsWith("警报级")){
//                            double rain4 = getAverageRain(time,4,item.getZhbb01a110().doubleValue(),
//                                    item.getZhbb01a120().doubleValue());
//                            if(rain4>=panju.getFbaj01a210().doubleValue()){
//                                ans.add(getWarningLevel(item,level));
//                                break;
//                            }
//                            else{
//                                double rain5 = getAverageRain(time,5,item.getZhbb01a110().doubleValue(),
//                                        item.getZhbb01a120().doubleValue());
//                                if(rain5>=panju.getFbaj01a240().doubleValue()){
//                                    ans.add(getWarningLevel(item,level));
//                                    break;
//                                }
//                                else {
//                                    ans.add(getWarningLevel(item,"警戒级"));
//                                    break;
//                                }
//                            }
//                        }
//                        else{
//                            ans.add(getWarningLevel(item,level));
//                            break;
//                        }
//                    }
//                }
//                else continue;
//            }
//        }
//        return ans;
//    }

    public JSONArray getDisasterForWarning1(String time){
        List<RainModel> rainModels = RaindataDao.getzhandianRainBytime(time);
        JSONArray ans = new JSONArray();
        for(RainModel item:rainModels){
            Double rainvalue = item.getRainValue();
            List<Fbaj01a> panjus = fbaj01aDao.queryHavingPanju(item.getId());
            if(panjus==null||panjus.size()<4)continue;
            for(Fbaj01a panju:panjus){
                BigDecimal down = panju.getFbaj01a160();
                BigDecimal up = panju.getFbaj01a150();
                if(down!=null && up!=null){
                    double raindown = down.doubleValue();
                    double rainup = up.doubleValue();
                    if(rainvalue>=raindown&&rainvalue<=rainup){
                        String level = panju.getFbaj01a250();
                        ans.add(getWarningLevel1(item,level));
                        break;
                    }
                }
                else continue;
            }
        }
        return ans;
    }
    private double getAverageRain(String date,int offset,double x,double y){
        double ans =0;
        for(int i=offset;i>0;--i){
            String xaxis = getSpecialdayOffset(date,-i);
            Double rainvalue = rainDataService.getXYraindata1(xaxis,x,y);
            if(rainvalue!=null){
                ans+=rainvalue;
            }
        }
        return ans/offset;
    }

    private JSONObject getWarningLevel1(RainModel item,String level){
        zhinfo zhinfo = zhinfoDao.queryById(item.getId());
        JSONObject jo = new JSONObject(true);
        jo.put("geometry",new JSONObject(true));
        double x = Double.parseDouble(item.getX().toString());
        double y = Double.parseDouble(item.getY().toString());
        jo.getJSONObject("geometry").put("x",x);
        jo.getJSONObject("geometry").put("y",y);
        jo.getJSONObject("geometry").put("spatialReference",new JSONObject(true));
        jo.getJSONObject("geometry").getJSONObject("spatialReference").put("wkid",4326);
        jo.put("attributes",new JSONObject(true));
        jo.getJSONObject("attributes").put("x",x);
        jo.getJSONObject("attributes").put("y",y);
        jo.getJSONObject("attributes").put("灾害点名称",zhinfo.getZhbb01a020());
        jo.getJSONObject("attributes").put("death",zhinfo.getZhbb01a810());
        jo.getJSONObject("attributes").put("hurt",zhinfo.getZhbb01a815());
        jo.getJSONObject("attributes").put("ecoloss",zhinfo.getZhbb01a820());
        jo.getJSONObject("attributes").put("威胁户数",zhinfo.getZhbb01a830());
        jo.getJSONObject("attributes").put("威胁人口",zhinfo.getZhbb01a840());
        jo.getJSONObject("attributes").put("威胁土地",zhinfo.getZhbb01a846());
        jo.getJSONObject("attributes").put("威胁其他",zhinfo.getZhbb01a848());
        jo.getJSONObject("attributes").put("威胁资产",zhinfo.getZhbb01a850());
        jo.getJSONObject("attributes").put("灾害危害程度",zhinfo.getZhbb01a860());
        jo.getJSONObject("attributes").put("灾害规模等级",zhinfo.getZhbb01a864());
        jo.getJSONObject("attributes").put("物质组成及结构特征",zhinfo.getZhbb01a878());
        jo.getJSONObject("attributes").put("水文地质条件",zhinfo.getZhbb01a872());
        jo.getJSONObject("attributes").put("主要成因及影响因素形成机制分析",zhinfo.getZhbb01a884());
        jo.getJSONObject("attributes").put("平面图",zhinfo.getZhbb01a940());
        jo.getJSONObject("attributes").put("剖面图",zhinfo.getZhbb01a950());
        jo.getJSONObject("attributes").put("现场照片",zhinfo.getZhbb01a960());
        jo.getJSONObject("attributes").put("loc",zhinfo.getZhbb01a071());
        jo.put("symbol",new JSONObject(true));
        if(level.startsWith("警报级")){
            jo.getJSONObject("symbol").put("color",new int[]{225,0,0});
            jo.getJSONObject("attributes").put("warnLabel",1);
        }
        else if(level.startsWith("警戒级")){
            jo.getJSONObject("symbol").put("color",new int[]{255,165,0});
            jo.getJSONObject("attributes").put("warnLabel",2);
        }
        else if(level.startsWith("警示级")){
            jo.getJSONObject("symbol").put("color",new int[]{255,255,0});
            jo.getJSONObject("attributes").put("warnLabel",3);
        }
        else{
            jo.getJSONObject("symbol").put("color",new int[]{0,0,255});
            jo.getJSONObject("attributes").put("warnLabel",4);
        }
        jo.getJSONObject("symbol").put("size",8);
        jo.getJSONObject("symbol").put("angle",0);
        jo.getJSONObject("symbol").put("xoffset",0);
        jo.getJSONObject("symbol").put("yoffset",0);
        jo.getJSONObject("symbol").put("type","esriSMS");
        jo.getJSONObject("symbol").put("style","esriSMSCircle");
        return jo;
    }

//    private JSONObject getWarningLevel(zhinfo item,String level){
//        JSONObject jo = new JSONObject(true);
//        jo.put("geometry",new JSONObject(true));
//        double x = Double.parseDouble(item.getZhbb01a110().toString());
//        double y = Double.parseDouble(item.getZhbb01a120().toString());
//        jo.getJSONObject("geometry").put("x",x);
//        jo.getJSONObject("geometry").put("y",y);
//        jo.getJSONObject("geometry").put("spatialReference",new JSONObject(true));
//        jo.getJSONObject("geometry").getJSONObject("spatialReference").put("wkid",4326);
//        jo.put("attributes",new JSONObject(true));
//        jo.getJSONObject("attributes").put("name",item.getZhbb01a020());
//        jo.getJSONObject("attributes").put("x",x);
//        jo.getJSONObject("attributes").put("y",y);
//        jo.put("symbol",new JSONObject(true));
//        if(level.startsWith("警报级")){
//            jo.getJSONObject("symbol").put("color",new int[]{225,0,0});
//        }
//        else if(level.startsWith("警戒级")){
//            jo.getJSONObject("symbol").put("color",new int[]{255,165,0});
//        }
//        else if(level.startsWith("警示级")){
//            jo.getJSONObject("symbol").put("color",new int[]{255,255,0});
//        }
//        else{
//            jo.getJSONObject("symbol").put("color",new int[]{0,0,255});
//        }
//        jo.getJSONObject("symbol").put("size",6);
//        jo.getJSONObject("symbol").put("angle",0);
//        jo.getJSONObject("symbol").put("xoffset",0);
//        jo.getJSONObject("symbol").put("yoffset",0);
//        jo.getJSONObject("symbol").put("type","esriSMS");
//        jo.getJSONObject("symbol").put("style","esriSMSCircle");
//        jo.put("infoTemplate",new JSONObject(true));
//        jo.getJSONObject("infoTemplate").put("title","${name}");
//        jo.getJSONObject("infoTemplate").put("content","经度：${x} <br/> 纬度：${y}");
//        return jo;
//    }
    public JSONArray getDisasterInfo(){
        List<zhinfo> zhinfos = zhinfoDao.queryByAll();
        return zhinfoToArray(zhinfos);
    }

    public JSONObject queryRianbefore10(String date,double x,double y){
        JSONObject ans = new JSONObject(true);
        JSONArray hans = new JSONArray();
        JSONArray zans = new JSONArray();
        for(int i=-9;i<1;++i){
            String xaxis = getSpecialdayOffset(date,i);
            Double rainvalue = rainDataService.getXYraindata1(xaxis,x,y);
            if(rainvalue!=null){
                zans.add(rainvalue);
            }
            else zans.add(0);
            hans.add(xaxis);
        }
        ans.put("hans",hans);
        ans.put("zans",zans);
        return ans;
    }

    public JSONObject queryRianafter10(String date,double x,double y){
        JSONObject ans = new JSONObject(true);
        JSONArray hans = new JSONArray();
        JSONArray zans = new JSONArray();
        for(int i=1;i<11;++i){
            String xaxis = getSpecialdayOffset(date,i);
            Double rainvalue = rainDataService.getXYraindata1(xaxis,x,y);
            if(rainvalue!=null){
                zans.add(rainvalue);
            }
            else zans.add(0);
            hans.add(xaxis);
        }
        ans.put("hans",hans);
        ans.put("zans",zans);
        return ans;
    }

    public JSONObject querySWbefore10(String loc,String date){
        JSONObject ans =null;
        String toswz;
        if(loc.startsWith("秭归"))toswz="凤凰山（常规）W2Q水位";
        else if(loc.startsWith("兴山"))toswz="兴山浮子水位";
        else if(loc.startsWith("巴东"))toswz="巴东大量程水位";
        else return ans;
        ans = new JSONObject(true);
        JSONArray hans = new JSONArray();
        JSONArray zans = new JSONArray();
        for(int i=-9;i<1;++i){
            String xaxis = getSpecialdayOffset(date,i);
            jcdb24a jcdb24aEnti= jcdb24aDao.queryBytimeandloc(xaxis,toswz);
            if(jcdb24aEnti!=null){
                zans.add(jcdb24aEnti.getJcdb24a050());
            }
            else zans.add(0);
            hans.add(xaxis);
        }
        ans.put("zans",zans);
        return ans;
    }

    private String getSpecialdayOffset(String time,int n){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date date = sdf.parse(time, new ParsePosition(0));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, n);
        Date date1 = calendar.getTime();
        return sdf.format(date1);
    }
    private JSONArray zhinfoToArray(List<zhinfo> zhinfos){
        JSONArray ans = new JSONArray();
        for(zhinfo item:zhinfos){
            JSONObject jo = new JSONObject(true);
            jo.put("geometry",new JSONObject(true));
            double x = Double.parseDouble(item.getZhbb01a110().toString());
            double y = Double.parseDouble(item.getZhbb01a120().toString());
            jo.getJSONObject("geometry").put("x",x);
            jo.getJSONObject("geometry").put("y",y);
            jo.getJSONObject("geometry").put("spatialReference",new JSONObject(true));
            jo.getJSONObject("geometry").getJSONObject("spatialReference").put("wkid",4326);
            jo.put("attributes",new JSONObject(true));
            jo.getJSONObject("attributes").put("灾害点名称",item.getZhbb01a020());
            jo.getJSONObject("attributes").put("death",item.getZhbb01a810());
            jo.getJSONObject("attributes").put("hurt",item.getZhbb01a815());
            jo.getJSONObject("attributes").put("ecoloss",item.getZhbb01a820());
            jo.getJSONObject("attributes").put("威胁户数",item.getZhbb01a830());
            jo.getJSONObject("attributes").put("威胁人口",item.getZhbb01a840());
            jo.getJSONObject("attributes").put("威胁土地",item.getZhbb01a846());
            jo.getJSONObject("attributes").put("威胁其他",item.getZhbb01a848());
            jo.getJSONObject("attributes").put("威胁资产",item.getZhbb01a850());
            jo.getJSONObject("attributes").put("灾害危害程度",item.getZhbb01a860());
            jo.getJSONObject("attributes").put("灾害规模等级",item.getZhbb01a864());
            jo.getJSONObject("attributes").put("物质组成及结构特征",item.getZhbb01a878());
            jo.getJSONObject("attributes").put("水文地质条件",item.getZhbb01a872());
            jo.getJSONObject("attributes").put("主要成因及影响因素形成机制分析",item.getZhbb01a884());
            jo.getJSONObject("attributes").put("平面图",item.getZhbb01a940());
            jo.getJSONObject("attributes").put("剖面图",item.getZhbb01a950());
            jo.getJSONObject("attributes").put("现场照片",item.getZhbb01a960());
            jo.getJSONObject("attributes").put("loc",item.getZhbb01a071());
            jo.put("symbol",new JSONObject(true));
            jo.getJSONObject("symbol").put("color",new JSONArray());
            jo.getJSONObject("symbol").getJSONArray("color").add(65);
            jo.getJSONObject("symbol").getJSONArray("color").add(105);
            jo.getJSONObject("symbol").getJSONArray("color").add(225);
//            jo.getJSONObject("symbol").getJSONArray("color").add(128);
            jo.getJSONObject("symbol").put("size",6);
            jo.getJSONObject("symbol").put("angle",0);
            jo.getJSONObject("symbol").put("xoffset",0);
            jo.getJSONObject("symbol").put("yoffset",0);
            jo.getJSONObject("symbol").put("type","esriSMS");
            jo.getJSONObject("symbol").put("style","esriSMSCircle");
            ans.add(jo);
        }
        return ans;
    }
}
