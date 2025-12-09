package com.ponscio.model.valueobjects;

import com.ponscio.model.error.CrediYaError;
import com.ponscio.model.error.EmailValidatorError;

public class Email {
    private String value;
    private String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    public Email(String email) throws CrediYaError {
        if (!email.matches(EMAIL_REGEX)) {
            throw new EmailValidatorError("Mani ojo con eso");
        }
        value = email;
    }

    public String getValue() {
        return value;
    }

    
}
