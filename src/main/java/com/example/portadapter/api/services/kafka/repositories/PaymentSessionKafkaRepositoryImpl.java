package com.example.portadapter.api.services.kafka.repositories;

import com.example.portadapter.api.domain.model.PaymentStatus;
import com.example.portadapter.api.services.kafka.model.PaymentSessionKafkaDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Profile("kafka")
@Repository
@RequiredArgsConstructor
public class PaymentSessionKafkaRepositoryImpl implements PaymentSessionKafkaRepository<PaymentSessionKafkaDomain, String> {

    private final PaymentSessionKafkaRepositorySupport paymentSessionKafkaRepositorySupport;

    public PaymentSessionKafkaDomain save(PaymentSessionKafkaDomain s) {
        if(s.getId() == null){
            s.setId(UUID.randomUUID().toString());
        }
        return paymentSessionKafkaRepositorySupport.save(s);
    }

    public Optional<PaymentSessionKafkaDomain> findById(String s) {
        PaymentSessionKafkaDomain obj = paymentSessionKafkaRepositorySupport.findById(s);
        if(obj == null)
            return Optional.empty();
        return Optional.of(obj);
    }

    @Override
    public Optional<PaymentSessionKafkaDomain> findByAmountDueGuidAndStatusOrStatus(String amountDueGuid, PaymentStatus status1, PaymentStatus status2) {
        PaymentSessionKafkaDomain obj = paymentSessionKafkaRepositorySupport.findById(amountDueGuid);
        if(obj == null)
            return Optional.empty();

        if(obj.getStatus().equals(status1) || obj.getStatus().equals(status2)){
            return Optional.of(obj);
        }
        return Optional.empty();
    }
}
