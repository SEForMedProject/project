package com.medical.controller.restcontroller;

import com.medical.model.DiseaseForSearch;
import com.medical.service.DiseaseService;
import com.medical.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by ewrfcas on 2017/3/14.
 */
@SuppressWarnings("unused")
@RestController
public class DiseaseController {
    @Autowired
    private transient DiseaseService diseaseService;

    @RequestMapping(value="/search/getDiseaseByName" ,method = RequestMethod.GET)
    public Response<List<DiseaseForSearch>> getDiseaseByName(String diseaseName) {
        return diseaseService.getDiseaseByName(diseaseName);
    }
}
