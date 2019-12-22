package com.example.portadapter.api.domain.services;


import com.example.portadapter.api.services.kafka.model.PaymentSessionKafka;
import com.example.portadapter.api.domain.model.PaymentSession;
import com.example.portadapter.api.domain.model.PaymentStatus;
import com.example.portadapter.api.domain.repositories.PaymentSessionRepository;
import com.example.portadapter.api.domain.validators.PaymentSessionValidator;
import com.example.portadapter.api.services.kafka.listeners.PaymentSessionKafkaService;
import com.example.portadapter.api.services.rest.model.PaymentSessionRest;
import lombok.RequiredArgsConstructor;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;

import java.util.Optional;

@RequiredArgsConstructor
public abstract class PaymentSessionService<T extends PaymentSession<ID>, ID> {

    protected final PaymentSessionRepository<T, ID> repository;
    protected final PaymentSessionValidator<T, ID> validator;

    private final PaymentSessionKafkaService paymentSessionListener;

    public Boolean existByCountry(String country) {
        return repository.existsByCountry(country);
    }

    public T save(T payment) {
        validate(payment);
        final T ret = repository.save(payment);
        paymentSessionListener.send(PaymentSessionMessage(ret));;
        return  ret;
    }

    public PaymentSession<ID> saveFromRest(final PaymentSessionRest paymentSessionRest) {
        return save(PaymentSession(paymentSessionRest));
    }

    public Optional<T> findByAmountDueGuidRestrictedStatus(ID amountDueGuid) {
        return repository.findByAmountDueGuidAndStatusOrStatus(amountDueGuid, PaymentStatus.ONE, PaymentStatus.TWO);
    }

    public T findById(final ID id) {
        return repository.findById(id).get();
    }

    public Optional<T> findByCountry(final ID id) {
        return repository.findByCountry(id);
    }

    protected void validate(final T object) {
        validator.validate(object);
    }

    public Message<PaymentSessionKafka> PaymentSessionMessage(T paymentSession){
        return MessageBuilder.withPayload(PaymentSessionKafka(paymentSession)).setHeader(KafkaHeaders.MESSAGE_KEY,paymentSession.getAmountDueGuid()).build();
    }

    public PaymentSessionKafka PaymentSessionKafka(T paymentSession){
        return PaymentSessionKafka.newBuilder().setId((String)paymentSession.getId()).setAmountDueGuid((String)paymentSession.getAmountDueGuid()).setAmount(paymentSession.getAmount()).setCurrency(paymentSession.getCurrency()).build();
    }

    abstract public T PaymentSession(final PaymentSessionKafka paymentSession);

    abstract public T PaymentSession(final PaymentSessionRest paymentSessionRest);
}
