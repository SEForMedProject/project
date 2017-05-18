package com.medical.model.jpa;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by ewrfcas on 2017/3/2.
 */
@Entity
@Data
@Table(name = "tb_doctor")
public class JPADoctor {
    @Id
    private int id;

    private String name;

    private String password;

    private String title;

    private String hospital;

    private String department;

    @Column(name = "good_at")
    private String goodAt;

    private float rating;

    private int num_treatment;

    private int num_reservation;

    private String introduction;

    private String phone;

    private String mail;

    private String icon_id;

    private int user_type;
}
