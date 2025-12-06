package com.ponscio.view;
import com.ponscio.service.Scan;

public class MenuConsole {
    
    private Scan scan;
    private MenuEmpleado mEmpleado;

    public MenuConsole() {
        this.scan = new Scan();
        this.mEmpleado = new MenuEmpleado();
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
            case 1 -> mEmpleado.iniciar();
            case 0 -> System.out.println("Gracias por elegir a Ponscio Studio's company");
        }
    }

    public void mostrarMenu() {
        System.out.println("\n====================================");
        System.out.println("         MENU PRINCIPAL");
        System.out.println("====================================\n");
        System.out.println("*\t1) Gestionar empleados");
        System.out.println("*\t2) Gestionar clientes");
        System.out.println("*\t3) Gestionar prestamos");
        System.out.println("*\t4) Gestionar Pagos");
        System.out.println("*\t5) Gestionar reportes");
        System.out.println("\n====================================");
    }
    
}
