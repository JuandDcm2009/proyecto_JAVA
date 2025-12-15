package com.ponscio.model;

import java.time.LocalDate;


public class Pago {
    private int id, prestamo_id;
    private LocalDate fecha_pago;
    private double monto;
   
    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Pago(int id,  int prestamo_id, double monto, LocalDate fecha_pago) {
        this.id = id;
        this.prestamo_id = prestamo_id;
        this.fecha_pago = fecha_pago;
        this.monto = monto;
    }  

    public int getId() {
        return id;
    }

    public int getPrestamo_id() {
        return prestamo_id;
    }

    public LocalDate getFecha_pago() {
        return fecha_pago;
    }

    public double getMonto() {
        return monto;
    }

    public String mostrarInfo(Cliente cliente) {
        String info = "";
        info += "\n=========== Informacion Pago ==========\n";
        info += "\n\n\tTitular: " + cliente.getNombre();
        info += "\n\tID prestamo asociado: " + prestamo_id;
        info += "\n\tMonto pagado: $" + String.format("%,.2f", monto);
        info += "\n\tFecha de pago: " + fecha_pago;
        info += "\n\n=======================================";
        return info;
    }


}