package com.ponscio.view;
import java.time.LocalDate;

import com.ponscio.model.Prestamo;
import com.ponscio.repository.PrestamoDAO;
import com.ponscio.util.Scan;
import com.ponscio.view.interfaz.IMenu;

public class MenuPrestamos implements IMenu {
    private Scan scan;
    private PrestamoDAO prestamoDAO;

   public MenuPrestamos() {
        this.scan = new Scan();
        this.prestamoDAO = new PrestamoDAO();
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
            case 1 -> crearPrestamo();
            case 0 -> System.out.println("Regresando...");
        }
    }

    private void crearPrestamo() {
        int cliente_id = scan.leerInt("> Ingrese el id del cliente: ");
        int empleado_id = scan.leerInt("> Ingrese el id del empleado:");

        double monto = scan.leerInt("> Ingrese el monto del prestamo:");
        double interes = scan.leerInt("> Ingrese el interes para el pretamo:");
        int cuotas = scan.leerInt("> Ingrese el numero de cuotas que tendra el prestamo:");

        LocalDate fecha_inicio = LocalDate.now();
        String estado = "pendiente";
        Prestamo prestamo = new Prestamo(0,cliente_id, empleado_id, monto, interes, cuotas, fecha_inicio, estado);
        if (prestamoDAO.setPrestamo(prestamo)) {
            System.out.println("Muy bien, le vendiste el alma al diablo (campus)");
        }
    }

    public void mostrarMenu() {
        System.out.println("\n====================================");
        System.out.println("         MENU EMPLEADOS");
        System.out.println("====================================\n");
        System.out.println("*\t1) Crear prestamo");
        System.out.println("*\t0) Regresar al menu principal");
        System.out.println("\n====================================");
    } 
}
