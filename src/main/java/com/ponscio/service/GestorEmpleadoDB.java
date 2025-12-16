package com.ponscio.service;

import com.ponscio.model.Empleado;
import com.ponscio.repository.EmpleadoDAO;

public class GestorEmpleadoDB {
    
    private EmpleadoDAO empleadoDAO;

    public GestorEmpleadoDB() {
        this.empleadoDAO = new EmpleadoDAO();
    }

    public Boolean validarEmpleadoById(int id_empleado) {
        return empleadoDAO.validarEmpleado(id_empleado);
    } 

    public Boolean validarEmpleadoByDocumento(String documento) {
        return empleadoDAO.validarEmpleado(documento);
    }

    public Empleado getEmpleadoByDocumento(String documento) {
        return empleadoDAO.getEmpleadoByDocumento(documento).get(0);
    }

}
