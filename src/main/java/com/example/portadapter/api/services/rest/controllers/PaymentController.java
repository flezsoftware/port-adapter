package com.example.portadapter.api.services.rest.controllers;

import com.example.portadapter.api.domain.controllers.PaymentSessionController;
import com.example.portadapter.api.domain.model.PaymentSession;
import com.example.portadapter.api.domain.services.PaymentSessionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("payment-session")
public class PaymentController extends PaymentSessionController<PaymentSession<String>,String> {
    public PaymentController(PaymentSessionService paymentSessionService) {
        super(paymentSessionService);
    }
}
