package com.example.portadapter.api.domain.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

//@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PaymentStatusValidator.class)
public @interface PaymentStatusRestricted {

    String message() default "{uniqie.status}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
