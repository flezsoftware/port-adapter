package com.example.portadapter.api.services.kafka.services;

import com.example.portadapter.api.domain.model.PaymentStatus;
import com.example.portadapter.api.domain.services.PaymentSessionService;
import com.example.portadapter.api.services.kafka.model.PaymentSessionKafkaDomain;
import com.example.portadapter.api.services.kafka.repositories.PaymentSessionKafkaRepositoryImpl;
import com.example.portadapter.api.services.kafka.validators.PaymentSessionKafkaValidator;
import com.example.portadapter.api.services.rest.model.PaymentSessionRest;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Profile("kafka")
@Service
public class PaymentSessionKafkaService extends PaymentSessionService<PaymentSessionKafkaDomain, String> {

    public PaymentSessionKafkaService(PaymentSessionKafkaRepositoryImpl repository, PaymentSessionKafkaValidator validator) {
        super(repository, validator);
    }

    @Override
    public PaymentSessionKafkaDomain PaymentSession(PaymentSessionRest paymentSession) {
        return new PaymentSessionKafkaDomain(paymentSession.getId(), paymentSession.getCurrency(), paymentSession.getAmount(), paymentSession.getStatus(), paymentSession.getAmountDueGuid());
    }

    @Override
    public Optional<PaymentSessionKafkaDomain> findByAmountDueGuidRestrictedStatus(String amountDueGuid) {

        final Optional<PaymentSessionKafkaDomain> domain = repository.findById(amountDueGuid);
        if (domain.isEmpty()) {
            return domain;
        }
        if (!Set.of(PaymentStatus.ONE, PaymentStatus.TWO).contains(domain.get().getStatus())) {
            return Optional.empty();
        }

        return domain;
    }
}
