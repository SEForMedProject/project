package com.medical.service;

import com.medical.model.Doctor;
import com.medical.util.Response;

/**
 * Created by ewrfcas on 2017/3/2.
 */
@SuppressWarnings("all")
public interface DoctorService {
    Response<Doctor> getDoctorByName(String doctorName);
}
