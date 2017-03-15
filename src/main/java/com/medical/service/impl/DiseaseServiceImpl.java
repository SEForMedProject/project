package com.medical.service.impl;

import com.medical.repository.DiseaseRepository;
import com.medical.model.Disease;
import com.medical.model.jpa.JPADisease;
import com.medical.service.DiseaseService;
import com.medical.util.Response;
import com.medical.util.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ewrfcas on 2017/3/14.
 */
@Service
public class DiseaseServiceImpl implements DiseaseService{
    @Autowired
    private transient DiseaseRepository diseaseRepository;
    @Override
    public Response<List<Disease>> getDiseaseByName(String diseaseName){
        Response<List<Disease>> response=new Response<>();
        if(diseaseName==""||diseaseName==null){
            response.setStatus(ResponseStatus.FAIL);
            response.setMessage("输入为空");
            return response;
        }
        try{
            List<JPADisease> jpaDiseaseList = diseaseRepository.findByNameContaining(diseaseName);
            if(jpaDiseaseList.size()==0){
                response.setStatus(ResponseStatus.FAIL);
                response.setMessage("无该疾病");
                return response;
            }
            List<Disease> diseases=new ArrayList<>();
            for(JPADisease jpaDisease : jpaDiseaseList){
                Disease disease=new Disease();
                disease.setDiseaseId(jpaDisease.getId());
                disease.setDiseaseName(jpaDisease.getName());
                diseases.add(disease);
            }
            response.setData(diseases);
            response.setStatus(ResponseStatus.SUCCESS);
            response.setMessage("疾病数据获取成功");
            return response;
        }catch (Exception e){
            response.setStatus(ResponseStatus.ERROR);
            response.setMessage(e.toString());
            return response;
        }
    }
}
