package com.mustache.bbs.service;

import com.mustache.bbs.domain.dto.UserDto;
import com.mustache.bbs.domain.dto.UserJoinRequest;
import com.mustache.bbs.domain.dto.UserLoginResponse;
import com.mustache.bbs.domain.entity.User;
import com.mustache.bbs.exception.ErrorCode;
import com.mustache.bbs.exception.HospitalReviewAppException;
import com.mustache.bbs.repository.UserRepository;
import com.mustache.bbs.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private final BCryptPasswordEncoder encoder;

    @Value("${jwt.token.secret}") // Spring Annotation
    private String secretKey;

    private long expireTimeMs = 10000 * 60 * 60 ; // 1시간

    public UserDto join(UserJoinRequest request) {

        // 없으면 에러처리
        /*userRepository.findByUserName(request.getUserName())
                .orElseThrow(() -> new RuntimeException("해당 UserName이 중복 됩니다."));*/

        // 있으면 에러처리
        userRepository.findByUserName(request.getUserName())
                .ifPresent(user -> {
                    throw new HospitalReviewAppException(ErrorCode.DUPLICATED_USER_NAME, String.format("UserName:%s", request.getUserName()));
                });
        User savedUser = userRepository.save(request.toEntity(encoder.encode(request.getPassword())));

        return UserDto.builder()
                .id(savedUser.getId())
                .userName(savedUser.getUserName())
                .emailAddress(savedUser.getEmailAddress())
                .build();
    }

    public String login(String userName, String password) {

        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new HospitalReviewAppException(
                        ErrorCode.NOT_FOUND, String.format("%s는 가입된 적이 없습니다.", userName)
                ));

        if (!encoder.matches(password, user.getPassword())) {
            throw new HospitalReviewAppException(ErrorCode.INVALID_PASSWORD, "userName 또는 password가 잘못 되었습니다.");
        }

        return JwtTokenUtil.createToken(userName,secretKey,expireTimeMs);
    }

    public UserLoginResponse find(String userName) {
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new HospitalReviewAppException(
                        ErrorCode.NOT_FOUND, String.format("%s는 가입된 적이 없습니다.", userName)
                ));

        UserLoginResponse response;

        return UserLoginResponse.builder()
                .token(JwtTokenUtil.createToken(userName,secretKey,expireTimeMs))
                .build();
    }
}
