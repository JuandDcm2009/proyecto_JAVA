package com.ponscio.model.valueobjects;

import java.math.BigDecimal;

import com.ponscio.model.error.BussinesError;
import com.ponscio.model.error.CrediYaError;

public class Bigecimal {
    private BigDecimal value;

    public BigDecimal getValue() {
        return value;
    }

    public Bigecimal(BigDecimal numero, int maxInt, int maxDecimal) throws CrediYaError {
        if (numero == null) {
            throw new CrediYaError("El número no puede ser nulo", BussinesError.FORMATO_INVALIDO_NUMERO);
        }
        
        int enteros = numero.precision() - numero.scale();
        if (enteros > maxInt) {
            throw new CrediYaError("Máx. dígitos enteros: " + maxInt, BussinesError.FORMATO_INVALIDO_NUMERO);
        }
        
        if (numero.scale() < 0 || numero.scale() > maxDecimal) {
            throw new CrediYaError("Máx. decimales: " + maxDecimal, BussinesError.FORMATO_INVALIDO_NUMERO);
        }
        
        value = numero;
    }

}
