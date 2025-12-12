package com.ponscio.model.valueobjects;

import com.ponscio.model.error.CrediYaError;
import com.ponscio.model.error.BussinesError;

public class InteresV {

    private static final String DECIMAL_REGEX = "^\\d+(\\.\\d+)?$";
    private double value;

    public InteresV(String interes) throws CrediYaError {

        if (interes == null) throw new CrediYaError("El interés no puede ser nulo.",BussinesError.VALOR_INVALIDO_NULO);

        if (!interes.matches(DECIMAL_REGEX)) throw new CrediYaError("El interés debe ser un número decimal válido.",BussinesError.FORMATO_INVALIDO_NUMERO);

        double interesConvertido = Double.parseDouble(interes);

        if (interesConvertido <= 0) {
            throw new CrediYaError("El interés debe ser mayor a 0.",BussinesError.FORMATO_INVALIDO_DECIMAL);
        }

        if (interesConvertido > 100) {
            throw new CrediYaError("El interés no puede ser mayor al 100%.",BussinesError.FORMATO_INVALIDO_DECIMAL);
        }

        this.value = interesConvertido;
    }

    public double getValue() {
        return value;
    }
}
