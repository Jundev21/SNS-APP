package com.sns.sns.service.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
public class AuthenticationConfig{
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
                .httpBasic(AbstractHttpConfigurer::disable)
                .csrf(CsrfConfigurer::disable)
                .cors(CorsConfigurer::disable)
                .formLogin(FormLoginConfigurer::disable)

                .authorizeHttpRequests((request) -> request
                        .requestMatchers("/**").permitAll() // TODO: login이나 signup, 상품 조회처럼 인증 필요없는 url 넣기
                        .anyRequest().authenticated()
                )
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                //secutiry 에서 오류가 생겼을시에 보내지는 api
//                .addFilterBefore(null, UsernamePasswordAuthenticationFilter.class)
//                .exceptionHandling(exceptionHandling -> {
//                    exceptionHandling.authenticationEntryPoint(null);
//                })
                .build();









    }

}
