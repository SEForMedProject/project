package com.medical;

import com.medical.model.jpa.JPADisease;
import com.medical.repository.DiseaseRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static jdk.nashorn.internal.objects.Global.print;

/**
 * Created by winyang on 3/25/17.
 */
public class DiseaseRepositoryTest {
    @Autowired
    private transient DiseaseRepository diseaseRepository;

    public  void nima(){
        List<JPADisease> a =  diseaseRepository.findBySymptomContaining("热");
       print(a.size());
    }


}
