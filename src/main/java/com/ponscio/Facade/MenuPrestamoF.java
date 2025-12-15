package com.ponscio.Facade;

import com.ponscio.model.Prestamo;
import com.ponscio.model.error.BussinesError;
import com.ponscio.model.error.CrediYaError;
import com.ponscio.repository.PrestamoDAO;
import com.ponscio.service.GestorClienteDB;
import com.ponscio.service.GestorEmpleadoDB;
import com.ponscio.util.PrintAdvise;

public class MenuPrestamoF {
    private PrestamoDAO prestamoDAO;
    private GestorClienteDB gClienteDB;
    private GestorEmpleadoDB gEmpleadoDB;

    public MenuPrestamoF(PrestamoDAO prestamoDAO) {
        this.prestamoDAO = prestamoDAO;
        this.gClienteDB = new GestorClienteDB();
        this.gEmpleadoDB = new GestorEmpleadoDB();
    }

    public void crearPrestamo(Prestamo prestamo) throws CrediYaError {
        if (!gClienteDB.validarClienteById(prestamo.getCliente_id())) 
            throw new CrediYaError("No se logro encontrar un cliente con el ID ingresado.", BussinesError.VALOR_INVALIDO_NULO);

        if (!gEmpleadoDB.validarEmpleadoById(prestamo.getEmpleado_id())) 
            throw new CrediYaError("No se logro encontrar un empleado con el ID ingresado.", BussinesError.VALOR_INVALIDO_NULO);


        if (prestamo != null && prestamoDAO.setPrestamo(prestamo)) {
            new PrintAdvise("Prestamo aprobado exitosamente");
            return;
        }

        throw new CrediYaError("Hubo un problema al gestionar el prestamo", BussinesError.ERROR_FALLO_PROCESO);
    }
}


