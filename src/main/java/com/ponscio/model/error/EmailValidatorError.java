package com.ponscio.model.error;

public class EmailValidatorError extends CrediYaError {

    public EmailValidatorError(String message) {
        super(message, BussinesError.FORMATO_INVALIDO_CORREO);
    }
    
}
