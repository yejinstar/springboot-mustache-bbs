package com.mustache.bbs.controller;

import com.mustache.bbs.domain.Response;
import com.mustache.bbs.domain.dto.UserLoginResponse;
import com.mustache.bbs.domain.dto.*;
import com.mustache.bbs.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping("/join")
    public Response<UserJoinResponse> join(@RequestBody UserJoinRequest userJoinRequest) {
        UserDto userDto = userService.join(userJoinRequest);
        return Response.success(new UserJoinResponse(userDto.getUserName(),userDto.getEmailAddress()));

    }

    @PostMapping("/login")
    public Response<UserLoginResponse> login(@RequestBody UserLoginRequest userLoginRequest) {
        String token = userService.login(userLoginRequest.getUserName(),
                userLoginRequest.getPassword());
        return Response.success(new UserLoginResponse(token));
    }

    @GetMapping("/login/{name}")
    public Response<UserLoginResponse> login_get(@PathVariable String name) {
        UserLoginResponse response = userService.find(name);
        return Response.success(response);
    }
}
