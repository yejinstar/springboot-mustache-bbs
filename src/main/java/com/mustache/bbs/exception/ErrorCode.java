package com.mustache.bbs.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    DUPLICATED_USER_NAME(HttpStatus.CONFLICT, "User name is duplicated."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "User name not found"),
    INVALID_PASSWORD(HttpStatus.NOT_FOUND, "Password is wrong" );


    private HttpStatus status;
    private String message;
}
