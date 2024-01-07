package com.sns.sns.service.domain.member.service;


import com.sns.sns.service.domain.exception.BasicException;
import com.sns.sns.service.domain.exception.ErrorCode;
import com.sns.sns.service.domain.member.dto.request.LoginRequest;
import com.sns.sns.service.domain.member.dto.request.RegisterRequest;
import com.sns.sns.service.domain.member.dto.response.LoginResponse;
import com.sns.sns.service.domain.member.dto.response.RegisterResponse;
import com.sns.sns.service.domain.member.model.entity.Member;
import com.sns.sns.service.domain.member.repository.MemberRepository;
import com.sns.sns.service.jwt.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder encoder;
    private final JwtTokenUtil jwtTokenUtil;


    @Transactional
    public RegisterResponse memberRegister(RegisterRequest registerRequest) {
        memberRepository.findByUserName(registerRequest.userName()).ifPresent(
                member->{
                    throw new BasicException(ErrorCode.ALREADY_EXIST_MEMBER, "이미 있는 회원입니다.");
                }
        );

        Member newMember = memberRepository.save(new Member(registerRequest.userName(), encoder.encode(registerRequest.password())));

        return RegisterResponse.fromEntity(newMember);
    }

    @Transactional
    public LoginResponse memberLogin(LoginRequest loginRequest) {
        Member member = memberRepository.findByUserName(loginRequest.userName())
                .orElseThrow(() -> new BasicException(ErrorCode.NOT_EXIST_MEMBER, "존재하지 않는 회원"));

        if(!encoder.matches(loginRequest.password(), member.getPassword())){
            throw new BasicException(ErrorCode.INVALID_PASSWORD,"비밀번호 다릅니다.");
        }

        String jwtToken = jwtTokenUtil.generateToken(member.getUserName());

        return new LoginResponse(jwtToken, null);


    }
}
