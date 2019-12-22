package com.example.portadapter.api.domain.validators;

import com.example.portadapter.api.domain.model.PaymentSession;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Set;

//@Component
@RequiredArgsConstructor
public class PaymentSessionValidator<T extends PaymentSession<ID>, ID> {
    private final Validator validator;

    public void validate(T paymentSession) {
        final Set<ConstraintViolation<T>> violations = validator.validate(paymentSession);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}
