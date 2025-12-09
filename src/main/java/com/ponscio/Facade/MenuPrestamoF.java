package com.ponscio.Facade;

import java.util.List;

import com.ponscio.model.Prestamo;
import com.ponscio.model.error.BussinesError;
import com.ponscio.model.error.CrediYaError;
import com.ponscio.repository.ClienteDAO;
import com.ponscio.repository.PrestamoDAO;
import com.ponscio.model.Cliente;

public class MenuPrestamoF {
    private PrestamoDAO prestamoDAO;
    private ClienteDAO clienteDAO;

    public MenuPrestamoF() {
        this.prestamoDAO = new PrestamoDAO();
        this.clienteDAO = new ClienteDAO();
    }

    
    
}


