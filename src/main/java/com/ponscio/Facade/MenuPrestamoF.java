package com.ponscio.Facade;

import com.ponscio.model.Cliente;
import com.ponscio.model.Empleado;
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

    public int getClienteByDocumento(String documento) throws CrediYaError {
        if (!gClienteDB.validarClienteByDocumento(documento)) 
            throw new CrediYaError("El documento ingresado es invalido.", BussinesError.VALOR_INEXISTENTE_NUMERO);

        Cliente cliente = gClienteDB.getClienteByDocumento(documento);
        
        if (cliente == null)
            throw new CrediYaError("Hubo un problema al consultar por el cliente", BussinesError.ERROR_FALLO_PROCESO);

        return cliente.getId();
    }

     public int getEmpleadoByDocumento(String documento) throws CrediYaError {
        if (!gEmpleadoDB.validarEmpleadoByDocumento(documento)) 
            throw new CrediYaError("El documento ingresado es invalido.", BussinesError.VALOR_INEXISTENTE_NUMERO);

        Empleado empleado = gEmpleadoDB.getEmpleadoByDocumento(documento);
        if (empleado == null)
            throw new CrediYaError("Hubo un problema al consultar por el empleado", BussinesError.ERROR_FALLO_PROCESO);

        return empleado.getId();
    }


}


