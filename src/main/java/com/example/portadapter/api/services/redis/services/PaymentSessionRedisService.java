package com.example.portadapter.api.services.redis.services;


import com.example.portadapter.api.services.kafka.model.PaymentSessionKafka;
import com.example.portadapter.api.domain.model.PaymentStatus;
import com.example.portadapter.api.domain.repositories.PaymentSessionRepository;
import com.example.portadapter.api.domain.services.PaymentSessionService;
import com.example.portadapter.api.domain.validators.PaymentSessionValidator;
import com.example.portadapter.api.services.kafka.listeners.PaymentSessionKafkaService;
import com.example.portadapter.api.services.redis.model.PaymentSessionRedis;
import com.example.portadapter.api.services.rest.model.PaymentSessionRest;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("redis")
@Service
public class PaymentSessionRedisService extends PaymentSessionService<PaymentSessionRedis, String> {
    public PaymentSessionRedisService(PaymentSessionRepository repository, PaymentSessionValidator validator, PaymentSessionKafkaService paymentSessionKafkaService) {
        super(repository, validator,paymentSessionKafkaService);
    }

    @Override
    public PaymentSessionRedis PaymentSession(PaymentSessionKafka paymentSession) {
        return new PaymentSessionRedis(paymentSession.getId().toString(), paymentSession.getCurrency().toString(),paymentSession.getAmount(), "PL", PaymentStatus.THREE, paymentSession.getAmountDueGuid().toString());
    }

    @Override
    public PaymentSessionRedis PaymentSession(final PaymentSessionRest paymentSessionRest) {
        return new PaymentSessionRedis(paymentSessionRest.getId(), paymentSessionRest.getCurrency(), paymentSessionRest.getAmount(), paymentSessionRest.getCountry(), paymentSessionRest.getStatus(), paymentSessionRest.getAmountDueGuid());
    }
}
