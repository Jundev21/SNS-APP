package com.sns.sns.service.domain.notification.dto.response;


import com.sns.sns.service.domain.comment.dto.response.CommentPostResponse;
import com.sns.sns.service.domain.comment.model.CommentEntity;
import com.sns.sns.service.domain.favorite.dto.response.GetFavoriteResponse;
import com.sns.sns.service.domain.favorite.dto.response.NotificationTypeResponse;
import com.sns.sns.service.domain.favorite.model.FavoriteEntity;
import com.sns.sns.service.domain.notification.dto.NotificationType;
import com.sns.sns.service.domain.notification.model.NotificationEntity;
import lombok.Builder;

import java.util.List;

@Builder
public record NotificationResponse(
        List<CommentPostResponse> commentInfo,
        List<GetFavoriteResponse> favoriteInfo,
        List<NotificationTypeResponse> notificationType

) {
    public static NotificationResponse notificationResponse(
            List<CommentEntity> comments,
            List<FavoriteEntity> favorites,
            List<NotificationEntity> findNotification

    ) {
        return NotificationResponse.builder()
//                .commentInfo(
//                        comments.stream()
//                                .map(comment -> CommentPostResponse.commentPostResponse(comment)).toList())
//                .favoriteInfo(
//                        favorites.stream()
//                                .map(favorite -> GetFavoriteResponse.getFavoriteResponse(favorite.getBoardEntity(), favorite.getMember())).toList())
//
                .commentInfo(null)
                .favoriteInfo(null)
                .notificationType(findNotification.stream()
                        .map(notice -> NotificationTypeResponse.notificationTypeBuilder(notice.getNotificationType())).toList())
                .build();
    }

}
