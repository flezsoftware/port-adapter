package com.example.portadapter.api.services.redis.validators;

import com.example.portadapter.api.domain.validators.PaymentSessionValidator;
import com.example.portadapter.api.services.redis.model.PaymentSessionRedis;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.validation.Validator;

@Profile("redis")
@Service
public class PaymentSessionRedisValidator extends PaymentSessionValidator<PaymentSessionRedis, String> {
    public PaymentSessionRedisValidator(Validator validator) {
        super(validator);
    }
}
