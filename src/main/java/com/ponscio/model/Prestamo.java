package com.ponscio.model;

import java.time.LocalDate;

import com.mysql.cj.xdevapi.Client;

public class Prestamo {
    private String estado;
    private double monto, interes;
    private int cuotas, id, cliente_id, empleado_id;
    private LocalDate fecha_inicio;
    
    public Prestamo(int id, int cliente_id, int empleado_id, double monto, double interes, int cuotas,
            LocalDate fecha_inicio, String estado) {
        this.cliente_id = cliente_id;
        this.empleado_id = empleado_id;
        this.estado = estado;
        this.monto = monto;
        this.interes = interes;
        this.cuotas = cuotas;
        this.id = id;
        this.fecha_inicio = fecha_inicio;
    }

    public int getCliente_id() {
        return cliente_id;
    }
    public int getEmpleado_id() {
        return empleado_id;
    }
    public String getEstado() {
        return estado;
    }
    public double getMonto() {
        return monto;
    }
    public double getInteres() {
        return interes;
    }
    public int getCuotas() {
        return cuotas;
    }
    public int getId() {
        return id;
    }
    public LocalDate getFecha_inicio() {
        return fecha_inicio;
    }

    public String mostrarInfo(Cliente cliente) {
        String info = "";
        info += "\n============= Prestamo ==============";
        info += "\n\tCliente ID: " + cliente.getId();
        info += "\n\tCliente: " + cliente.getNombre();
        info += "\n\tFecha inicio: " + fecha_inicio;
        info += "\n\tMonto: " + monto;        
        info += "\n\tInteres: " + interes +"%";
        info += "\n\tCuotas: " + cuotas;
        info += "\n\tEstado: " + estado;
        info += "\n======================================";
        return info;
    }
    
}