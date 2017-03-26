package com.medical.service;

import com.medical.model.DoctorForSearch;
import com.medical.util.Response;

import java.util.List;

/**
 * Created by winyang on 3/25/17.
 */
@SuppressWarnings("all")
public interface Disease2DoctorService {
    Response<List<DoctorForSearch>>getDoctorByDiseaseName(String diseaseName);
}
