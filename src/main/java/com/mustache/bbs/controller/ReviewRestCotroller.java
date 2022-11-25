package com.mustache.bbs.controller;

import com.mustache.bbs.domain.dto.HospitalResponse;
import com.mustache.bbs.domain.dto.ReviewCreateRequest;
import com.mustache.bbs.domain.dto.ReviewReadResponse;
import com.mustache.bbs.domain.dto.ReviewResponse;
import com.mustache.bbs.domain.entity.Hospital;
import com.mustache.bbs.domain.entity.Review;
import com.mustache.bbs.service.HospitalService;
import com.mustache.bbs.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/hospitals")
public class ReviewRestCotroller {
    private final ReviewService reviewService;
    private final HospitalService hospitalService;

    @PostMapping("/{id}")
    public ResponseEntity<ReviewResponse> addReview(@PathVariable Integer id,
        @RequestBody ReviewCreateRequest reviewCreateRequest) {
        log.info("{}",reviewCreateRequest);
        return ResponseEntity.ok().body(reviewService.createReview(reviewCreateRequest));
    }


    //리뷰 읽어오기
    @GetMapping("/reviews/{id}")
    public ResponseEntity<ReviewReadResponse> getReview(@PathVariable Long id) {
        Review review = reviewService.getReview(id);
        Hospital hospital = review.getHospital();
        ReviewReadResponse response = ReviewReadResponse.builder()
                .id(review.getId())
                .title(review.getTitle())
                .content(review.getContent())
                .userName(review.getUserName())
                .hospitalName(hospital.getHospitalName())
                .build();

        return ResponseEntity.ok().body(response);
    }

}
