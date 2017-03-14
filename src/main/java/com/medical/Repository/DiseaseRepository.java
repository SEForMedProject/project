package com.medical.Repository;

import com.medical.model.jpa.JPADisease;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by ewrfcas on 2017/3/14.
 */
public interface DiseaseRepository extends JpaRepository<JPADisease,String> {
    List<JPADisease> findByNameContaining(String name);
}
