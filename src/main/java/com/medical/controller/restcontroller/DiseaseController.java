package com.medical.controller.restcontroller;

import com.medical.model.DiseaseForSearch;
import com.medical.model.jpa.JPADisease;
import com.medical.service.DiseaseService;
import com.medical.util.IndexSortForDisease;
import com.medical.util.Response;
import com.medical.util.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ewrfcas on 2017/3/14.
 */
@SuppressWarnings("unused")
@RestController
public class DiseaseController {
    @Autowired
    private transient DiseaseService diseaseService;
    @Autowired
    private transient IndexSortForDisease indexSortForDisease;

    @RequestMapping(value="/search/getDiseaseByName" ,method = RequestMethod.GET)
    public Response<List<DiseaseForSearch>> getDiseaseByName(String diseaseName) {
        Response<List<DiseaseForSearch>> response=new Response<>();
        List<DiseaseForSearch> diseaseForSearches=new ArrayList<>();
//        if (diseaseName.contains("痛")||(diseaseName.contains("疼"))){
//            List<DiseaseForSearch> diseaseForSearches1=diseaseService.getDiseaseByName(diseaseName).getData();
//            if(diseaseForSearches1!=null&&diseaseForSearches1.size()!=0)diseaseForSearches.addAll(diseaseForSearches1);
//            if(diseaseName.contains("痛")){
//                diseaseName=diseaseName.replaceAll("痛","疼");
//            }else{
//                diseaseName=diseaseName.replaceAll("疼","痛");
//            }
//            diseaseForSearches1=diseaseService.getDiseaseByName(diseaseName).getData();
//            if(diseaseForSearches1!=null&&diseaseForSearches1.size()!=0)diseaseForSearches.addAll(diseaseForSearches1);
//            Collections.sort(diseaseForSearches, indexSortForDisease);
//            response.setStatus(ResponseStatus.SUCCESS);
//            response.setMessage("success");
//            response.setData(diseaseForSearches);
//            return response;
//        }
        String[] diseaseNames=diseaseName.split(" ");
        if(diseaseNames.length==1){
            return diseaseService.getDiseaseByName(diseaseName);
        }
        List<Response<List<DiseaseForSearch>>> responseList=new ArrayList<>();
        for (String s: diseaseNames){
            Response<List<DiseaseForSearch>> responseTemp=diseaseService.getDiseaseByName(s);
            responseList.add(responseTemp);
        }
        for (DiseaseForSearch diseaseForSearch: responseList.get(0).getData()){
            boolean flag=false;
            for (int i=1;i<responseList.size();i++){
                if(responseList.get(i).getData().contains(diseaseForSearch)){
                    flag=true;
                    break;
                }
            }
            if(flag){
                diseaseForSearches.add(diseaseForSearch);
            }
        }
        if(diseaseForSearches.size()==0){
            for(int i=0;i<responseList.size();i++){
                diseaseForSearches.addAll(responseList.get(i).getData());
            }
        }
        Collections.sort(diseaseForSearches, indexSortForDisease);
        response.setStatus(ResponseStatus.SUCCESS);
        response.setMessage("success");
        response.setData(diseaseForSearches);
        return response;
    }

    @RequestMapping(value = "/disease/getDiseaseDetailById", method = RequestMethod.GET)
    public Response<JPADisease> getDiseaseDetailById(int diseaseId){
        return diseaseService.getDiseaseDetailById(diseaseId);
    }
}
