package com.ponscio.model;

public class PrestamoDos {
    private int id, cliente_id, plazoMeses;
    private double monto, interesMensual;
    private String estado;
    private double total;


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public int getCliente_id() {
        return cliente_id;
    }


    public void setCliente_id(int cliente_id) {
        this.cliente_id = cliente_id;
    }


    public int getPlazoMeses() {
        return plazoMeses;
    }


    public void setPlazoMeses(int plazoMeses) {
        this.plazoMeses = plazoMeses;
    }


    public double getMonto() {
        return monto;
    }


    public void setMonto(double monto) {
        this.monto = monto;
    }


    public double getInteresMensual() {
        return interesMensual;
    }


    public void setInteresMensual(double interesMensual) {
        this.interesMensual = interesMensual;
    }


    public String getEstado() {
        return estado;
    }


    public void setEstado(String estado) {
        this.estado = estado;
    }


    public PrestamoDos(int id, int cliente_id, double monto, double interesMensual, int plazoMeses, String estado) {
        this.id = id;
        this.cliente_id = cliente_id;
        this.monto = monto;
        this.interesMensual = interesMensual;
        this.plazoMeses = plazoMeses; 
        this.estado = estado;
        this.total = (monto + (monto * interesMensual / 100));
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("\n======== Informacion Prestamao2");
        string.append("\nID #" + id);
        string.append("\ncliente_id: " + cliente_id);
        string.append("\nMonto: " + String.format("%,.2f", monto));
        string.append("\nInteres mensual: " + interesMensual +"%");
        string.append("\nPlazo meses: " + plazoMeses);
        string.append("\nEstado: " + estado.toUpperCase());
        string.append("\nTotal: " + total);
        return string.toString();
    }

    
}
