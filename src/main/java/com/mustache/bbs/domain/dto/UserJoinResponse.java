package com.mustache.bbs.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
public class UserJoinResponse {
    private String userName;

    private String emailAdderess;
}
