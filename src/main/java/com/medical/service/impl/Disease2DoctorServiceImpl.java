package com.medical.service.impl;

import com.medical.model.DoctorForSearch;


import com.medical.service.Disease2DoctorService;
import org.springframework.stereotype.Service;
import com.medical.util.Response;
import com.medical.util.ResponseStatus;

import com.winyang.Repository.DoctorRepository;

import java.util.List;

import static java.sql.DriverManager.println;


/**
 * Created by winyang on 3/25/17.
 *获取疾病名字然后使用疾病名字模糊匹配医生表中good_at字段 返回模糊匹配结果的医生的
 * 姓名, 擅长 , 介绍,
 *
 */
@Service
public class Disease2DoctorServiceImpl implements Disease2DoctorService {

    private  DoctorRepository doctorRepository = new DoctorRepository();

    @Override
    public Response<List<DoctorForSearch>> getDoctorByDiseaseName(String diseaseName) {
        Response<List<DoctorForSearch>> response = new Response<>();
        if (diseaseName == "" || diseaseName == null) {
            response.setStatus(ResponseStatus.FAIL);
            response.setMessage("输入为空");
            return response;
        }
        try {
            //获取数据
            List<DoctorForSearch> doctorForSeaches = doctorRepository.findBygood_atLike(diseaseName);
            //关闭连接
            doctorRepository.mysqlTool().closeConn();

            if (doctorForSeaches.isEmpty()) {
                response.setStatus(ResponseStatus.FAIL);
                response.setMessage("无该医生");
                return response;
            }
            response.setData(doctorForSeaches);
            response.setStatus(ResponseStatus.SUCCESS);
            response.setMessage("医生推荐获取成功");
            println("医生推荐获取成功");
            return response;
        } catch (Exception e) {
            response.setStatus(ResponseStatus.ERROR);
            response.setMessage(e.toString());
            return response;
        }
    }
}
