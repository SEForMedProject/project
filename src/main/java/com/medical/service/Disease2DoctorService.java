package com.medical.service;

import com.medical.model.DoctorForSeach;
import com.medical.model.jpa.JPADisease;
import com.medical.util.Response;

import java.util.List;

/**
 * Created by winyang on 3/25/17.
 */
public interface Disease2DoctorService {
    Response<List<DoctorForSeach>> getDoctorInfoByDisease(String diseaseName);
}