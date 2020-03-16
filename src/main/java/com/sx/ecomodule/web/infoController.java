package com.sx.ecomodule.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sx.ecomodule.web.service.infoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class infoController {
    @Autowired
    private infoService infoService;
    @CrossOrigin
    @GetMapping("/getDisasterInfo")
    public String getDisasterInfo(Model model){
        String test = "测试数据";
        model.addAttribute("test",test);
        return "tabpage";
    }
    @CrossOrigin
    @ResponseBody
    @GetMapping("/getJsonPoint")
    public JSONArray getJsonPoint(){
        return infoService.getDisasterInfo();
    }

    @CrossOrigin
    @ResponseBody
    @GetMapping("/getXYchart")
    public JSONArray getXYchart(@RequestParam  String time, @RequestParam double x, @RequestParam double y,
                           @RequestParam String loc){
        JSONArray ans = new JSONArray();
        ans.add(infoService.queryRianbefore10(time,x,y));
        //这里未空，是因为没有水位站信息
        ans.add(infoService.querySWbefore10(loc,time));
        ans.add(infoService.queryRianafter10(time,x,y));
        return ans;
    }

    @CrossOrigin
    @ResponseBody
    @GetMapping("/getWarnJsonPoint")
    public JSONArray getWarnJsonPoint(String time){
        return infoService.getDisasterForWarning1(time);
    }
}
