package com.sns.sns.service.domain.notification.model;


import com.sns.sns.service.common.BaseTimeEntity;
import com.sns.sns.service.domain.comment.model.CommentEntity;
import com.sns.sns.service.domain.favorite.model.FavoriteEntity;
import com.sns.sns.service.domain.member.model.entity.Member;
import com.sns.sns.service.domain.notification.dto.NotificationType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class NotificationEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //알림을 받을 사용자
    @ManyToOne
    private Member member;

    //어떤 타입의 알림을 받을 것인지
    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;
    @OneToMany(mappedBy = "notificationEntity")
    List<CommentEntity> commentEntityList = new ArrayList<>();
    @OneToMany(mappedBy = "notificationEntity")
    List<FavoriteEntity> favoriteEntityList = new ArrayList<>();


public NotificationEntity(Member member, NotificationType notificationType){
    this.member = member;
    this.notificationType = notificationType;
}

}
