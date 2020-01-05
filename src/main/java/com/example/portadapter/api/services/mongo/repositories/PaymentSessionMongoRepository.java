package com.example.portadapter.api.services.mongo.repositories;

import com.example.portadapter.api.domain.model.PaymentStatus;
import com.example.portadapter.api.domain.repositories.PaymentSessionRepository;
import com.example.portadapter.api.services.mongo.model.PaymentSessionMongo;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Profile("mongo")
@Repository
public interface PaymentSessionMongoRepository extends PaymentSessionRepository<PaymentSessionMongo, String> , CrudRepository<PaymentSessionMongo, String> {
    Optional<PaymentSessionMongo> findByAmountDueGuidAndStatusIn(String amountDueGuid, Set<PaymentStatus> statuses);
}
