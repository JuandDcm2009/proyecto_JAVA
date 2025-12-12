package com.ponscio.service;

import com.ponscio.repository.PrestamoDAO;
import com.ponscio.model.Prestamo;
import java.util.List;

public class GestorPrestamoDB {
    private PrestamoDAO prestamoDAO;

    public GestorPrestamoDB() {
        this.prestamoDAO = new PrestamoDAO();    
    }
    
    public List<Prestamo> getPrestamos(int cliente_id) {
        return prestamoDAO.getPrestamos(cliente_id);
    }
}
