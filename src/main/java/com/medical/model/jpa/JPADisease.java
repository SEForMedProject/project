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
    private String department;
    private String alias;
    private String symptom;
    private String bodypart;
    private String prone_group;
    private String related_ill;
    private String definition;
    private String etiology;
    private String clinical_feature;
    private int search_index;
    private int search_index_new;
}
