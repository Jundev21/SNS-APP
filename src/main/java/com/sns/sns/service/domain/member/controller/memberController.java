package com.sns.sns.service.domain.member.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class memberController {


    @PostMapping("/register")
    public String memberRegister(){


        return "register";
    }


    @PostMapping("/login")
    public String memberLogin(){
        
    }

}
