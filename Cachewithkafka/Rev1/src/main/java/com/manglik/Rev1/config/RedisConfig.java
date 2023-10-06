package com.manglik.Rev1.config;


import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class RedisConfig {
    @Bean
    public StatefulRedisConnection<String, String> redisConnection() {
        RedisURI uri = RedisURI.builder().withHost("localhost")
                .withPort(Integer.parseInt("6379"))
                .withDatabase(Integer.parseInt("0")).build();
        return RedisClient.create(uri).connect();
    }


    @Bean
    @DependsOn("redisConnection")
    public RedisCommands<String, String> redisSync(@Autowired StatefulRedisConnection<String, String> redisConnection) {
        return redisConnection.sync();
    }
}
