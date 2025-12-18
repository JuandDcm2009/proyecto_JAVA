package com.ponscio.view;

import com.ponscio.model.PrestamoDos;
import com.ponscio.model.valueobjects.MontoV;
import com.ponscio.service.GestorPrestamosDos;
import com.ponscio.util.Scan;

public class MenuPrestamoDos {

    private Scan scan;
    private GestorPrestamosDos gPrestamosDos;

    public MenuPrestamoDos() {
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
            default -> System.out.println("Opcion invalida");
        }
    }

    public void registrarPrestamo() {
        try {
            int cliente_id = scan.leerInt("> Ingrese el ID del cliente: ");
            double mont = new MontoV(scan.leerTexto("> Ingrese el monto del prestamo: ")).getValue();
            double interesMensual = new MontoV(scan.leerTexto("> Ingrese el porcentaje de interes mensual: ")).getValue();
            int plazoMeses = scan.leerInt("> Ingrese la cantidad de meses de plazo: "); 
            gPrestamosDos.registrarPrestamo(new PrestamoDos(0, cliente_id, mont, interesMensual, plazoMeses, "Activo"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.toString());
        }
    }

    public void listarPrestamos() {
        try {
            System.out.println("=========== Prestamos REGISTRADOS ===========");
            System.out.println(gPrestamosDos.listarPrestamos());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.toString());
        }
    }

    public void FiltrarPrestamos() {
        try {
            System.out.println("=========== Prestamos FILTRADOS ===========");
            System.out.println(gPrestamosDos.buscarPrestamoPorEstado(scan.leerTexto("> Ingrese el estado a buscar: ").trim().toLowerCase()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.toString());
        }
    }


    public void mostrarMenu() {
        System.out.println("\n==============================================");
        System.out.println("              MENU PRESTAMOS EXAMEN");
        System.out.println("==============================================\n");
        System.out.println("*\t1) Registrar prestamos");
        System.out.println("*\t2) Listar prestamos");
        System.out.println("*\t3) Filtrar prestamos por estado");
        System.out.println("*\t0) Regresar");
        System.out.println("\n==============================================");
    }
}
