package com.ponscio.model.error;

public enum BussinesError {
    FORMATO_INVALIDO_NUMERO (1001, "El valor ingresado no es un número entero válido."),
    FORMATO_INVALIDO_DECIMAL(1002, "El valor ingresado no es un número decimal válido."),
    CADENA_VACIA          (1003, "La entrada no puede estar vacía."),
    LONGITUD_INCORRECTA   (1004, "La longitud de la cadena no es la esperada."),
    VALOR_FUERA_DE_RANGO  (1005, "El valor numérico está fuera del rango permitido."),
    CARACTER_NO_PERMITIDO (1100, "La entrada contiene caracteres no permitidos."),
    FORMATO_INVALIDO_CORREO (1006, "La entrada no cumple con el formato de un Correo."),
    VALOR_REPETIDO_NUMERO  (1005, "El valor ingresado ya esta repetido.");



    private int code;
    private String description;

    
    private BussinesError(int errorCode, String description) {
        this.code = errorCode; 
        this.description = description;
    }

    public int getCode() {
        return code;
    }
    public String getDescription() {
        return description;
    }
    
    @Override
    public String toString() {
        return "ERROR [" + code + "]: " + description;
    }
}
