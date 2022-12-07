package com.mustache.bbs.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class VisitCreateRequest {
    private Long hospitalId;
    private String disease;
    private float amount;
}
