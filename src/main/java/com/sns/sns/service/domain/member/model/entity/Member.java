package com.sns.sns.service.domain.member.model.entity;


import com.sns.sns.service.common.BaseTimeEntity;
import com.sns.sns.service.domain.board.model.BoardEntity;
import com.sns.sns.service.domain.comment.model.CommentEntity;
import com.sns.sns.service.domain.favorite.model.FavoriteEntity;
import com.sns.sns.service.domain.member.model.UserRole;
import com.sns.sns.service.domain.notification.model.NotificationEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Member extends BaseTimeEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String userEmail;
    private String password;
    private long visitedTimes;
    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.USER;
    @OneToMany(mappedBy = "member")
    private List<BoardEntity> boardEntityList = new ArrayList<>();
    @OneToMany(mappedBy = "member")
    private List<FavoriteEntity> favoriteEntityList = new ArrayList<>();
    @OneToMany(mappedBy = "member")
    private List<CommentEntity> commentEntityList = new ArrayList<>();
    @OneToMany(mappedBy = "member")
    private List<NotificationEntity> notificationEntityList = new ArrayList<>();

    public Member(
            String username,
            String password,
            String userEmail
    ){
        this.userName = username;
        this.password = password;
        this.userEmail = userEmail;
    }

    public void UpdateMemberInfo(
            String userEmail,
            String password
    ){
        this.userEmail = userEmail;
        this.password = password;
    }
    public void UpdateVisitedCount(){
        this.visitedTimes +=1;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.getRole().toString()));
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
