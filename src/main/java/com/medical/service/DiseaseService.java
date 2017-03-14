package com.medical.service;

import com.medical.model.Disease;
import com.medical.util.Response;

import java.util.List;

/**
 * Created by ewrfcas on 2017/3/14.
 */
@SuppressWarnings("all")
public interface DiseaseService {
    Response<List<Disease>> getDiseaseByName(String diseaseName);
}
