package com.medical.dao;

import com.medical.model.jpa.JPADoctor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by ewrfcas on 2017/3/2.
 */
public interface DoctorDao extends PagingAndSortingRepository<JPADoctor,String> {
    @Query("select d from JPADoctor d where d.doctor_name=?1")
    public JPADoctor getDoctorByName(String doctorName);
}
