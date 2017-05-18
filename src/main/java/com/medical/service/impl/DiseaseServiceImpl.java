package com.medical.service.impl;

import com.huaban.analysis.jieba.JiebaSegmenter;
import com.medical.model.DiseaseForSearch;
import com.medical.model.jpa.JPADisease;
import com.medical.repository.DiseaseRepository;
import com.medical.service.DiseaseService;
import com.medical.util.IndexSortForDisease;
import com.medical.util.Response;
import com.medical.util.ResponseStatus;
import com.medical.util.Standardization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ewrfcas on 2017/3/14.
 */
@Service
public class DiseaseServiceImpl implements DiseaseService {

    @Autowired
    private transient DiseaseRepository diseaseRepository;
<<<<<<< HEAD

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Response<List<DiseaseForSearch>> getDiseaseByName(String diseaseName) {
//        stringRedisTemplate.opsForValue().set("wdx", "gsj");
//        System.out.println(stringRedisTemplate.opsForValue().get("wdx"));
        Response<List<DiseaseForSearch>> response = new Response<>();
        if (diseaseName == "" || diseaseName == null) {
=======
    @Autowired
    private transient IndexSortForDisease indexSortForDisease;
    @Autowired
    private transient Standardization standardization;
    @Override
    public Response<List<DiseaseForSearch>> getDiseaseByName(String diseaseName){
        diseaseName=standardization.filter(diseaseName);//输入标准化处理
        Response<List<DiseaseForSearch>> response=new Response<>();
        if(diseaseName==""||diseaseName==null){
            response.setData(null);
>>>>>>> dev
            response.setStatus(ResponseStatus.FAIL);
            response.setMessage("输入为空");
            return response;
        }
<<<<<<< HEAD
        try {
            List<JPADisease> jpaDiseaseList1 = diseaseRepository.findByNameContaining(diseaseName);
            List<JPADisease> jpaDiseaseList2 = diseaseRepository.findBySymptomContaining(diseaseName);
            if (jpaDiseaseList1.size() + jpaDiseaseList2.size() == 0) {
                response.setStatus(ResponseStatus.FAIL);
                response.setMessage("无该疾病");
                return response;
            }
            HashMap<Integer, Integer> hasFlag = new HashMap<>();
            List<DiseaseForSearch> diseaseForSearches = new ArrayList<>();
            for (JPADisease jpaDisease : jpaDiseaseList1) {
                DiseaseForSearch diseaseForSearch = new DiseaseForSearch();
                diseaseForSearch.setDiseaseId(jpaDisease.getId());
                diseaseForSearch.setDiseaseName(jpaDisease.getName());
                diseaseForSearch.setShowDetail(jpaDisease.getName());
                diseaseForSearch.setTag("疾病");
                hasFlag.put(jpaDisease.getId(), 1);
                diseaseForSearches.add(diseaseForSearch);
            }
            for (JPADisease jpaDisease : jpaDiseaseList2) {
                if (!hasFlag.containsKey(jpaDisease.getId())) {
                    DiseaseForSearch diseaseForSearch = new DiseaseForSearch();
                    diseaseForSearch.setDiseaseId(jpaDisease.getId());
                    diseaseForSearch.setDiseaseName(jpaDisease.getName());
                    diseaseForSearch.setShowDetail(jpaDisease.getSymptom() + "(" + jpaDisease.getName() + ")");
                    diseaseForSearch.setTag("症状");
                    hasFlag.put(jpaDisease.getId(), 1);
                    diseaseForSearches.add(diseaseForSearch);
                }
=======
        try{
            //名词分词
            JiebaSegmenter segmenter = new JiebaSegmenter();
            List<String> strings=segmenter.sentenceProcess(diseaseName);
            List<DiseaseForSearch> diseaseForSearches =new ArrayList<>();
            if(strings.size()!=0){//先到bodyPart中匹配
                diseaseForSearches=FindByBodypart(strings,diseaseName);
            }
            if(diseaseForSearches==null||diseaseForSearches.size()==0){//身体查询失败，转为全局模糊匹配
                diseaseForSearches=GlobalSearch(diseaseName);
>>>>>>> dev
            }
            if(diseaseForSearches==null||diseaseForSearches.size()==0){//2种查询全部失败
                response.setData(null);
                response.setStatus(ResponseStatus.FAIL);
                response.setMessage("没有查到该数据");
                return response;
            }
            Collections.sort(diseaseForSearches, indexSortForDisease);//疾病排序
            response.setData(diseaseForSearches);
            response.setStatus(ResponseStatus.SUCCESS);
            response.setMessage("疾病数据获取成功");
            return response;
        } catch (Exception e) {
            response.setStatus(ResponseStatus.ERROR);
            response.setMessage(e.toString());
            return response;
        }
    }

    @Override
    public Response<JPADisease> getDiseaseDetailById(int diseaseId) {
        Response<JPADisease> response = new Response<>();
        try {
            JPADisease jpaDisease = diseaseRepository.findOne(diseaseId);
            if (jpaDisease == null) {
                response.setStatus(ResponseStatus.FAIL);
                response.setMessage("未找到该疾病");
                return response;
            }
            response.setData(jpaDisease);
            response.setStatus(ResponseStatus.SUCCESS);
            response.setMessage("疾病数据获取成功");
            return response;
        } catch (Exception e) {
            response.setStatus(ResponseStatus.ERROR);
            response.setMessage(e.toString());
            return response;
        }
    }

    //SQLJPA格式转换为前后端通用格式DiseaseForSearch
    private DiseaseForSearch JPAToDiseaseForSearch(JPADisease jpaDisease,int flag){//flag==1时候是疾病，flag==2时候是症状
        DiseaseForSearch diseaseForSearch =new DiseaseForSearch();
        diseaseForSearch.setDiseaseId(jpaDisease.getId());
        diseaseForSearch.setDiseaseName(jpaDisease.getName());
        diseaseForSearch.setIndex(jpaDisease.getSearch_index_new());
        diseaseForSearch.setAlias(jpaDisease.getAlias());
        diseaseForSearch.setBodypart(jpaDisease.getBodypart());
        if(flag==1){
            diseaseForSearch.setShowDetail(jpaDisease.getName());
            diseaseForSearch.setTag("疾病");
        }else if(flag==2){
            String detail=jpaDisease.getSymptom();
            if(detail.length()>50)detail=detail.substring(detail.length()-40,detail.length());
            diseaseForSearch.setShowDetail(detail);
            diseaseForSearch.setTag("症状");
        }
        return diseaseForSearch;
    }

    //身体部位bodypart查询
    private List<DiseaseForSearch> FindByBodypart(List<String> strings,String diseaseName){
        List<DiseaseForSearch> diseaseForSearches=new ArrayList<>();
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
            for(JPADisease jpaDisease:jpaDiseases){//从符合要求的bodypart中选出合适的疾病
                if(jpaDisease.getName().contains(diseaseName)){
                    diseaseForSearches.add(JPAToDiseaseForSearch(jpaDisease,1));
                }else if(jpaDisease.getSymptom().contains(diseaseName)){
                    diseaseForSearches.add(JPAToDiseaseForSearch(jpaDisease,2));
                }
            }
        }
        return diseaseForSearches;
    }

    //全局模糊匹配
    private List<DiseaseForSearch> GlobalSearch(String diseaseName){
        List<DiseaseForSearch> diseaseForSearches=new ArrayList<>();
        List<JPADisease> jpaDiseaseList1 = diseaseRepository.findByNameContaining(diseaseName);
        List<JPADisease> jpaDiseaseList2 = diseaseRepository.findBySymptomContaining(diseaseName);
        if(jpaDiseaseList1.size()+jpaDiseaseList2.size()==0){
            //全局没有搜索到，放宽标准，逐字拆开匹配
            String newName="";
            for(int j=0;j<diseaseName.length();j++){
                newName+=diseaseName.substring(j,j+1);
                if(j==diseaseName.length()-1)break;
                newName+="%";//中间加%
            }
            List<JPADisease> jpaDiseaseList = diseaseRepository.findAnyWay(newName);
            if(jpaDiseaseList==null||jpaDiseaseList.size()==0){
                return diseaseForSearches;
            }
            for (JPADisease jpaDisease:jpaDiseaseList){
                diseaseForSearches.add(JPAToDiseaseForSearch(jpaDisease,2));
            }
            return diseaseForSearches;
        }
        HashMap<Integer,Integer> hasFlag=new HashMap<>();
        for(JPADisease jpaDisease : jpaDiseaseList1){
            hasFlag.put(jpaDisease.getId(),1);
            diseaseForSearches.add(JPAToDiseaseForSearch(jpaDisease,2));
        }
        for(JPADisease jpaDisease:jpaDiseaseList2){
            if(!hasFlag.containsKey(jpaDisease.getId())){
                hasFlag.put(jpaDisease.getId(),1);
                diseaseForSearches.add(JPAToDiseaseForSearch(jpaDisease,2));
            }
        }
        return diseaseForSearches;
    }
}
