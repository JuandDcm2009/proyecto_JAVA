package com.ponscio.model.valueobjects;

import com.ponscio.model.error.BussinesError;
import com.ponscio.model.error.CrediYaError;

public class TipoCedula {
    private String value;

    
    public TipoCedula(int option) throws CrediYaError {
        if (option == 1) value = "CC";
        else if (option == 2) value = "CE"; 
        else throw new CrediYaError("La opcion ingresada no es valida.", BussinesError.VALOR_INEXISTENTE_NUMERO);  
    }
    
    public String getValue() {
        return value;
    }
    
}
