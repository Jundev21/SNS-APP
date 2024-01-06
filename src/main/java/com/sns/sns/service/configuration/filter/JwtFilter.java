package com.sns.sns.service.configuration.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtFilter extends OncePerRequestFilter {


    //jwt filter 는 클라이언트 요청이 올때 서블릿에 바로 들어가지않고 먼저 필터링을 거친다.
    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain
    ) throws ServletException, IOException {

//        필터링이 실행될때 가장먼저 헤더를 확인한다. 헤더에 jwt 토큰이 있는지 없는지를 먼저체크
        // jwt 토큰을 헤더에 담는지 쿠키에담는지 먼저 체크확인

        String getHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
//        String jwtToken = getHeader


    }
}
