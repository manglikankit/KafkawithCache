package com.manglik.Rev1.Validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = PersonNameValidor.class)

public @interface PersonNameValid {

    String message() default "Invalid Person name";

    //represent froup of constraints
    Class<?>[] groups() default {};

    //additional info of Person name

    Class<? extends Payload>[] payload() default {};
}
