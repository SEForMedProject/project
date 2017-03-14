package com.medical.model.jpa;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by ewrfcas on 2017/3/14.
 */
@Entity
@Data
@Table(name = "tb_ill")
public class JPADisease {
    @Id
    private int id;
    private String name;
    private int department_id;
    private String alias;
    private String symptom;
    private String body_part;
    private String prone_group;
    private String related_ill_id;
    private String definition;
    private String etiology;
    private String clinical_feature;
}
