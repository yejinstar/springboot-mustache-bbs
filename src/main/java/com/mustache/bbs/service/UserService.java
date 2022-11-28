package com.mustache.bbs.service;

import com.mustache.bbs.domain.dto.UserDto;
import com.mustache.bbs.domain.dto.UserJoinRequest;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public UserDto join(UserJoinRequest request) {
        return new UserDto("","","");
    }
}
