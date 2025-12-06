package com.ponscio.model;

public class Empleado {
    private int id, documento;
    private String nombre, rol, correo;
    private double salario;
    
    public Empleado(int id, String nombre, int documento, String rol, String correo, double salario) {
        this.id = id;
        this.documento = documento;
        this.nombre = nombre;
        this.rol = rol;
        this.correo = correo;
        this.salario = salario;
    }

    public int getId() {
        return id;
    }
    public int getDocumento() {
        return documento;
    }
    public String getNombre() {
        return nombre;
    }
    public String getRol() {
        return rol;
    }
    public String getCorreo() {
        return correo;
    }
    public double getSalario() {
        return salario;
    }
    


}
