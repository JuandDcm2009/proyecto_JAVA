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
        if (!numero.matches(INT_REGEX)) {
            throw new IntegerValidatorError("Error inesperado");
        }

        value = numero;
    }


}


