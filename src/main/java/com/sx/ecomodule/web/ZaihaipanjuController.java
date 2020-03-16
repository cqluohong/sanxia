package com.sx.ecomodule.web;

import com.sx.ecomodule.entity.Shuiwei;
import com.sx.ecomodule.model.ZaihaidianAttribute;
import com.sx.ecomodule.web.service.RainDataService;
import com.sx.ecomodule.web.service.ShuiweiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ZaihaipanjuController {
    @Autowired
    private RainDataService rainDataService;
    @Autowired
    private ShuiweiService shuiweiService;
    @PostMapping("/getzaihaidianColor")
    public Object getzaihaidianColor(@RequestBody List<ZaihaidianAttribute> zas){
        zas.forEach(item->System.out.println(item.toString()));
        for(ZaihaidianAttribute item:zas){
            Double jyl = rainDataService.getXYraindata(item.getTime(),item.getX(),item.getY());
            Double sw = shuiweiService.querySwBytime(item.getTime());
        }
        return "s";
    }
}
