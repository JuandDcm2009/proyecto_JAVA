package com.ponscio.model.valueobjects;

import com.ponscio.model.error.BussinesError;
import com.ponscio.model.error.CrediYaError;
import com.ponscio.model.error.EmailValidatorError;

public class Email {
    private String value;
    private String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    public Email(String email) throws CrediYaError {
        if (email == null) {
            throw new CrediYaError("Email vacio; Tienes que ingresar un email.", BussinesError.VALOR_INVALIDO_NULO);
        }

        if (!email.matches(EMAIL_REGEX)) {
            throw new EmailValidatorError("El email ingresado es invalido.");
        }

        value = email;
    }

    public String getValue() {
        return value;
    }
}
