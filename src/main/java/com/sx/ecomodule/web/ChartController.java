package com.sx.ecomodule.web;

import com.alibaba.fastjson.JSONObject;
import com.sx.ecomodule.entity.Shuiwei;
import com.sx.ecomodule.web.service.ShuiweiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ChartController {
    @Autowired
    private ShuiweiService shuiweiService;
    @GetMapping("/getShuiweiBytime")
    public JSONObject getShuiweiChart(int page,int limit, String zhandian,String starttime, String endtime){
        return shuiweiService.getSwChartSerarch(page,limit,zhandian,starttime,endtime);
    }
    @GetMapping("/getshuiweiEchart")
    public JSONObject getshuiweiEchart(String zhandian,String starttime, String endtime){
        return shuiweiService.getSwChartBynopage(zhandian,starttime,endtime);
    }
}
