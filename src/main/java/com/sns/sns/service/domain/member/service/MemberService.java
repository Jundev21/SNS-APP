package com.sns.sns.service.domain.member.service;


import com.sns.sns.service.domain.exception.BasicException;
import com.sns.sns.service.domain.exception.ErrorCode;
import com.sns.sns.service.domain.member.dto.request.LoginRequest;
import com.sns.sns.service.domain.member.dto.request.RegisterRequest;
import com.sns.sns.service.domain.member.dto.response.RegisterResponse;
import com.sns.sns.service.domain.member.model.entity.Member;
import com.sns.sns.service.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public RegisterResponse memberRegister(RegisterRequest registerRequest) {
        memberRepository.findByUserName(registerRequest.userName()).ifPresent(
                member->{
                    throw new BasicException(ErrorCode.ALREADY_EXIST_MEMBER, "이미 있는 회원입니다.");
                }
        );

        Member newMember = memberRepository.save(new Member(registerRequest.userName(), registerRequest.password()));

        return RegisterResponse.fromEntity(newMember);
    }

    public String memberLogin(LoginRequest loginRequest) {
        Member member = memberRepository.findByUserName(loginRequest.userName())
                .orElseThrow(() -> new BasicException("없는 회원"));

        if(!member.getPassword().equals(loginRequest.userPassword())){
            throw new BasicException("비밀번호 다름");
        }


//        토큰 생성
        return "test token";


    }
}
