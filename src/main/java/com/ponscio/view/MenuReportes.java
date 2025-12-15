package com.ponscio.view;

import com.ponscio.Facade.MenuReporteF;
import com.ponscio.util.Scan;

public class MenuReportes {
    private Scan scan;
    private MenuReporteF mReporteF;

    public MenuReportes() {
        this.mReporteF = new MenuReporteF();
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
            case 1 -> consultarPrestamosPagados();
            case 0 -> System.out.println("Regresando...");
            default -> System.out.println("Opcion invalida");
        }
    }

    public void consultarPrestamosPagados() {
        System.out.println(mReporteF.consultarPrestamosPagados());
    }

    public void mostrarMenu() {
        System.out.println("\n====================================");
        System.out.println("         MENU EMPLEADOS");
        System.out.println("====================================\n");
        System.out.println("*\t1) Consultar prestamos pagados");
        System.out.println("*\t1) Consultar prestamos pendientes");
        System.out.println("*\t0) Regresar al menu principal");
        System.out.println("\n====================================");
    }
}
