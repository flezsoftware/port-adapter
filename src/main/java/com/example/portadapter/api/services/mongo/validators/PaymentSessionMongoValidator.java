package com.example.portadapter.api.services.mongo.validators;

import com.example.portadapter.api.domain.validators.PaymentSessionValidator;
import com.example.portadapter.api.services.mongo.model.PaymentSessionMongo;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.validation.Validator;

@Profile("mongo")
@Service
public class PaymentSessionMongoValidator extends PaymentSessionValidator<PaymentSessionMongo, String> {
    public PaymentSessionMongoValidator(Validator validator) {
        super(validator);
    }
}
