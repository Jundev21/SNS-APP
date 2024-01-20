package com.sns.sns.service.domain.member.controller;


import com.sns.sns.service.common.response.Response;
import com.sns.sns.service.domain.member.dto.request.LoginRequest;
import com.sns.sns.service.domain.member.dto.request.RegisterRequest;
import com.sns.sns.service.domain.member.dto.response.LoginResponse;
import com.sns.sns.service.domain.member.dto.response.RegisterResponse;
import com.sns.sns.service.domain.member.model.entity.Member;
import com.sns.sns.service.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/register")
    public Response<RegisterResponse> memberRegister(
            @RequestBody RegisterRequest registerRequest
    ) {
        return Response.success(memberService.memberRegister(registerRequest));
    }

    @PostMapping("/login")
    public Response<LoginResponse> memberLogin(
            @RequestBody LoginRequest loginRequest
            ){
        return Response.success(memberService.memberLogin(loginRequest));
    }

}
