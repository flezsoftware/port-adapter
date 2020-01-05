package com.example.portadapter.api.services.kafka.repositories;


import com.example.portadapter.api.domain.model.PaymentSession;
import com.example.portadapter.api.domain.repositories.PaymentSessionRepository;
import com.example.portadapter.api.services.kafka.model.PaymentSessionKafkaDomain;

public interface PaymentSessionKafkaRepository<P extends PaymentSession<String>, S> extends PaymentSessionRepository<PaymentSessionKafkaDomain, String> {

}
