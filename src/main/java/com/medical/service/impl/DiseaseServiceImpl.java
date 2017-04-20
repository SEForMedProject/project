package com.medical.service.impl;

import com.huaban.analysis.jieba.JiebaSegmenter;
import com.medical.model.DiseaseForSearch;
import com.medical.model.jpa.JPADisease;
import com.medical.repository.DiseaseRepository;
import com.medical.service.DiseaseService;
import com.medical.util.IndexSortForDisease;
import com.medical.util.Response;
import com.medical.util.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ewrfcas on 2017/3/14.
 */
@Service
public class DiseaseServiceImpl implements DiseaseService{
    @Autowired
    private transient DiseaseRepository diseaseRepository;
    @Autowired
    private transient IndexSortForDisease indexSortForDisease;
    @Override
    public Response<List<DiseaseForSearch>> getDiseaseByName(String diseaseName){
        diseaseName=filter(diseaseName);
        Response<List<DiseaseForSearch>> response=new Response<>();
        boolean searchByBody=false;
        if(diseaseName==""||diseaseName==null){
            response.setStatus(ResponseStatus.FAIL);
            response.setMessage("输入为空");
            return response;
        }
        try{
            //名词分词
            JiebaSegmenter segmenter = new JiebaSegmenter();
            List<String> strings=segmenter.sentenceProcess(diseaseName);
            List<DiseaseForSearch> diseaseForSearches =new ArrayList<>();
            if(strings.size()!=0){//到bodyPart中匹配
                List<JPADisease> jpaDiseases=new ArrayList<>();
                HashMap<Integer,Integer> hasFlag=new HashMap<>();
                for(String bodyPart:strings){
                    List<JPADisease> jpaDiseasesTemp=diseaseRepository.findByBodypartContaining(bodyPart);
                    for(JPADisease jpaDisease:jpaDiseasesTemp){
                        if(!hasFlag.containsKey(jpaDisease.getId())){
                            jpaDiseases.add(jpaDisease);
                            hasFlag.put(jpaDisease.getId(),1);
                        }
                    }
                }
                if(jpaDiseases.size()!=0){
                    searchByBody=true;
                    for(JPADisease jpaDisease:jpaDiseases){//从符合要求的bodypart中选出合适的疾病
                        if(jpaDisease.getName().contains(diseaseName)){
                            DiseaseForSearch diseaseForSearch =new DiseaseForSearch();
                            diseaseForSearch.setDiseaseId(jpaDisease.getId());
                            diseaseForSearch.setDiseaseName(jpaDisease.getName());
                            diseaseForSearch.setShowDetail(jpaDisease.getName());
                            diseaseForSearch.setIndex(jpaDisease.getSearch_index());
                            diseaseForSearch.setAlias(jpaDisease.getAlias());
                            diseaseForSearch.setBodypart(jpaDisease.getBodypart());
                            diseaseForSearch.setTag("疾病");
                            diseaseForSearches.add(diseaseForSearch);
                        }else if(jpaDisease.getSymptom().contains(diseaseName)){
                            DiseaseForSearch diseaseForSearch =new DiseaseForSearch();
                            diseaseForSearch.setDiseaseId(jpaDisease.getId());
                            diseaseForSearch.setDiseaseName(jpaDisease.getName());
                            String detail=jpaDisease.getSymptom()+"("+jpaDisease.getName()+")";
                            if(detail.length()>50)detail=detail.substring(detail.length()-40,detail.length());
                            diseaseForSearch.setAlias(jpaDisease.getAlias());
                            diseaseForSearch.setBodypart(jpaDisease.getBodypart());
                            diseaseForSearch.setShowDetail(detail);
                            diseaseForSearch.setIndex(jpaDisease.getSearch_index());
                            diseaseForSearch.setTag("症状");
                            diseaseForSearches.add(diseaseForSearch);
                        }
                    }
                }
                if(diseaseForSearches.size()==0)searchByBody=false;
            }
            if(!searchByBody){//全局模糊匹配
                List<JPADisease> jpaDiseaseList1 = diseaseRepository.findByNameContaining(diseaseName);
                List<JPADisease> jpaDiseaseList2 = diseaseRepository.findBySymptomContaining(diseaseName);
                if(jpaDiseaseList1.size()+jpaDiseaseList2.size()==0){
                    //无论怎么样都要搜到~
                    String newName="";
                    for(int j=0;j<diseaseName.length();j++){
                        newName+=diseaseName.substring(j,j+1);
                        if(j==diseaseName.length()-1)break;
                        newName+="%";
                    }
                    List<JPADisease> jpaDiseaseList = diseaseRepository.findAnyWay(newName);
                    if(jpaDiseaseList==null||jpaDiseaseList.size()==0){
                        response.setStatus(ResponseStatus.FAIL);
                        response.setMessage("无该疾病");
                        return response;
                    }
                    for (JPADisease jpaDisease:jpaDiseaseList){
                        DiseaseForSearch diseaseForSearch =new DiseaseForSearch();
                        diseaseForSearch.setDiseaseId(jpaDisease.getId());
                        diseaseForSearch.setDiseaseName(jpaDisease.getName());
                        String detail=jpaDisease.getSymptom()+"("+jpaDisease.getName()+")";
                        if(detail.length()>50)detail=detail.substring(detail.length()-40,detail.length());
                        diseaseForSearch.setAlias(jpaDisease.getAlias());
                        diseaseForSearch.setBodypart(jpaDisease.getBodypart());
                        diseaseForSearch.setShowDetail(detail);
                        diseaseForSearch.setTag("症状");
                        diseaseForSearch.setIndex(jpaDisease.getSearch_index());
                        diseaseForSearches.add(diseaseForSearch);
                    }
                    Collections.sort(diseaseForSearches, indexSortForDisease);
                    response.setData(diseaseForSearches);
                    response.setStatus(ResponseStatus.SUCCESS);
                    response.setMessage("疾病查找成功");
                    return response;
                }
                HashMap<Integer,Integer> hasFlag=new HashMap<>();
                for(JPADisease jpaDisease : jpaDiseaseList1){
                    DiseaseForSearch diseaseForSearch =new DiseaseForSearch();
                    diseaseForSearch.setDiseaseId(jpaDisease.getId());
                    diseaseForSearch.setDiseaseName(jpaDisease.getName());
                    diseaseForSearch.setShowDetail(jpaDisease.getName());
                    diseaseForSearch.setAlias(jpaDisease.getAlias());
                    diseaseForSearch.setBodypart(jpaDisease.getBodypart());
                    diseaseForSearch.setTag("疾病");
                    diseaseForSearch.setIndex(jpaDisease.getSearch_index());
                    hasFlag.put(jpaDisease.getId(),1);
                    diseaseForSearches.add(diseaseForSearch);
                }
                for(JPADisease jpaDisease:jpaDiseaseList2){
                    if(!hasFlag.containsKey(jpaDisease.getId())){
                        DiseaseForSearch diseaseForSearch =new DiseaseForSearch();
                        diseaseForSearch.setDiseaseId(jpaDisease.getId());
                        diseaseForSearch.setDiseaseName(jpaDisease.getName());
                        String detail=jpaDisease.getSymptom()+"("+jpaDisease.getName()+")";
                        if(detail.length()>50)detail=detail.substring(detail.length()-40,detail.length());
                        diseaseForSearch.setShowDetail(detail);
                        diseaseForSearch.setIndex(jpaDisease.getSearch_index());
                        diseaseForSearch.setAlias(jpaDisease.getAlias());
                        diseaseForSearch.setBodypart(jpaDisease.getBodypart());
                        diseaseForSearch.setTag("症状");
                        hasFlag.put(jpaDisease.getId(),1);
                        diseaseForSearches.add(diseaseForSearch);
                    }
                }
            }
            Collections.sort(diseaseForSearches, indexSortForDisease);
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

    @Override
    public Response<JPADisease> getDiseaseDetailById(int diseaseId){
        Response<JPADisease> response=new Response<>();
        try{
            JPADisease jpaDisease=diseaseRepository.findOne(diseaseId);
            if(jpaDisease==null){
                response.setStatus(ResponseStatus.FAIL);
                response.setMessage("未找到该疾病");
                return response;
            }
            response.setData(jpaDisease);
            response.setStatus(ResponseStatus.SUCCESS);
            response.setMessage("疾病数据获取成功");
            return response;
        }catch (Exception e){
            response.setStatus(ResponseStatus.ERROR);
            response.setMessage(e.toString());
            return response;
        }
    }

    private String filter(String name){
       return name.replaceAll("十分|相当|非常|很|有点|有点儿|特别|剧|剧烈","");
    }
}
