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
        if (numero.precision() > maxInt) {
            throw new CrediYaError("El maximo numero de digitos permitido es de " + maxInt, BussinesError.FORMATO_INVALIDO_NUMERO);
        }

         if (numero.scale() > maxDecimal) {
            throw new CrediYaError("El maximo numero de digitos permitido es de " + maxDecimal, BussinesError.FORMATO_INVALIDO_NUMERO);
        }

        value = numero;
    }

    

}
