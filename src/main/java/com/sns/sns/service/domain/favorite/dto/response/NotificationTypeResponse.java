package com.sns.sns.service.domain.favorite.dto.response;

import com.sns.sns.service.domain.notification.dto.NotificationType;
import com.sns.sns.service.domain.notification.model.NotificationEntity;
import lombok.Builder;


@Builder
public record NotificationTypeResponse(
        String message
) {

    public static NotificationTypeResponse notificationTypeBuilder(NotificationType notificationType) {
        return NotificationTypeResponse.builder()
                .message(notificationType.getMessage())
                .build();

    }

}

