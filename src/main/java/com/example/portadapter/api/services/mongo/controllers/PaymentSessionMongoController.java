package com.example.portadapter.api.services.mongo.controllers;

import com.example.portadapter.api.domain.controllers.PaymentSessionController;
import com.example.portadapter.api.services.mongo.model.PaymentSessionMongo;
import com.example.portadapter.api.services.mongo.services.PaymentSessionMongoService;

/*@Profile("mongo")
@RestController
@RequestMapping("payment-session2")*/
public class PaymentSessionMongoController extends PaymentSessionController<PaymentSessionMongo, String> {
    public PaymentSessionMongoController(PaymentSessionMongoService service) {
        super(service);
    }
}
