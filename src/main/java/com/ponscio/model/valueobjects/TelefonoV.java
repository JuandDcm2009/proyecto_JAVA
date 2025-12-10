package com.ponscio.model.valueobjects;

import com.ponscio.model.error.BussinesError;
import com.ponscio.model.error.CrediYaError;

public class TelefonoV {
    
    private String value;
    private String INT_REGEX = "\\d+";

    public TelefonoV(String telefono) throws CrediYaError {
        if (telefono == null) throw new CrediYaError("El telefono ingresado no puede ser NULO", BussinesError.VALOR_INVALIDO_NULO);

        if (!telefono.matches(INT_REGEX)) {
            throw new CrediYaError("El telefono solo puede contener numeros enteros", BussinesError.FORMATO_INVALIDO_NUMERO);
        }

        if (telefono.length() > 10 || telefono.length() < 9) {
            throw new CrediYaError("El telefono solo puede contener entre 9 y 10 digitos", BussinesError.VALOR_FUERA_DE_RANGO);
        }

        value = telefono;
    }

    public String getValue() {
        return value;
    }

}
