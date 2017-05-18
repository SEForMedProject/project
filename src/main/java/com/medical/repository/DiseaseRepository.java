package com.medical.repository;

import com.medical.model.jpa.JPADisease;
import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD
import org.springframework.stereotype.Repository;
=======
import org.springframework.data.jpa.repository.Query;
>>>>>>> dev

import java.util.List;

/**
 * Created by ewrfcas on 2017/3/14.
 */
public interface DiseaseRepository extends JpaRepository<JPADisease,Integer> {
    List<JPADisease> findByNameContaining(String name);
    List<JPADisease> findBySymptomContaining(String name);
    List<JPADisease> findByBodypartContaining(String name);
    @Query(value="select * from tb_ill where symptom like %?1% or name like %?1%" ,nativeQuery=true)
    List<JPADisease> findAnyWay(String name);
}
