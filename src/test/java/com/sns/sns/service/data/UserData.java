package com.sns.sns.service.data;

import com.sns.sns.service.domain.member.dto.request.RegisterRequest;
import com.sns.sns.service.domain.member.model.entity.Member;

public class UserData {

    public Member userEntity(String username, String password){
        return new Member(username,password);
    }

    public RegisterRequest userRegister(String username, String password){
        return new RegisterRequest(username, password);
    }
}
