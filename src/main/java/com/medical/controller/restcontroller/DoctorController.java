package com.medical.controller.restcontroller;

import com.medical.model.Doctor;
import com.medical.model.DoctorForSearch;
import com.medical.service.Disease2DoctorService;
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
    private transient Disease2DoctorService disease2DoctorService;
    //测试接口
    @RequestMapping(value="/doctor/getDoctorByName" ,method = RequestMethod.GET)
    public Response<Doctor> getDoctorByName(String doctorName) {
        return doctorService.getDoctorByName(doctorName);
    }

    @RequestMapping(value="/doctor/getDoctorInfoByDiseaseName" ,method = RequestMethod.GET)
    public Response<List<DoctorForSearch>> getDiseaseDetailById(String diseaseName){
        return disease2DoctorService.getDoctorByDiseaseName(diseaseName);
    }
}
