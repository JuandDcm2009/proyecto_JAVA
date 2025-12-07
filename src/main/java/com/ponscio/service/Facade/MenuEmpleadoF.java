package com.ponscio.service.Facade;

import java.util.List;

import com.ponscio.model.Empleado;
import com.ponscio.repository.EmpleadoDAO;
import com.ponscio.service.Scan;

public class MenuEmpleadoF {

    private EmpleadoDAO empleadoDAO;
    private Scan scan;

    public MenuEmpleadoF(EmpleadoDAO empleadoDAO) {
        this.empleadoDAO = empleadoDAO;
        this.scan = new Scan();
    }

    public void registrar(Empleado empleado) {
        if (!empleado.getCorreo().contains("@")) {
            System.out.println("\nError: Correo invalido.");
        }

        if (empleadoDAO.validarEmpleado(empleado.getDocumento())) {
            System.out.println("\nError: El documento ingresado ya fue registrado por otro empleado");
            return;
        }
        empleadoDAO.setEmpleado(empleado);
    } 

    public void consultar(int option) {
        if (option == 0) return;

        switch (option) {
            case 1:
                var nombre = scan.leerTexto("> Ingrese el nombre a consultar: ").trim();
                List<Empleado> empleadosByNombre = empleadoDAO.getEmpleadoByNombre(nombre);
                resultadosConsulta(empleadosByNombre);
                break;
            case 2:
                var documento = scan.leerTexto("> Ingrese el documento a consultar: ").trim();
                List<Empleado> empleadosByDocumento = empleadoDAO.getEmpleadoByDocumento(documento);
                resultadosConsulta(empleadosByDocumento);
                break;
            case 3:
                var id = scan.leerInt("> Ingrese el ID a consultar: ");
                List<Empleado> empleadosById = empleadoDAO.getEmpleadoById(id);
                resultadosConsulta(empleadosById);
                break;
            default:
                System.out.println("\nError: Opcion no valida.");
                break;
        }

    }

    private void resultadosConsulta(List<Empleado> empleados) {
        if (empleados != null) {
            empleados.forEach((e) -> e.mostrarInfo(empleadoDAO.getRoles()));
            System.out.println("Resultados: " + empleados.size());
        };
    }

}
