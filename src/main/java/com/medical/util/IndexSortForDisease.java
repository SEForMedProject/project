package com.medical.util;

import com.medical.model.DiseaseForSearch;
import org.springframework.stereotype.Component;

import java.util.Comparator;

/**
 * Created by ewrfcas on 2017/4/18.
 */
//疾病排序
@Component
public class IndexSortForDisease implements Comparator {
    public int compare(Object a1,Object a2){
        DiseaseForSearch b1=(DiseaseForSearch) a1;
        DiseaseForSearch b2=(DiseaseForSearch) a2;
        return b2.getIndex()-(b1.getIndex());
    }
}
