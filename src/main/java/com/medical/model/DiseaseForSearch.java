package com.medical.model;

import lombok.Data;

/**
 * Created by ewrfcas on 2017/3/14.
 */
//专门用来搜索返回的词条，轻便封装
@Data
public class DiseaseForSearch {
    private int diseaseId;
    private String diseaseName;
    private String alias;//别名
    private String bodypart;
    private String showDetail;//搜索框左边展示的内容，通常是diseaseName或者是症状（diseaseName）
    private String tag;//搜索框右边显示词条（疾病，症状）
    private int index;
}
