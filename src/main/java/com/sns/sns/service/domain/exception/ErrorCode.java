package com.sns.sns.service.domain.exception;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
    ALREADY_EXIST_MEMBER(HttpStatus.CONFLICT,"이미 존재하는 멤버"),
    ALREADY_EXIST_FAVORITE(HttpStatus.CONFLICT,"이미 좋아요가 존재합니다."),
    NOT_EXIST_MEMBER(HttpStatus.NOT_FOUND,"존재하지 않는 회원"),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED,"비밀번호가 다릅니다."),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED,"유효하지 않은 토큰입니다."),
    NOT_EXIST_BOARD(HttpStatus.NOT_FOUND, "존재하지 않는 게시판"),
    INVALID_PERMISSION(HttpStatus.UNAUTHORIZED, "게시물 작성자가 아닙니다."),
    INVALID_PERMISSION_FAVORITE(HttpStatus.UNAUTHORIZED, "좋아요를 누른 사용자가 아닙니다.")
    ;

    public final HttpStatus status;
    public final String message;
}
