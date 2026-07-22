package com.example.demo.config;

import com.example.demo.subscriber.PromotionSubscriber;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;

@Configuration
public class RedisSubscriberConfig {

    @Bean
    RedisMessageListenerContainer container(
            RedisConnectionFactory connectionFactory,
            MessageListenerAdapter listenerAdapter
    ) {
        RedisMessageListenerContainer container =
                new RedisMessageListenerContainer();

        container.setConnectionFactory(connectionFactory);

        container.addMessageListener(
                listenerAdapter,
                new PatternTopic("promotion-updates")
        );

        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(
            PromotionSubscriber subscriber) {

        MessageListenerAdapter adapter =
                new MessageListenerAdapter(subscriber, "receiveMessage");

        adapter.setSerializer(new GenericJackson2JsonRedisSerializer());

        return adapter;
    }

}