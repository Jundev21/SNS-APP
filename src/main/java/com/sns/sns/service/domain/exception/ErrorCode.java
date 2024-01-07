package com.sns.sns.service.domain.exception;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
    ALREADY_EXIST_MEMBER(HttpStatus.CONFLICT,"이미 존재하는 멤버"),
    NOT_EXIST_MEMBER(HttpStatus.NOT_FOUND,"존재하지 않는 회원"),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED,"비밀번호가 다릅니다.")
    ;

    public final HttpStatus status;
    public final String message;
}
