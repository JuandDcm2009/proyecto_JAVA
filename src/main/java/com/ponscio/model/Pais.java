package com.ponscio.model;

public class Pais {
    private String nombre, codigo; 
    private int id, continente_id;
    
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public int getContinente_id() {
        return continente_id;
    }

    public Pais(int id, String nombre, String codigo, int continente_id) {
        this.id = id;
        this.nombre = nombre;
        this.codigo = codigo;
        this.continente_id = continente_id;
    }
}
