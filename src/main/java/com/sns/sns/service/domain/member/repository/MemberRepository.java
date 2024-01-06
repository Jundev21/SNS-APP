package com.sns.sns.service.domain.member.repository;

import com.sns.sns.service.domain.member.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface MemberRepository extends JpaRepository<Member,Long> {

    Optional<Member> findByUserName(String userName);

}
