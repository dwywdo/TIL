package com.euiyub.springarchive;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class EventValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Event.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "notempty", "Empty title is not allowed 1");
        Event event = (Event) target; // Does not support Generic, yet.
        if(event.getTitle() == null) {
            errors.reject("notempty", "Empty title is not allowed 2");
            // errors.rejectValue: Rejection for a specific field of instance.
        }
        // errorCode(notempty) is key value for resolving message by application context
        // defaultMessage(Empty title is not allowed) is for message in case that message key is not valid
    }
}
