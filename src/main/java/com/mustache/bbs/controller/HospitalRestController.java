package com.mustache.bbs.controller;

import com.mustache.bbs.domain.dto.HospitalResponse;
import com.mustache.bbs.domain.dto.ReviewCreateRequest;
import com.mustache.bbs.domain.dto.ReviewResponse;
import com.mustache.bbs.service.HospitalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/hospitals")
public class HospitalRestController {
    private final HospitalService hospitalService;
    public HospitalRestController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<HospitalResponse> get(@PathVariable Integer id) { // ResponseEntity도 DTO타입
        HospitalResponse hospitalResponse = hospitalService.getHospital(id); // DTO
        return ResponseEntity.ok().body(hospitalResponse); // Return은 DTO로
    }

}
