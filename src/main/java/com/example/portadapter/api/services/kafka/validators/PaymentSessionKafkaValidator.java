package com.example.portadapter.api.services.kafka.validators;

import com.example.portadapter.api.domain.validators.PaymentSessionValidator;
import com.example.portadapter.api.services.kafka.model.PaymentSessionKafkaDomain;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.validation.Validator;

@Profile("kafka")
@Service
public class PaymentSessionKafkaValidator extends PaymentSessionValidator<PaymentSessionKafkaDomain, String> {
    public PaymentSessionKafkaValidator(Validator validator) {
        super(validator);
    }
}
