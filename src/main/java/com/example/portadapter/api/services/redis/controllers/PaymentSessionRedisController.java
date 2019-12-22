package com.example.portadapter.api.services.redis.controllers;

import com.example.portadapter.api.domain.controllers.PaymentSessionController;
import com.example.portadapter.api.services.redis.model.PaymentSessionRedis;
import com.example.portadapter.api.services.redis.services.PaymentSessionRedisService;

/*@Profile("redis")
@RestController
@RequestMapping("payment-session2")*/
public class PaymentSessionRedisController extends PaymentSessionController<PaymentSessionRedis, String> {
    public PaymentSessionRedisController(PaymentSessionRedisService service) {
        super(service);
    }
}
