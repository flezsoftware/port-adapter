package com.example.portadapter.api.domain.model;


public interface PaymentSession<ID> {

    ID getId();

    String getCurrency();

    String getCountry();

    ID getAmountDueGuid();

    PaymentStatus getStatus();

    Double getAmount();

    void setId(ID id);

    void setAmountDueGuid(ID id);

    void setStatus(PaymentStatus status);

    void setCurrency(String currency);

    void setCountry(String country);

    void setAmount(Double amount);


}

