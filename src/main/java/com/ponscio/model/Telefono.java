package com.ponscio.model;

public class Telefono {
    private int id, pais_id;
    private String numero;

    public int getId() {
        return id;
    }

    public String getNumero() {
        return numero;
    }

    public int getPais_id() {
        return pais_id;
    }

    public Telefono(int id, String numero, int pais_id) {
        this.id = id;
        this.numero = numero;
        this.pais_id = pais_id;
    }
}
