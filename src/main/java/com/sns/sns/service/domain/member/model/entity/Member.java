package com.sns.sns.service.domain.member.model.entity;


import com.sns.sns.service.common.BaseTimeEntity;
import com.sns.sns.service.domain.member.model.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
public class Member extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.USER;


    public Member(
            String username,
            String password
    ){
        this.userName = username;
        this.password = password;
    }
}
