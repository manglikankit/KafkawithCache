package com.manglik.Rev1.Validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class PersonNameValidor implements ConstraintValidator<PersonNameValid, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        log.info("Message from isValid "+s);
        if(s.isBlank()){
            return false;
        }
        else {
            return true;
        }
    }
}
