package com.medical.service.impl;

import com.medical.repository.DiseaseRepository;
import com.medical.model.DiseaseForSearch;
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
    public Response<List<DiseaseForSearch>> getDiseaseByName(String diseaseName){
        Response<List<DiseaseForSearch>> response=new Response<>();
        if(diseaseName==""||diseaseName==null){
            response.setStatus(ResponseStatus.FAIL);
            response.setMessage("输入为空");
            return response;
        }
        try{
            List<JPADisease> jpaDiseaseList = diseaseRepository.findContaining(diseaseName);
            if(jpaDiseaseList.size()==0){
                response.setStatus(ResponseStatus.FAIL);
                response.setMessage("无该疾病");
                return response;
            }
            List<DiseaseForSearch> diseaseForSearches =new ArrayList<>();
            for(JPADisease jpaDisease : jpaDiseaseList){
                DiseaseForSearch diseaseForSearch =new DiseaseForSearch();
                diseaseForSearch.setDiseaseId(jpaDisease.getId());
                diseaseForSearch.setDiseaseName(jpaDisease.getName());
                if(jpaDisease.getName().contains(diseaseName)){
                    diseaseForSearch.setShowDetail(jpaDisease.getName());
                    diseaseForSearch.setTag("疾病");
                }else if(jpaDisease.getSymptom().contains(diseaseName)){
                    diseaseForSearch.setShowDetail(jpaDisease.getSymptom()+"("+jpaDisease.getName()+")");
                    diseaseForSearch.setTag("症状");
                }else if(jpaDisease.getBody_part().contains(diseaseName)){
                    diseaseForSearch.setShowDetail(jpaDisease.getBody_part()+"("+jpaDisease.getName()+")");
                    diseaseForSearch.setTag("身体部位");
                }else{
                    diseaseForSearch.setShowDetail(jpaDisease.getName());
                    diseaseForSearch.setTag("未知");
                }
                diseaseForSearches.add(diseaseForSearch);
            }
            response.setData(diseaseForSearches);
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
