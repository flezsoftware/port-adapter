package com.example.portadapter.api.domain.controllers;

import com.example.portadapter.api.domain.model.PaymentSession;
import com.example.portadapter.api.domain.services.PaymentSessionService;
import com.example.portadapter.api.services.rest.model.PaymentSessionRest;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


public abstract class PaymentSessionController<T extends PaymentSession<ID>, ID> {

    protected final PaymentSessionService<T, ID> service;

    protected PaymentSessionController(PaymentSessionService<T, ID> service) {
        this.service = service;
    }


    @PostMapping
    public PaymentSession<ID> save(@RequestBody final PaymentSessionRest paymentSessionRest) {
        return service.saveFromRest(paymentSessionRest);
    }


    @GetMapping("/{id}")
    public T findById(@PathVariable("id") ID id) throws ChangeSetPersister.NotFoundException {
        return service.findById(id);
    }
}
