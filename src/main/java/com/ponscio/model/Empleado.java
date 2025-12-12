package com.ponscio.model;
import java.math.BigDecimal;
import java.util.Map;

public class Empleado {
    private int id, rol_id;
    private String nombre, correo, documento_numero, documento_tipo;
    private BigDecimal salario;
    
    public Empleado(int id, String nombre, String documento_numero, String documento_tipo,int rol_id, String correo, BigDecimal salario) {
        this.id = id;
        this.nombre = nombre;
        this.documento_numero = documento_numero;
        this.documento_tipo = documento_tipo;
        this.rol_id = rol_id;
        this.correo = correo;
        this.salario = salario;
    }

    public int getId() {
        return id;
    }
    public String getDocumentoNumero() {
        return documento_numero;
    }
    public String getDocumentoTipo() {
        return documento_tipo;
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
    public BigDecimal getSalario() {
        return salario;
    }
    
    public String mostrarInfo(Map<Integer, String> roles) {
        String info = "";

        info += "\n================ Empleado ================";
        info += "\n\n\tID #"+id;
        info += "\n\tNombre: "+nombre;
        info += "\n\tDocumento: "+documento_numero+" ("+documento_tipo+")";
        info += "\n\tCorreo: "+correo;
        info += "\n\tSalario actual: $"+ salario;
        info += "\n\tCargo Actual: "+ roles.get(rol_id);
        info += "\n\n==========================================";
        return info;
    }

}
