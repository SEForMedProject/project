package com.medical.service;

import com.medical.model.Doctor;
import com.medical.util.Response;

import java.util.List;

/**
 * Created by ewrfcas on 2017/3/2.
 */
@SuppressWarnings("all")
public interface DoctorService {
    Response<List<Doctor>> getDoctorByDiseaseName(String diseaseName);
}
