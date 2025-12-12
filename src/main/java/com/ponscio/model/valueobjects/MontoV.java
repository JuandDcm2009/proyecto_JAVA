package com.ponscio.model.valueobjects;

import com.ponscio.model.error.CrediYaError;
import com.ponscio.model.error.BussinesError;

public class MontoV {

    private static final String DECIMAL_REGEX = "^\\d+(\\.\\d+)?$";

    private double value;

    public MontoV(String monto) throws CrediYaError {

        if (monto == null)
            throw new CrediYaError("El monto no puede ser nulo.", BussinesError.VALOR_INVALIDO_NULO);

        if (!monto.matches(DECIMAL_REGEX)) {
            throw new CrediYaError("El monto debe ser un número decimal válido.", BussinesError.FORMATO_INVALIDO_DECIMAL);
        }

        double valorConvertido = Double.parseDouble(monto);

        if (valorConvertido <= 0) {
            throw new CrediYaError("El monto debe ser mayor que 0.", BussinesError.FORMATO_INVALIDO_DECIMAL);
        }

        this.value = valorConvertido;
    }

    public double getValue() {
        return value;
    }
}
