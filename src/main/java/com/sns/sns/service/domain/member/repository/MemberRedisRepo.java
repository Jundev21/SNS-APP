package com.sns.sns.service.domain.member.repository;

import com.sns.sns.service.domain.member.model.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;

@Repository
@RequiredArgsConstructor
public class MemberRedisRepo {

//    private final RedisTemplate<String, Member> memberRedisTemplate;
//    // time limit 걸어줘야함 왜냐하면 멤버가 더이상 서비스를 사용하지 않을 경우에는 메모리 낭비가 될 수 있기 때문
//    private final Duration redisLimitTime = Duration.ofDays(7);
//
//    public void setMember(Member member){
//        memberRedisTemplate.opsForValue().set(getKey(member.getUsername()),member, redisLimitTime);
//    }
//    public void getMember(String userName){
//        memberRedisTemplate.opsForValue().get(getKey(userName));
//    }
//
//    private String getKey(String userName){
//        return "Member" + userName;
//    }
}
