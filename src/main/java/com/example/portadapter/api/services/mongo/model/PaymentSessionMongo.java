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
import org.springframework.data.mongodb.core.mapping.Field;

@Profile("mongo")
@Document
@Getter
@Setter
@AllArgsConstructor
@PaymentStatusRestricted
public class PaymentSessionMongo extends PaymentSession<String> {

    @Id
    private String id;

    @Indexed
    private String currency;

    private Double amount;

    @Indexed
    private PaymentStatus status;

    @Indexed
    private String amountDueGuid;

}
