package com.example.portadapter.api.services.mongo.services;

import com.example.portadapter.api.services.kafka.model.PaymentSessionKafka;
import com.example.portadapter.api.domain.model.PaymentStatus;
import com.example.portadapter.api.domain.services.PaymentSessionService;
import com.example.portadapter.api.services.mongo.model.PaymentSessionMongo;
import com.example.portadapter.api.services.mongo.repositories.PaymentSessionMongoRepository;
import com.example.portadapter.api.services.mongo.validators.PaymentSessionMongoValidator;
import com.example.portadapter.api.services.rest.model.PaymentSessionRest;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Profile("mongo")
@Service
public class PaymentSessionMongoService extends PaymentSessionService<PaymentSessionMongo, String> {
    public PaymentSessionMongoService(PaymentSessionMongoRepository repository, PaymentSessionMongoValidator validator) {
        super(repository, validator);
    }

    @Override
    public PaymentSessionMongo PaymentSession(PaymentSessionRest paymentSessionRest) {
        return new PaymentSessionMongo(paymentSessionRest.getId(), paymentSessionRest.getCurrency(), paymentSessionRest.getAmount(), paymentSessionRest.getStatus(), paymentSessionRest.getAmountDueGuid());
    }

    @Override
    public Optional<PaymentSessionMongo> findByAmountDueGuidRestrictedStatus(String amountDueGuid) {
        return ((PaymentSessionMongoRepository) repository).findByAmountDueGuidAndStatusIn(amountDueGuid, Set.of(PaymentStatus.ONE, PaymentStatus.TWO));
    }
}
