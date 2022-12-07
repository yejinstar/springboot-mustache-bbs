package com.mustache.bbs.service;

import com.mustache.bbs.domain.dto.VisitCreateRequest;
import com.mustache.bbs.domain.entity.Hospital;
import com.mustache.bbs.domain.entity.User;
import com.mustache.bbs.domain.entity.Visit;
import com.mustache.bbs.exception.ErrorCode;
import com.mustache.bbs.exception.HospitalReviewAppException;
import com.mustache.bbs.repository.HospitalRepository;
import com.mustache.bbs.repository.UserRepository;
import com.mustache.bbs.repository.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VisitService {

    private final VisitRepository visitRepository;
    private final HospitalRepository hospitalRepository;
    private final UserRepository userRepository;

    public void create(VisitCreateRequest dto, String userName) {
// hospital이 없을 때 등록불가
        Hospital hospital = hospitalRepository.findById(dto.getHospitalId())
                .orElseThrow(() -> new HospitalReviewAppException(ErrorCode.NOT_FOUNDED, String.format("hospitalId:%s 가 없습니다.", dto.getHospitalId())));

        // user가 없을 때 등록불가
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new HospitalReviewAppException(ErrorCode.USER_NOT_FOUNDED, String.format("%s user가 없습니다.", userName)));

        Visit visit = Visit.builder()
                .user(user)
                .hospital(hospital)
                .disease(dto.getDisease())
                .amount(dto.getAmount())
                .build();
        visitRepository.save(visit);
    }

}
