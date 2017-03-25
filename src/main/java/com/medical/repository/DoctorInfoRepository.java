package com.medical.repository;

import com.medical.model.jpa.DoctorInfo;
import com.medical.model.jpa.JPADisease;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by winyang on 3/25/17.
 */


public interface DoctorInfoRepository extends JpaRepository<DoctorInfo,Integer> {
    List<DoctorInfo> findBygood_atContaining(String illName);
}

