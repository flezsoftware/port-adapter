package com.example.portadapter.api.services.redis.model;

import com.example.portadapter.api.domain.model.PaymentSession;
import com.example.portadapter.api.domain.model.PaymentStatus;
import com.example.portadapter.api.domain.validators.PaymentStatusRestricted;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Profile;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Profile("redis")
@RedisHash("payment-session")
@Getter
@Setter
@AllArgsConstructor
@PaymentStatusRestricted
public class PaymentSessionRedis extends PaymentSession<String> {
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
