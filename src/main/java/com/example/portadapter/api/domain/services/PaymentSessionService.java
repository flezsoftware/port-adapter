package com.example.portadapter.api.domain.services;


import com.example.portadapter.api.domain.model.PaymentSession;
import com.example.portadapter.api.domain.model.PaymentStatus;
import com.example.portadapter.api.domain.repositories.PaymentSessionRepository;
import com.example.portadapter.api.domain.validators.PaymentSessionValidator;
import com.example.portadapter.api.services.rest.model.PaymentSessionRest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.Optional;

@RequiredArgsConstructor
public abstract class PaymentSessionService<T extends PaymentSession<ID>, ID> {

    protected final PaymentSessionRepository<T, ID> repository;
    protected final PaymentSessionValidator<T, ID> validator;

    public T save(T payment) {
        validate(payment);
        return repository.save(payment);
    }

    public PaymentSession<ID> saveFromRest(final PaymentSessionRest paymentSessionRest) {
        return save(PaymentSession(paymentSessionRest));
    }

    public Optional<T> findByAmountDueGuidRestrictedStatus(ID amountDueGuid) {
        return repository.findByAmountDueGuidAndStatusOrStatus(amountDueGuid, PaymentStatus.ONE, PaymentStatus.TWO);
    }

    public T findById(final ID id) throws ChangeSetPersister.NotFoundException {
        return repository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    protected void validate(final T object) {
        validator.validate(object);
    }

    abstract public T PaymentSession(final PaymentSessionRest paymentSessionRest);
}
