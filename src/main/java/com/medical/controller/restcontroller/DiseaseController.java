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
import java.util.HashMap;
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

    @RequestMapping(value = "/search/getDiseaseByName", method = RequestMethod.GET)
    public Response<List<DiseaseForSearch>> getDiseaseByName(String diseaseName) {
        Response<List<DiseaseForSearch>> response=new Response<>();
        List<DiseaseForSearch> diseaseForSearches=new ArrayList<>();
        String[] diseaseNames=diseaseName.split(" ");
        List<DiseaseForSearch> diseaseForSearchesReal=new ArrayList<>();
        HashMap<Integer,Integer> hashMap=new HashMap<>();
        int maxNum=0;
        for (String s: diseaseNames){//hashmap中统计各疾病在各关键词中出现次数
            Response<List<DiseaseForSearch>> responseTemp=diseaseService.getDiseaseByName(s);
            for(DiseaseForSearch diseaseForSearch:responseTemp.getData()){
                int tempNum=hashMap.getOrDefault(diseaseForSearch.getDiseaseId(),0)+1;
                hashMap.put(diseaseForSearch.getDiseaseId(),tempNum);
                if(tempNum>maxNum)maxNum=tempNum;
                if(tempNum==1)diseaseForSearchesReal.add(diseaseForSearch);
            }
        }
        //判断是否是最大maxnum
        for(DiseaseForSearch diseaseForSearch:diseaseForSearchesReal){
            if(hashMap.get(diseaseForSearch.getDiseaseId())==maxNum)diseaseForSearches.add(diseaseForSearch);
        }
        Collections.sort(diseaseForSearches, indexSortForDisease);
        response.setStatus(ResponseStatus.SUCCESS);
        response.setMessage("疾病信息获取成功");
        response.setData(diseaseForSearches);
        return response;
    }

    @RequestMapping(value = "/disease/getDiseaseDetailById", method = RequestMethod.GET)
    public Response<JPADisease> getDiseaseDetailById(int diseaseId) {
        return diseaseService.getDiseaseDetailById(diseaseId);
    }
}
