package com.ponscio.model.valueobjects;

import com.ponscio.model.error.BussinesError;
import com.ponscio.model.error.CrediYaError;

public class IDS {

    private String value;
    private static final String ID_REGEX = "^\\d+$";

    public IDS(String ids) throws CrediYaError {

        if (ids == null)
            throw new CrediYaError("El id ingresado no puede ser NULO", BussinesError.VALOR_INVALIDO_NULO);

        if (!ids.matches(ID_REGEX)) {
            throw new CrediYaError(
                "El id solo puede contener dígitos, sin letras, sin espacios, sin signos",
                BussinesError.FORMATO_INVALIDO_NUMERO
            );
        }

        if (Integer.parseInt(ids) <= 0)
            throw new CrediYaError(
                "El id debe ser mayor a 0",
                BussinesError.CARACTER_NO_PERMITIDO
            );

        this.value = ids;
    }

    public String getValue() {
        return value;
    }
}

