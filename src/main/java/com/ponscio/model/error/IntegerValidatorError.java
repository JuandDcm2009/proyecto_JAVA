package com.ponscio.model.error;

public class IntegerValidatorError extends CrediYaError {
    public IntegerValidatorError(String message) {
        super(message, BussinesError.FORMATO_INVALIDO_NUMERO);
    }
}
