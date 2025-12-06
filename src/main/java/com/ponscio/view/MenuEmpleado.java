package com.ponscio.view;
import com.ponscio.service.Scan;

public class MenuEmpleado {
    
    private Scan scan;

    public MenuEmpleado() {
        this.scan = new Scan();
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
            case 0 -> System.out.println("Regresando...");
        }
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
