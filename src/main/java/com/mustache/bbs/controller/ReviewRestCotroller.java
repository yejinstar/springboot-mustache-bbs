package com.mustache.bbs.controller;

import com.mustache.bbs.domain.dto.HospitalResponse;
import com.mustache.bbs.domain.dto.ReviewCreateRequest;
import com.mustache.bbs.domain.dto.ReviewResponse;
import com.mustache.bbs.domain.entity.Review;
import com.mustache.bbs.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/v1/hospitals")
public class ReviewRestCotroller {
    private final ReviewService reviewService;

    public ReviewRestCotroller(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/{id}")
    public ResponseEntity<ReviewResponse> addReview(@PathVariable Integer id,
        @RequestBody ReviewCreateRequest reviewCreateRequest) {
        log.info("{}",reviewCreateRequest);
        return ResponseEntity.ok().body(reviewService.createReview(reviewCreateRequest));
    }


    //리뷰 읽어오기
    /*@GetMapping("/{id}/reviews")
    public ResponseEntity<ReviewResponse> getReview(@PathVariable Integer id) {
        ReviewResponse reviewResponse = reviewService.get(id);
        return ResponseEntity.ok().body(reviewResponse);
    }*/

}
