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
            case 1 -> consultarPrestamosVencidos();
            case 2 -> consultarPrestamosActivos();
            case 3 -> consultarClienteCE();
            case 4 -> consultarClienteCC();
            case 0 -> System.out.println("Regresando...");
            default -> System.out.println("Opcion invalida");
        }
    }

    public void consultarPrestamosVencidos() {
        System.out.println("\n============ Prestamos VENCIDOS ============");
        System.out.println(mReporteF.consultarPrestamosEstado("VENCIDO"));
    }

    public void consultarPrestamosActivos() {
        System.out.println("\n=========== Prestamos ACTIVOS ===========");
        System.out.println(mReporteF.consultarPrestamosEstado("ACTIVO"));
    }

    public void consultarClienteCE() {
        System.out.println("\n=========== Clientes Extranjeros ===========");
        System.out.println(mReporteF.consultarClientesC("CE"));
    }

    public void consultarClienteCC() {
        System.out.println("\n=========== Clientes Connacionales ===========");
        System.out.println(mReporteF.consultarClientesC("CC"));
    }

    public void mostrarMenu() {
        System.out.println("\n==============================================");
        System.out.println("              MENU REPORTES EXAMEN");
        System.out.println("============================\n");
        System.out.println("*\t1) Prestamos VENCIDOS");
        System.out.println("*\t2) Prestamos ACTIVOS");
        System.out.println("*\t3) Clientes Extranjeros");
        System.out.println("*\t4) Clientes Connacionales");
        System.out.println("*\t0) Regresar al menu principal");
        System.out.println("\n==============================================");
    }
}