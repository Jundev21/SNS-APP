package com.sns.sns.service.domain.notification.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public enum NotificationType {
    COMMENT_NOTIFICATION("새로운 댓글을 달았습니다."),
    LIKE_NOTIFICATION("새로운 좋아요를 받았습니다.");


    private final String message;

}
