package com.sx.ecomodule.web;

import com.alibaba.fastjson.JSONObject;
import com.sx.ecomodule.entity.qxz;
import com.sx.ecomodule.web.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DataController {
    @Autowired
    private qxzService qxzservice;
    @Autowired
    private QxzrService qxzrservice;
    @Autowired
    private PanjuService panjuService;
    @Autowired
    private ShuiweiService shuiweiService;
    @Autowired
    private RainDataService rainDataService;
    @GetMapping("/getQxzData")
    public JSONObject getQxzData(@RequestParam int page,@RequestParam int limit){
        return qxzservice.selectBypage(page,limit);
    }

    @PostMapping("/qxzAdd")
    public String qxzAdd(@RequestBody qxz qxz){
        qxzservice.insert(qxz);
        return "success";
    }
    @PostMapping("/qxzUpdate")
    public String qxzUpdate(@RequestBody qxz qxz){
        qxzservice.updateByPrimaryKey(qxz);
        return "success";
    }
    @PostMapping("/qxzDelete")
    public String qxzDelete(@RequestBody qxz qxz){
        qxzservice.deleteByPrimaryKey(qxz.getQxaa01a010());
        return "success";
    }
    @GetMapping("/qxzSearch")
    public JSONObject qxzSearch(@RequestParam String searchstr){
        return qxzservice.selectByfuzzy(searchstr);
    }
    @GetMapping("/getQxzrData")
    public JSONObject getQxzrData(@RequestParam int page,@RequestParam int limit){
        return qxzrservice.selectBypage(page,limit);
    }
//    @PostMapping("/qxzrUpdate")
//    public String qxzUpdate(@RequestBody Qxzr qxzr){
//        qxzrservice.updateByPrimaryKey(qxzr);
//        return "success";
//    }
    @GetMapping("/getPanjuData")
    public JSONObject getPanjuData(@RequestParam int page,@RequestParam int limit){
        return panjuService.selectBypage(page,limit);
    }
    @GetMapping("/getShuiweiData")
    public JSONObject ShuiweiData(@RequestParam int page,@RequestParam int limit){
        return shuiweiService.selectBypage(page,limit);
    }
    @GetMapping("/getXYraindata")
    public double getXYraindata(String time,double x,double y){
        return rainDataService.getXYraindata(time, x, y);
    }
}
