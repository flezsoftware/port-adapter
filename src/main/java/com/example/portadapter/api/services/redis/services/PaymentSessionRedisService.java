package com.example.portadapter.api.services.redis.services;


import com.example.portadapter.api.domain.model.PaymentStatus;
import com.example.portadapter.api.domain.repositories.PaymentSessionRepository;
import com.example.portadapter.api.domain.services.PaymentSessionService;
import com.example.portadapter.api.domain.validators.PaymentSessionValidator;
import com.example.portadapter.api.services.kafka.model.PaymentSessionKafka;
import com.example.portadapter.api.services.redis.model.PaymentSessionRedis;
import com.example.portadapter.api.services.redis.repositories.PaymentSessionRedisRepository;
import com.example.portadapter.api.services.redis.validators.PaymentSessionRedisValidator;
import com.example.portadapter.api.services.rest.model.PaymentSessionRest;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("redis")
@Service
public class PaymentSessionRedisService extends PaymentSessionService<PaymentSessionRedis, String> {
    public PaymentSessionRedisService(PaymentSessionRedisRepository repository, PaymentSessionRedisValidator validator) {
        super(repository, validator);
    }

    @Override
    public PaymentSessionRedis PaymentSession(final PaymentSessionRest paymentSessionRest) {
        return new PaymentSessionRedis(paymentSessionRest.getId(), paymentSessionRest.getCurrency(), paymentSessionRest.getAmount(), paymentSessionRest.getStatus(), paymentSessionRest.getAmountDueGuid());
    }
}
