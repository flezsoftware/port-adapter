package com.example.portadapter.api.services.kafka.model;

import com.example.portadapter.api.domain.model.PaymentSession;
import com.example.portadapter.api.domain.model.PaymentStatus;
import com.example.portadapter.api.domain.validators.PaymentStatusRestricted;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@PaymentStatusRestricted
public class PaymentSessionKafkaDomain extends PaymentSession<String> {

    private String id;

    private String currency;

    private Double amount;

    private PaymentStatus status;

    private String amountDueGuid;
}
