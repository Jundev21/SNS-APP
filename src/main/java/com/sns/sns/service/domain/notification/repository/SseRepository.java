package com.sns.sns.service.domain.notification.repository;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Repository
public class SseRepository {

    private Map<String, SseEmitter> sseEmitterMap = new HashMap<>();

    //client 는 아이디 값으로 들어온다.
    public SseEmitter saveSse(Long userId, SseEmitter sseEmitter ){
        final String key =getKey(userId);
        sseEmitterMap.put(key, sseEmitter);
        log.info("set emitter" + key);
        return sseEmitter;
    }

    public Optional<SseEmitter> get(Long userId){
        SseEmitter result = sseEmitterMap.get(getKey(userId));
        log.info("Get Emitter from Redis {}", result);
        return Optional.ofNullable(result);
    }

    public void delete(Long userId){
        sseEmitterMap.remove(getKey(userId));
    }

    public String getKey(Long userId){
        return "Emitter userId" + userId;
    }
}
