package com.medical.service;

import com.medical.model.DiseaseForSearch;
import com.medical.model.jpa.JPADisease;
import com.medical.util.Response;

import java.util.List;

/**
 * Created by ewrfcas on 2017/3/14.
 */
@SuppressWarnings("all")
public interface DiseaseService {
    Response<List<DiseaseForSearch>> getDiseaseByName(String diseaseName,int flag);
    Response<JPADisease> getDiseaseDetailById(int diseaseId);
}
