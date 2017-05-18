package com.medical.repository;

import com.medical.model.jpa.JPADisease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ewrfcas on 2017/3/14.
 */
public interface DiseaseRepository extends JpaRepository<JPADisease,Integer> {
    List<JPADisease> findByNameContaining(String name);
    List<JPADisease> findBySymptomContaining(String name);
}
