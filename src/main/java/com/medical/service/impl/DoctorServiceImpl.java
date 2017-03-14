package com.medical.service.impl;

import com.medical.Repository.DoctorRepository;
import com.medical.model.Doctor;
import com.medical.model.jpa.JPADoctor;
import com.medical.service.DoctorService;
import com.medical.util.Response;
import com.medical.util.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ewrfcas on 2017/3/2.
 */
@Service
public class DoctorServiceImpl implements DoctorService{
    @Autowired
    private transient DoctorRepository doctorRepository;

    @Override
    public Response<Doctor> getDoctorByName(String doctorName){
        Response<Doctor> response=new Response<Doctor>();
        try{
            JPADoctor jpaDoctor= doctorRepository.getDoctorByName(doctorName);
            if(jpaDoctor==null){
                response.setStatus(ResponseStatus.FAIL);
                response.setMessage("未找到该医生");
                return response;
            }
            Doctor doctor=new Doctor();
            doctor.setName(jpaDoctor.getDoctor_name());
            doctor.setAge(jpaDoctor.getDoctor_age());
            response.setData(doctor);
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
