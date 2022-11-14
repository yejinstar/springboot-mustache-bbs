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
    @DisplayName("광진구 + BusinessTypeName이 보건소 보건지소 보건진료소인 데이터 파싱")
    void findByBusinessTypeNameInAndRoadNameAddressLike() {
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

    @Test
    @DisplayName("BusinessTypeName이 보건소 보건지소 보건진료소인 데이터 파싱")
    void findByBusinessTypeNameIn() {
        List<String> inClues = new ArrayList<>();
        inClues.add("보건소");
        inClues.add("보건진료소");
        inClues.add("보건지소");
        List<Hospital> hospitals = hospitalRepository.findByBusinessTypeNameIn(inClues);
        for (var hospital:hospitals
        ) {
            System.out.println(hospital.getHospitalName());
        }
    }
    // 출력코드
    void printHospitalNameAndAddress(List<Hospital> hospitals) {
        for (var hospital : hospitals) {
            System.out.printf("%s | %s %f | %d\n", hospital.getHospitalName(), hospital.getRoadNameAddress(), hospital.getTotalAreaSize(),
                    hospital.getPatientRoomCount());
        }

        System.out.println(hospitals.size());
    }
    @Test
    @DisplayName("containing")
    void containing() {
        List<Hospital> hospitals = hospitalRepository.findByRoadNameAddressContaining("송파구");
        printHospitalNameAndAddress(hospitals);
    }

    @Test
    @DisplayName("startsWith")
    void startsWith() {
        List<Hospital> hospitals = hospitalRepository.findByHospitalNameStartsWith("연세");
        printHospitalNameAndAddress(hospitals);
    }

    @Test
    @DisplayName("endingWith")
    void endingWith() {
        List<Hospital> hospitals = hospitalRepository.findByHospitalNameEndingWith("보건소");
        printHospitalNameAndAddress(hospitals);
    }

    @Test
    @DisplayName("patientRoomCount")
    void patientRoomCount() {
        List<Hospital> hospitals = hospitalRepository.findByPatientRoomCountBetween(10,19);
        printHospitalNameAndAddress(hospitals);
    }
}