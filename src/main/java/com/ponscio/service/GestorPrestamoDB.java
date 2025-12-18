package com.ponscio.service;

import com.ponscio.repository.PrestamoDAO;
import com.ponscio.model.Prestamo;
import java.util.List;

public class GestorPrestamoDB {
    private PrestamoDAO prestamoDAO;

    public GestorPrestamoDB() {
        this.prestamoDAO = new PrestamoDAO();    
    }
    
    public List<Prestamo> getPrestamosbyCliente(int cliente_id) {
        return prestamoDAO.getPrestamosByClienteId(cliente_id);
    }

    public List<Prestamo> getPrestamosById(int id) {
        return prestamoDAO.getPrestamosById(id);
    }

    public Boolean validarPrestamoById(int id) {
        return prestamoDAO.validarPrestamoById(id);
    }
    public void updateEstadoToPagado(int id_prestamo) {
        prestamoDAO.updateEstadoToPagado(id_prestamo);
    }

    public List<Prestamo> PrestamosVencidos(int prestamo_id) {
        return prestamoDAO.getPrestamosByClienteId(prestamo_id);
    }

    public List<Prestamo> PrestamosActivos(int cliente_id) {
        return prestamoDAO.getPrestamosByClienteId(cliente_id);

    }
    public List<Prestamo> MostrarResumen(int cliente_id) {
            return prestamoDAO.getPrestamosByClienteId(cliente_id);
        
    
    }

}


