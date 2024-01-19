package com.sns.sns.service.domain.notification.service;


import com.sns.sns.service.domain.comment.dto.response.CommentPostResponse;
import com.sns.sns.service.domain.comment.model.CommentEntity;
import com.sns.sns.service.domain.comment.repository.CommentRepository;
import com.sns.sns.service.domain.exception.BasicException;
import com.sns.sns.service.domain.exception.ErrorCode;
import com.sns.sns.service.domain.favorite.model.FavoriteEntity;
import com.sns.sns.service.domain.favorite.repository.FavoriteRepository;
import com.sns.sns.service.domain.member.model.entity.Member;
import com.sns.sns.service.domain.member.repository.MemberRepository;
import com.sns.sns.service.domain.notification.dto.request.NotificationRequest;
import com.sns.sns.service.domain.notification.dto.response.NotificationResponse;
import com.sns.sns.service.domain.notification.model.NotificationEntity;
import com.sns.sns.service.domain.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final MemberRepository memberRepository;
    private final CommentRepository commentRepository;
    private final FavoriteRepository favoriteRepository;

    public NotificationResponse makeNotification(Member member) {

        //알림을 받는 사용자가아니라
        List<NotificationEntity> findNotification = notificationRepository.findAllByMember(member);

        //알림을 받는 사용자의 커멘트 좋아요가 아니라 알림을 요청 한 사용자 => 코멘트나 라이크를 누른 사용자
//        List<CommentEntity> commentPostResponseList = commentRepository.findAllByMember(findNotification.getMember());
//        List<FavoriteEntity> favoriteEntityList = favoriteRepository.findAllByMember(findNotification.getMember());

//        return NotificationResponse.notificationResponse(commentPostResponseList, favoriteEntityList, findNotification);
        return NotificationResponse.notificationResponse(null, null, findNotification);
    }
}
