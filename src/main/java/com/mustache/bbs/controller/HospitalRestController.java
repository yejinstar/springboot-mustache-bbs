package com.mustache.bbs.controller;

import com.mustache.bbs.domain.dto.*;
import com.mustache.bbs.domain.entity.Hospital;
import com.mustache.bbs.service.HospitalService;
import com.mustache.bbs.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/hospitals")
public class HospitalRestController {
    private final HospitalService hospitalService;
    private final ReviewService reviewService;

    /*@GetMapping("/{id}")
    public ResponseEntity<HospitalResponse> get(@PathVariable Long id) { // ResponseEntity도 DTO타입
        HospitalResponse hospitalResponse = hospitalService.getHospital(id); // DTO
        return ResponseEntity.ok().body(hospitalResponse); // Return은 DTO로
    }*/

    @GetMapping("/{id}/reviews")
    public ResponseEntity<List<ReviewReadResponse>> reviews(@PathVariable Long id) {
        return ResponseEntity.ok().body(reviewService.findAllByHospitalId(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HospitalReadResponse> getHospitalWithReview(@PathVariable Long id) { // ResponseEntity도 DTO타입
        Hospital hospital = hospitalService.findById(id);
        return ResponseEntity.ok().body(HospitalReadResponse.fromEntity(hospital)); // Return은 DTO로
    }
}
