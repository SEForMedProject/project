package com.medical.repository;

import com.medical.model.jpa.JPADoctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by ewrfcas on 2017/3/2.
 */
public interface DoctorRepository extends JpaRepository<JPADoctor,Integer> {
    @Query(value="select * from tb_doctor where good_at like %?1% or introduction LIKE %?1%" ,nativeQuery=true)
    List<JPADoctor> findByContaining(String diseaseName);
}
