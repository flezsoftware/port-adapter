package com.example.portadapter.api.services.mongo.model;

import com.example.portadapter.api.domain.model.PaymentSession;
import com.example.portadapter.api.domain.model.PaymentStatus;
import com.example.portadapter.api.domain.validators.PaymentStatusRestricted;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Profile;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Profile("mongo")
@Document
@Getter
@Setter
@AllArgsConstructor
@PaymentStatusRestricted
public class PaymentSessionMongo implements PaymentSession<String> {
    @Id
    private String id;

    @Indexed(unique = true)
    private String currency;

    private Double amount;

    @Indexed(unique = true)
    private String country;

    @Indexed
    private PaymentStatus status;

    private String amountDueGuid;

}
