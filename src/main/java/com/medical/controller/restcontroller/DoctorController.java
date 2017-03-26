package com.medical.controller.restcontroller;

import com.medical.model.Doctor;
import com.medical.service.DoctorService;
import com.medical.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by ewrfcas on 2017/3/2.
 */
@SuppressWarnings("unused")
@RestController
public class DoctorController {
    @Autowired
    private transient DoctorService doctorService;

    @RequestMapping(value="/doctor/getDoctorByDiseaseName" ,method = RequestMethod.GET)
    public Response<List<Doctor>> getDoctorByDiseaseName(String diseaseName) {
        return doctorService.getDoctorByDiseaseName(diseaseName);
    }
}
