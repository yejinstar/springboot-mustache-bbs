package com.mustache.bbs.domain.dto;

import com.mustache.bbs.domain.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor
public class ReviewReadResponse {
    private Long id;
    private String title;
    private String content;
    private String userName;
    private String hospitalName;

    public static ReviewReadResponse fromEntity(Review review) {
        ReviewReadResponse response = ReviewReadResponse.builder()
                .id(review.getId())
                .title(review.getTitle())
                .content(review.getContent())
                .userName(review.getUserName())
                .hospitalName(review.getHospital().getHospitalName())
                .build();
        return response;
}}
