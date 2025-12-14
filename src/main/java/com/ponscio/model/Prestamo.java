package com.ponscio.model;

import java.time.LocalDate;

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
        double interesTotal = monto + (monto * (interes / 100.0));
        String info = "";
        info += "\n============= Prestamo ==============\n";
        info += "\n\tID #" + id;
        info += "\n\n\tCliente ID: " + cliente.getId();
        info += "\n\tCliente: " + cliente.getNombre();
        info += "\n\tFecha inicio: " + fecha_inicio;
        info += "\n\n\tMonto: $" + String.format("%,.2f", monto);
        info += "\n\tInteres: " + interes +"%";
        info += "\n\tCuotas: " + cuotas;
        info += "\n\tEstado: " + estado.toUpperCase();
        info += "\n\n\tMonto con interes por cuota: $" + String.format("%,.2f", interesTotal / cuotas);
        info += "\n\tMonto con interes Total: $" + String.format("%,.2f",interesTotal );
        info += "\n\n=====================================";
        return info;
    }

    // 2000 * (20 / 100)
    // 20% -> 2000 = 400
    // 2000 + 400 = Interes Simple.
}