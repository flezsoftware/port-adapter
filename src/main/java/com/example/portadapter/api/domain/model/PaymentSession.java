package com.example.portadapter.api.domain.model;


public abstract class PaymentSession<ID> {

    public abstract ID getId();

    public abstract String getCurrency();

    public abstract ID getAmountDueGuid();

    public abstract PaymentStatus getStatus();

    public abstract Double getAmount();

/*    public abstract void setId(ID id);

    public abstract void setAmountDueGuid(ID id);

    public abstract void setStatus(PaymentStatus status);

    public abstract void setCurrency(String currency);

    public abstract void setCountry(String country);

    public abstract void setAmount(Double amount);*/


}

