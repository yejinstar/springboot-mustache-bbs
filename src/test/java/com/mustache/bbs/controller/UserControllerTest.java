package com.mustache.bbs.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mustache.bbs.domain.dto.UserDto;
import com.mustache.bbs.domain.dto.UserJoinRequest;
import com.mustache.bbs.exception.ErrorCode;
import com.mustache.bbs.exception.HospitalReviewAppException;
import com.mustache.bbs.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    UserService userService;

    UserJoinRequest userJoinRequest = UserJoinRequest.builder()
            .userName("Yejins")
            .password("1234")
            .emailAddress("yejins@gmail.com")
            .build();

    @Test
    @DisplayName("Join success")
    @WithMockUser
    void join_success() throws Exception {
        UserJoinRequest userJoinRequest = UserJoinRequest.builder()
                .userName("Yejins")
                .password("1234")
                .emailAddress("yejins@gmail.com")
                .build();

        when(userService.join(any())).thenReturn(mock(UserDto.class));

        mockMvc.perform(post("/api/v1/users/join")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(userJoinRequest)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Join fail")
    @WithMockUser
    void join_fail() throws Exception {

        UserJoinRequest userJoinRequest = UserJoinRequest.builder()
                .userName("Yejins")
                .password("1234")
                .emailAddress("yejins@gmail.com")
                .build();

        when(userService.join(any())).thenThrow(new HospitalReviewAppException(ErrorCode.DUPLICATED_USER_NAME, ""));

        mockMvc.perform(post("/api/v1/users/join")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(userJoinRequest)))
                .andDo(print())
                .andExpect(status().isConflict());
    }
    @Test
    @DisplayName("Login fail - no id")
    @WithMockUser
    void login_fail1() throws Exception{
        when(userService.login(any(),any())).thenThrow(new HospitalReviewAppException(ErrorCode.NOT_FOUND, ""));
            /*
            * id pw 보내서
            * NOT_FOUND
            * */
        mockMvc.perform(post("/api/v1/users/login")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(userJoinRequest)))
                .andDo(print())
                .andExpect(status().isConflict());
    }
    @Test
    @DisplayName("Login fail - error pwd")
    @WithMockUser
    void login_fail2() throws Exception{

    }
    @Test
    @DisplayName("Login success")
    @WithMockUser
    void login_success() throws Exception{

    }
}
