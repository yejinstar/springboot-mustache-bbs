package com.mustache.bbs.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ReviewResponse {
    //Review Create response

    private Long id;
    private String title;
    private String content;
    private String userName;
    private String message;
}