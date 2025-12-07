package com.ponscio.model;
import java.util.Map;

public class Empleado {
    private int id, rol_id;
    private String nombre, correo, documento;
    private double salario;
    
    public Empleado(int id, String nombre, String documento, int rol_id, String correo, double salario) {
        this.id = id;
        this.documento = documento;
        this.nombre = nombre;
        this.rol_id = rol_id;
        this.correo = correo;
        this.salario = salario;
    }

    public int getId() {
        return id;
    }
    public String getDocumento() {
        return documento;
    }
    public String getNombre() {
        return nombre;
    }
    public int getRol() {
        return rol_id;
    }
    public String getCorreo() {
        return correo;
    }
    public double getSalario() {
        return salario;
    }
    
    public void mostrarInfo(Map<Integer, String> roles) {

        System.out.println("================ Empleado ================");
        System.out.println("\n\tID #"+id);
        System.out.println("\n\tNombre: "+nombre);
        System.out.println("\tDocumento: "+documento);
        System.out.println("\tCorreo: "+correo);
        
        System.out.println("\n\tSalario actual: "+salario);
        System.out.println("\tCargo Actual: "+ roles.get(rol_id));
        System.out.println("\n==========================================");

    }

}
