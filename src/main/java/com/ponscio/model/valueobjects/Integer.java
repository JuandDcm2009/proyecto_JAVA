package com.ponscio.model.valueobjects;

import com.ponscio.model.error.CrediYaError;
import com.ponscio.model.error.IntegerValidatorError;

public class Integer {
    
    private String value;
    
    public String getValue() {
        return value;
    }

    private String INT_REGEX = "\\d+";
    
    public Integer(String numero) throws CrediYaError {
        if (numero == null) throw new IntegerValidatorError("El valor ingresado es nulo.");
        
        if (!numero.matches(INT_REGEX) && numero != null) {
            throw new IntegerValidatorError("El valor ingresado debe ser de tipo numerico del 0 al 9.");
        }

        value = numero;
    }
}


