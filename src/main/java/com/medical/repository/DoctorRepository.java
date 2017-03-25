package com.medical.repository;

import com.medical.model.jpa.JPADoctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by ewrfcas on 2017/3/2.
 */
public interface DoctorRepository extends JpaRepository<JPADoctor,String> {
    @Query("select d from JPADoctor d where d.doctor_name=?1")
    public JPADoctor getDoctorByName(String doctorName);
}
