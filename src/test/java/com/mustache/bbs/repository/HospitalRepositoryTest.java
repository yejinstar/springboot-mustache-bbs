package com.mustache.bbs.repository;

import com.mustache.bbs.domain.entity.Hospital;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class HospitalRepositoryTest {

    @Autowired
    HospitalRepository hospitalRepository;

    @Test
    void name(){
        Optional<Hospital> hospital = hospitalRepository.findById(1);
        Hospital hp = hospital.get();
        System.out.println("Test"+hp.getId());
    }

    @Test
    @DisplayName("BusinessTypeName이 보건소 보건지소 보건진료소인 데이터 파싱")
    void findByBusinessTypeNameIn() {
        List<String> inClues = new ArrayList<>();
        inClues.add("보건소");
        inClues.add("보건진료소");
        inClues.add("보건지소");
        List<Hospital> hospitals = hospitalRepository.findByBusinessTypeNameInAndRoadNameAddressLike(inClues,"%광진구%");
        for (var hospital:hospitals
             ) {
            System.out.println(hospital.getHospitalName());
        }
    }
}