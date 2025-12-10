package com.ponscio.model.valueobjects;

import com.ponscio.model.error.BussinesError;
import com.ponscio.model.error.CrediYaError;

public class Letters {
    private String value;
    private String STRING_REGEX = "^[a-zA-Z\\s]+$";

    public Letters(String texto) throws CrediYaError {
        if (texto == null) throw new CrediYaError("El campo ingresado no puede ser NULO", BussinesError.VALOR_INVALIDO_NULO);
        if (!texto.matches(STRING_REGEX)) throw new CrediYaError("El nombre solo puede contener Letras del abecedario", BussinesError.VALOR_INVALIDO_STRING);
        value = texto;
    }

    public String getValue() {
        return value;
    }

}
