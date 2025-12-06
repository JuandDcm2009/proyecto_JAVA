package com.ponscio.view;
import com.ponscio.model.Empleado;
import com.ponscio.repository.EmpleadoDAO;
import com.ponscio.service.Scan;

public class MenuEmpleado {
    
    private Scan scan;
    private EmpleadoDAO empleadoDAO;

    public MenuEmpleado() {
        this.scan = new Scan();
        this.empleadoDAO = new EmpleadoDAO();
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
            case 0 -> System.out.println("Regresando...");
        }
    }

    private void registrar() {
        var nombre = scan.leerTexto("\n> Ingrese el nombre del empleado: ");
        var documento = scan.leerTexto("\n> Ingrese el documento (CC) del empleado: ");
        var rol = scan.leerInt("\n> Ingrese el Rol del empleado: ");
        var correo = scan.leerTexto("\n> Ingrese el correo del empleado: ");
        var salario = scan.leerInt("\n salario: ");
        Empleado empleado = new Empleado(0, nombre, documento, rol, correo, 1000.00);
        empleadoDAO.setEmpleado(empleado);
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
