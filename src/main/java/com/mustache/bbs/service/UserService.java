package com.mustache.bbs.service;

import com.mustache.bbs.domain.dto.UserDto;
import com.mustache.bbs.domain.dto.UserJoinRequest;
import com.mustache.bbs.domain.entity.User;
import com.mustache.bbs.exception.ErrorCode;
import com.mustache.bbs.exception.HospitalReviewAppException;
import com.mustache.bbs.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
/*
* 비지니스 로직 - 회원가입
* 회원 userName(id) 중복을 check
* 중복이면 회원가입 안되도록 Exception
* */
    private final UserRepository userRepository;

    public UserDto join(UserJoinRequest request) {

        // 없으면 에러처리
        /*userRepository.findByUserName(request.getUserName())
                .orElseThrow(() -> new RuntimeException("해당 UserName이 중복 됩니다."));*/

        // 있으면 에러처리
        userRepository.findByUserName(request.getUserName())
                .ifPresent(user -> {
                    throw new HospitalReviewAppException(ErrorCode.DUPLICATED_USER_NAME, String.format("UserName:%s", request.getUserName()));
                });
        User savedUser = userRepository.save(request.toEntity());

        return UserDto.builder()
                .id(savedUser.getId())
                .userName(savedUser.getUserName())
                .emailAddress(savedUser.getEmailAddress())
                .build();
    }
}
