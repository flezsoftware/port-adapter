package com.example.portadapter.api.domain.repositories;

import com.example.portadapter.api.domain.model.PaymentStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PaymentSessionRepository<T, ID> extends CrudRepository<T, ID> {

    Boolean existsByCountry(String country);

    Optional<T> findByAmountDueGuidAndStatusOrStatus(ID amountDueGuid, PaymentStatus status1, PaymentStatus status2);

    Optional<T> findByStatus(PaymentStatus status);

    Optional<T> findByCountry(ID s);
}
