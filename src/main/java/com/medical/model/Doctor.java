package com.medical.model;

import lombok.Data;

/**
 * Created by ewrfcas on 2017/3/2.
 */
@Data
public class Doctor {
    private int id;
    private String name;
    private String goodAt;
    private String title;
    private String department;
    private String introduction;
}
