package com.mustache.bbs.controller;

import com.mustache.bbs.domain.HospitalResponse;
import com.mustache.bbs.domain.entity.Hospital;
import com.mustache.bbs.repository.HospitalRepository;
import com.mustache.bbs.service.HospitalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/hospitals")
public class HospitalRestController {
    private final HospitalService hospitalService;
    public HospitalRestController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    /*private final HospitalRepository hospitalRepository;

    public HospitalRestController(HospitalService hospitalService, HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }*/

    @GetMapping("/{id}")
    public ResponseEntity<HospitalResponse> get(@PathVariable Integer id) { // ResponseEntity도 DTO타입
        /*Optional<Hospital> hospital = hospitalRepository.findById(id); // Entity
        HospitalResponse hospitalResponse = Hospital.of(hospital.get()); // DTO
        return ResponseEntity.ok().body(hospitalResponse); // Return은 DTO로*/

        HospitalResponse hospitalResponse = hospitalService.getHospital(id); // DTO
        return ResponseEntity.ok().body(hospitalResponse); // Return은 DTO로
    }

}
