package com.ponscio.Facade;

import java.util.List;

import com.ponscio.model.Prestamo;
import com.ponscio.model.error.BussinesError;
import com.ponscio.model.error.CrediYaError;
import com.ponscio.repository.ClienteDAO;
import com.ponscio.repository.PrestamoDAO;
import com.ponscio.util.PrintAdvise;

public class MenuPrestamoF {
    private PrestamoDAO prestamoDAO;
    private ClienteDAO clienteDAO;

    public MenuPrestamoF(PrestamoDAO prestamoDAO) {
        this.prestamoDAO = prestamoDAO;
        this.clienteDAO = new ClienteDAO();
    }

    public void crearPrestamo(Prestamo prestamo) throws CrediYaError {
        if (prestamo != null && prestamoDAO.setPrestamo(prestamo)) {
            new PrintAdvise("Prestamo aprobado exitosamente");
        }

        throw new CrediYaError("Hubo un problema al gestionar el prestamo", BussinesError.ERROR_FALLO_PROCESO);
    }
}


