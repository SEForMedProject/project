package com.medical.service.impl;

import com.medical.model.Doctor;
import com.medical.model.jpa.JPADoctor;
import com.medical.repository.DoctorRepository;
import com.medical.service.DoctorService;
import com.medical.util.Response;
import com.medical.util.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ewrfcas on 2017/3/2.
 */
@Service
public class DoctorServiceImpl implements DoctorService{
    @Autowired
    private transient DoctorRepository doctorRepository;

    @Override
    public Response<List<Doctor>> getDoctorByDiseaseName(String diseaseName){
        Response<List<Doctor>> response=new Response<>();
        try{
            List<JPADoctor> jpaDoctors= doctorRepository.findByContaining(diseaseName);
            if(jpaDoctors.size()==0){
                response.setStatus(ResponseStatus.FAIL);
                response.setMessage("未找到该医生");
                return response;
            }
            List<Doctor> doctors=new ArrayList<>();
            for(JPADoctor jpaDoctor:jpaDoctors){
                Doctor doctor=new Doctor();
                doctor.setId(jpaDoctor.getId());
                doctor.setName(jpaDoctor.getName());
                doctor.setGoodAt(jpaDoctor.getGoodAt());
                doctor.setTitle(jpaDoctor.getTitle());
                doctor.setIntroduction(jpaDoctor.getIntroduction());
                doctors.add(doctor);
            }
            response.setData(doctors);
            response.setStatus(ResponseStatus.SUCCESS);
            response.setMessage("医生数据获取成功");
            return response;
        }catch (Exception e){
            response.setStatus(ResponseStatus.ERROR);
            response.setMessage(e.toString());
            return response;
        }
    }
}
