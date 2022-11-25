package com.mustache.bbs.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ReviewCreateRequest {
    private Long hospitalId;
    private String userName;
    private String title;
    private String content;
}