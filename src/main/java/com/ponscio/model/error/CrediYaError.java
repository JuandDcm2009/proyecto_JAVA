package com.ponscio.model.error;


public class CrediYaError extends Exception {
    private BussinesError errorType;

    public CrediYaError(String message, BussinesError type) {
        super(message);
        this.errorType = type;
    }

    @Override
    public String toString() {
        return  errorType.toString();
    }    
}