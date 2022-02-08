package com.example.springbootvalidation.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = ProductIdExistingValidator.class)
public @interface ProductIdExisting {
    String message() default "ProductId is taken";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
