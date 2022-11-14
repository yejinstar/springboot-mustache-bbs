package com.mustache.bbs.repository;

import com.mustache.bbs.domain.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HospitalRepository extends JpaRepository<Hospital,Integer> {

    List<Hospital> findByRoadNameAddressContaining(String keyword); // 포함
    List<Hospital> findByHospitalNameStartsWith(String keyword); // 시작
    List<Hospital> findByHospitalNameEndingWith(String keyword); // 끝남

    List<Hospital> findByPatientRoomCountBetween(Integer start, Integer end);
    List<Hospital> findByBusinessTypeNameIn(List<String> businessTypes);
    List<Hospital> findByBusinessTypeNameInAndRoadNameAddressLike(List<String> businessTypes, String roadNameAddress);
}
