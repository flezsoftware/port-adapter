package com.example.portadapter.api.domain.validators;

import com.example.portadapter.api.domain.model.PaymentSession;
import com.example.portadapter.api.domain.services.PaymentSessionService;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
class PaymentStatusValidator implements ConstraintValidator<PaymentStatusRestricted, PaymentSession<String>> {

    private final PaymentSessionService paymentSessionService;

    @Override
    public boolean isValid(PaymentSession<String> value, ConstraintValidatorContext context) {
        if (value.getStatus() == null) {
            return true;
        }
        return paymentSessionService.findByAmountDueGuidRestrictedStatus(value.getAmountDueGuid()).isEmpty();
    }
}