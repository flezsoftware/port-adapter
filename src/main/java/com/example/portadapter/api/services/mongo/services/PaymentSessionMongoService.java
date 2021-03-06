package com.example.portadapter.api.services.mongo.services;

import com.example.portadapter.api.services.kafka.model.PaymentSessionKafka;
import com.example.portadapter.api.domain.model.PaymentStatus;
import com.example.portadapter.api.domain.services.PaymentSessionService;
import com.example.portadapter.api.services.kafka.listeners.PaymentSessionKafkaService;
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
    public PaymentSessionMongoService(PaymentSessionMongoRepository repository, PaymentSessionMongoValidator validator, PaymentSessionKafkaService paymentSessionKafkaService) {
        super(repository, validator, paymentSessionKafkaService);
    }

    @Override
    public PaymentSessionMongo PaymentSession(PaymentSessionRest paymentSessionRest) {
        return new PaymentSessionMongo(paymentSessionRest.getId(), paymentSessionRest.getCurrency(), paymentSessionRest.getAmount(), paymentSessionRest.getCountry(), paymentSessionRest.getStatus(), paymentSessionRest.getAmountDueGuid());
    }

    @Override
    public Optional<PaymentSessionMongo> findByAmountDueGuidRestrictedStatus(String amountDueGuid) {
        return ((PaymentSessionMongoRepository) repository).findByAmountDueGuidAndStatusIn(amountDueGuid, Set.of(PaymentStatus.ONE, PaymentStatus.TWO));
    }

    @Override
    public PaymentSessionMongo PaymentSession(PaymentSessionKafka paymentSession) {
        final PaymentSessionMongo paymentSessionMongo = new PaymentSessionMongo(paymentSession.getId().toString(), paymentSession.getCurrency().toString(), paymentSession.getAmount(), paymentSession.getCurrency().toString(), PaymentStatus.THREE, paymentSession.getAmountDueGuid().toString());
        return paymentSessionMongo;

    }
}
