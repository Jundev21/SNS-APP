package com.sns.sns.service.domain.exception;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
    ALREADY_EXIST_MEMBER(HttpStatus.CONFLICT,"이미 존재하는 멤버");

    public final HttpStatus status;
    public final String message;
}
