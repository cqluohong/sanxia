package com.sx.ecomodule.web.service;

import com.sx.ecomodule.dao.RaindataMapper;
import com.sx.ecomodule.model.RainModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class RainDataService {
    @Autowired
    RaindataMapper raindataMapper;
    @Transactional
    public Double getXYraindata(String time , double x , double y){
        Double inversedistanceSum = raindataMapper.getInversedistanceSum(time,x,y);
        Double rainxydata =null;
        if(inversedistanceSum!=null){
            rainxydata = raindataMapper.getIdwValue(inversedistanceSum,time,x,y);
        }
        return rainxydata;
    }
    public Double getXYraindata1(String time,double x , double y){
        List<RainModel> rainModels = raindataMapper.getzhandianRainBytime(time);
        if(rainModels.size()==0)return null;
        double ans =0,tmp;
        for(RainModel item: rainModels){
            tmp = 1/(Math.pow(x-item.getX(),2)+Math.pow(y-item.getY(),2));
            item.setW(tmp);
            ans+=tmp;
        }
        double rans =0;
        for(RainModel item: rainModels){
            rans += item.getW()/ans*item.getRainValue();
        }
        return rans;
    }
}
