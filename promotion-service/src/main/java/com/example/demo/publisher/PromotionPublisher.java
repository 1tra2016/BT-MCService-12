package com.example.demo.publisher;

import com.example.demo.dto.promotion.PromotionUpdateEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PromotionPublisher {

    private final RedisTemplate<String, Object> redisTemplate;

    private static final String CHANNEL = "promotion-updates";

    public void publish(PromotionUpdateEvent event) {
        redisTemplate.convertAndSend(CHANNEL, event);
    }

}