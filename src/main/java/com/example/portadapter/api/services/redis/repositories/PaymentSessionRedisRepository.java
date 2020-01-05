package com.example.portadapter.api.services.redis.repositories;

import com.example.portadapter.api.domain.repositories.PaymentSessionRepository;
import com.example.portadapter.api.services.redis.model.PaymentSessionRedis;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Profile("redis")
@Repository
public interface PaymentSessionRedisRepository extends PaymentSessionRepository<PaymentSessionRedis, String>, CrudRepository<PaymentSessionRedis, String> {
}
