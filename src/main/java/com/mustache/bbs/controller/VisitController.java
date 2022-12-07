package com.mustache.bbs.controller;

import com.mustache.bbs.domain.dto.VisitCreateRequest;
import com.mustache.bbs.service.VisitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/visits")
public class VisitController {
    /*
    *   GET /api/v1/visits → 전체 조회
        GET /api/v1/visits/users/{id} → 특정 user의 기록 조회
        GET /api/v1/visits/hospitals/{id} → 특정 병원의 기록 조회
    * */

    private final VisitService visitService;

    @PostMapping
    public ResponseEntity<String> createVisit(
            @RequestBody VisitCreateRequest visitCreateRequest,
            Authentication authentication) {
        String userName = authentication.getName();
        log.info("userName:{}",userName);
        log.info("{}", visitCreateRequest);
        visitService.create(visitCreateRequest, userName);
        return ResponseEntity.ok().body("visit log success");
    }

}
