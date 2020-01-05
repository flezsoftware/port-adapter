package com.example.portadapter.api.domain.repositories;

import com.example.portadapter.api.domain.model.PaymentStatus;

import java.util.Optional;

public interface PaymentSessionRepository<T, ID> {

    Optional<T> findByAmountDueGuidAndStatusOrStatus(ID amountDueGuid, PaymentStatus status1, PaymentStatus status2);

    Optional<T> findById(ID id);

    T save(T obj);

}
