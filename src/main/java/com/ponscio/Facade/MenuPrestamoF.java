package com.ponscio.Facade;

import com.ponscio.model.Prestamo;
import com.ponscio.model.error.BussinesError;
import com.ponscio.model.error.CrediYaError;
import com.ponscio.repository.PrestamoDAO;
import com.ponscio.util.PrintAdvise;

public class MenuPrestamoF {
    private PrestamoDAO prestamoDAO;

    public MenuPrestamoF(PrestamoDAO prestamoDAO) {
        this.prestamoDAO = prestamoDAO;
    }

    public void crearPrestamo(Prestamo prestamo) throws CrediYaError {
        if (prestamo != null && prestamoDAO.setPrestamo(prestamo)) {
            new PrintAdvise("Prestamo aprobado exitosamente");
        }

        throw new CrediYaError("Hubo un problema al gestionar el prestamo", BussinesError.ERROR_FALLO_PROCESO);
    }
}


