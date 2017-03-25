package com.medical.model.jpa;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by winyang on 3/25/17.
 * 医生表,但是电话邮箱还有icon_id以及密码还有用户类型没弄进来
 * 目前没有正常值
 */
@Entity
@Data
@Table(name = "tb_doctor")
public  class DoctorInfo {
    @Id
    private int id ;
    private String name;
    private int department_id;
    private String good_at ;
    private Float rating ;
    private int num_treatment;
    private int num_reservation;
    private String introduction ;
}
