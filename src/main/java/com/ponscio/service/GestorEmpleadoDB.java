package com.ponscio.service;

import com.ponscio.repository.EmpleadoDAO;

public class GestorEmpleadoDB {
    
    private EmpleadoDAO empleadoDAO;

    public GestorEmpleadoDB() {
        this.empleadoDAO = new EmpleadoDAO();
    }

    public Boolean validarEmpleadoById(int id_empleado) {
        return empleadoDAO.validarEmpleado(id_empleado);
    } 

}
