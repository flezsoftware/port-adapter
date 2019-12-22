package com.example.portadapter.api.services.rest.model;

import com.example.portadapter.api.domain.model.PaymentSession;
import com.example.portadapter.api.domain.model.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentSessionRest implements PaymentSession<String> {

    private String id;

    private String currency;

    private Double amount;

    private String country;

    private PaymentStatus status;

    private String amountDueGuid;
}
