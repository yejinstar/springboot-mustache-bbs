package com.mustache.bbs.service;

import com.mustache.bbs.domain.dto.ArticleDto;
import com.mustache.bbs.domain.dto.ReviewCreateRequest;
import com.mustache.bbs.domain.dto.ReviewReadResponse;
import com.mustache.bbs.domain.dto.ReviewResponse;
import com.mustache.bbs.domain.entity.Article;
import com.mustache.bbs.domain.entity.Hospital;
import com.mustache.bbs.domain.entity.Review;
import com.mustache.bbs.repository.HospitalRepository;
import com.mustache.bbs.repository.ReviewRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final HospitalRepository hospitalRepository;

    public ReviewService(ReviewRepository reviewRepository, HospitalRepository hospitalRepository) {
        this.reviewRepository = reviewRepository;
        this.hospitalRepository = hospitalRepository;
    }

    public ReviewResponse createReview(ReviewCreateRequest dto) {

        Optional<Hospital> optionalHospital = hospitalRepository.findById(dto.getHospitalId());

        Review review = Review.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .userName(dto.getUserName())
                .hospital(optionalHospital.get())
                .build();
        Review savedReview = reviewRepository.save(review);
        return ReviewResponse.builder()
                .userName(savedReview.getUserName())
                .title(savedReview.getTitle())
                .content(savedReview.getContent())
                .message("리뷰 등록 성공")
                .build();
    }

    public ReviewResponse add(ReviewCreateRequest reviewCreateRequest) {
        Optional<Hospital> optHospital = hospitalRepository.findById(reviewCreateRequest.getHospitalId());
        Review review = Review.builder()
                .title(reviewCreateRequest.getTitle())
                .content(reviewCreateRequest.getContent())
                .userName(reviewCreateRequest.getUserName())
                .hospital(optHospital.get())
                .build();
        Review savedReview = reviewRepository.save(review);
        return new ReviewResponse(savedReview.getId(), savedReview.getTitle(), savedReview.getContent(), savedReview.getContent(),
                "리뷰 등록이 성공 했습니다.");
    }

    // get Review
    /*public ReviewResponse getReview(Integer id) {
        Optional<Review> optionalReview = reviewRepository.
    }*/

    public Review getReview(Long id) {
        Review review = reviewRepository.findById(id).orElseThrow(
                ()->new RuntimeException("해당 id가 없습니다."));
        return review;
    }

    public List<ReviewReadResponse> findAllByHospitalId(Long hospitalId) {
        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(()-> new IllegalArgumentException("해당 id가 없습니다."));
        List<ReviewReadResponse> reviews = reviewRepository.findByHospital(hospital)
                .stream().map(review -> ReviewReadResponse.builder()
                        .title(review.getTitle())
                        .content(review.getContent())
                        .userName(review.getUserName())
                        .hospitalName(review.getHospital().getHospitalName())
                        .build()
                ).collect(Collectors.toList());
        return reviews;
    }
}
