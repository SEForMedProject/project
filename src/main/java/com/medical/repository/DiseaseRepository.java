package com.medical.repository;

import com.medical.model.jpa.JPADisease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by ewrfcas on 2017/3/14.
 */
public interface DiseaseRepository extends JpaRepository<JPADisease,String> {
    @Query(value="select * from tb_ill where name like %?1% or symptom like %?1% or body_part like %?1%" ,nativeQuery=true)
    List<JPADisease> findContaining(String name);
}
