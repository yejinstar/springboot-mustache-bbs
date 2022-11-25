package com.mustache.bbs.repository;

import com.mustache.bbs.domain.entity.Hospital;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HospitalRepository extends JpaRepository<Hospital,Long> {

    Optional<Hospital> findById(Long id);
    Page<Hospital> findByRoadNameAddressContaining(String keyword, Pageable pageable);
    List<Hospital> findByRoadNameAddressContaining(String keyword); // 포함
    List<Hospital> findByHospitalNameStartsWith(String keyword); // 시작
    List<Hospital> findByHospitalNameEndingWith(String keyword); // 끝남

    List<Hospital> findByPatientRoomCountBetween(Integer start, Integer end);

    List<Hospital> findByPatientRoomCountBetweenOrderByPatientRoomCount(Integer start, Integer end);
    List<Hospital> findByPatientRoomCountBetweenOrderByPatientRoomCountDesc(Integer start, Integer end);
    List<Hospital> findByBusinessTypeNameIn(List<String> businessTypes);
    List<Hospital> findByBusinessTypeNameInAndRoadNameAddressLike(List<String> businessTypes, String roadNameAddress);
}
