package com.example.portadapter.api.services.redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
@Profile("redis")
public class RedisConfig {
    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }
}
