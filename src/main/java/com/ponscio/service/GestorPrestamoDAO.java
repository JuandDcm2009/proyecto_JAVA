package com.ponscio.service;

import com.ponscio.repository.PrestamoDAO;
import com.ponscio.model.Prestamo;
import java.util.List;

public class GestorPrestamoDAO {
    private PrestamoDAO prestamoDAO;

    public GestorPrestamoDAO() {
        this.prestamoDAO = new PrestamoDAO();    
    }
    
    public List<Prestamo> getPrestamos(int cliente_id) {
        return prestamoDAO.getPrestamos(cliente_id);
    }
}
