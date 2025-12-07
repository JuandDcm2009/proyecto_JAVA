package com.ponscio.view;
import java.util.Map;

import com.ponscio.model.Empleado;
import com.ponscio.repository.EmpleadoDAO;
import com.ponscio.service.Scan;
import com.ponscio.service.Facade.MenuEmpleadoF;

public class MenuEmpleado {
    
    private Scan scan;
    private EmpleadoDAO empleadoDAO;
    private MenuEmpleadoF empleadoF;

    public MenuEmpleado() {
        this.scan = new Scan();
        this.empleadoDAO = new EmpleadoDAO();
        this.empleadoF = new MenuEmpleadoF(empleadoDAO);
    }

    public void iniciar() {
        var option = -1;
        do {
            mostrarMenu();
            option = scan.leerInt("> Ingrese una opcion: ");
            leerOpcion(option);
        } while (option != 0);
    }

    public void leerOpcion(int opcion) {
        switch (opcion) {
            case 1 -> registrar();
            case 2 -> consultar();
            case 0 -> System.out.println("Regresando...");
        }
    }

    private void registrar() {
        var nombre = scan.leerTexto("> Ingrese el nombre del empleado: ");
        var documento = scan.leerTexto("> Ingrese el documento (CC) del empleado: ");

        System.out.println("\n> Roles disponibles: \n");
        Map<Integer, String> roles = empleadoDAO.getRoles();
        roles.forEach((rolId, rolNombre) -> System.out.println(rolId +") " + rolNombre));
        var rol = scan.leerInt("> Ingrese el Rol del empleado: ");
        var correo = scan.leerTexto("> Ingrese el correo del empleado: ");
        var salario = scan.leerDouble("> Ingrese el salario del empleado: ");

        Empleado empleado = new Empleado(0, nombre, documento, rol, correo, salario);
        empleadoF.registrar(empleado);
    }

    private void consultar() {
        System.out.println("\n====================================");
        System.out.println("         CONSULTAR POR: ");
        System.out.println("====================================\n");
        System.out.println("*\t1) Consultar por nombre");
        System.out.println("*\t2) Consultar por documento");
        System.out.println("*\t3) Consultar por ID");
        System.out.println("*\t0) Regresar");
        System.out.println("\n====================================");
        empleadoF.consultar(scan.leerInt("> Ingrese una opcion: "));
    }

    public void mostrarMenu() {
        System.out.println("\n====================================");
        System.out.println("         MENU EMPLEADOS");
        System.out.println("====================================\n");
        System.out.println("*\t1) Registrar empleado");
        System.out.println("*\t2) Consultar empleado");
        System.out.println("*\t0) Regresar al menu principal");
        System.out.println("\n====================================");
    }
    
}
