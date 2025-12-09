package com.ponscio.model;

public class Cliente {
    
    private int id, telefono_id;
    private String nombre, correo, documento_tipo, documento_numero;
    
    public int getId() {
        return id;
    }
    
    public Cliente(int id, String nombre, String documento_numero, String documento_tipo, String correo, int telefono_id) {
        this.id = id;
        this.nombre = nombre;
        this.documento_numero = documento_numero;
        this.documento_tipo = documento_tipo;
        this.correo = correo;
        this.telefono_id = telefono_id;

    }

    public String getNombre() {
        return nombre;
    }

    public String getDocumentoNumero() {
        return documento_numero;
    }

    public String getDocumentoTipo() {
        return documento_tipo;
    }

    public String getCorreo() {
        return correo;
    }

    public int getTelefonoId() {
        return telefono_id;
    }

    public String mostrarInfo(Telefono telefono, String codigoPais) {
        String info = "";
        if (telefono != null) {
            info += "\n================ Cliente ================";
            info += "\n\n\tID #"+id;
            info += "\n\tNombre: "+nombre;
            info += "\n\tDocumento: " + documento_numero + " (" + documento_tipo + ")";
            info += "\n\tCorreo: "+correo;
            info += "\n\tTelefono: ("+codigoPais+") " + telefono.getNumero();
            info += "\n\n==========================================";
        }
        return info;
    }

}
