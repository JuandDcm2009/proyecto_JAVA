package com.ponscio.Facade;

import java.util.List;
import com.ponscio.model.Empleado;
import com.ponscio.repository.EmpleadoDAO;

import java.util.Map;

public class MenuEmpleadoF {

    private EmpleadoDAO empleadoDAO;

    public MenuEmpleadoF(EmpleadoDAO empleadoDAO) {
        this.empleadoDAO = empleadoDAO;
    }

    public String registrar(Empleado empleado) {
        if (empleado.getRol() > empleadoDAO.getRoles().size()) {
            return "\nError: El ID del Cargo a asignar es invalido";
        }

        if (!empleado.getCorreo().contains("@")) {
            return "\nError: Correo invalido.";
        }

        if (empleadoDAO.validarEmpleado(empleado.getDocumento())) {
            return "\nError: El documento ingresado ya fue registrado por otro empleado";
        }

        empleadoDAO.setEmpleado(empleado);
        return "\nEmpleado guardado.";
    } 

    public List<Empleado> consultarByNombre(String nombre) {
        return empleadoDAO.getEmpleadoByNombre(nombre);
    }

    public List<Empleado> consultarById(int id) {
        return empleadoDAO.getEmpleadoById(id);
    }

    public List<Empleado> consultarByDocumento(String documento) {
        return empleadoDAO.getEmpleadoByDocumento(documento);
    }

    public String mostrarResultados(List<Empleado> empleados) {
        String resultados = "";
        for (Empleado e: empleados) {
            resultados += e.mostrarInfo(empleadoDAO.getRoles());
        }
    
         return resultados;
    }

    public String eliminar(int id) {
        if (empleadoDAO.eliminarEmpleado(id)) return "Empleado correctamente eliminado con el ID: #" + id; 
        else return "Error: No se pudo eliminar empleado; Id invalido";
    }

    public String getRoles() {
        String lista = "";
        Map<Integer,String> roles = empleadoDAO.getRoles();
        for (Map.Entry<Integer,String> r : roles.entrySet()) {
            lista += "\n" + r.getKey() + ") " + r.getValue();
        }
        return lista;
    }
}
