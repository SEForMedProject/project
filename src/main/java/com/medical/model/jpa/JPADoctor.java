package com.medical.model.jpa;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by ewrfcas on 2017/3/2.
 */
@Entity
@Data
@Table(name = "doctor")
public class JPADoctor {
    @Id
    private int doctor_id;

    private String doctor_name;

    private int doctor_age;
}
