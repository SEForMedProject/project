package com.medical.util;

import org.springframework.stereotype.Component;

/**
 * Created by ewrfcas on 2017/5/12.
 */
//标准化方法
@Component
public class Standardization {
    public String filter(String name){
        return name.replaceAll("十分|相当|非常|很|有点|有点儿|特别|剧|剧烈","");
    }
}
