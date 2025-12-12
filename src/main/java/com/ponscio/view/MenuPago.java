package com.ponscio.view;
import java.time.LocalDate;

import com.ponscio.model.Pago;
import com.ponscio.util.Scan;
import com.ponscio.view.interfaz.IMenu;
import com.ponscio.repository.PagoDAO;

public class MenuPago implements IMenu {
    private Scan scan;
    private PagoDAO pagoDAO;

   public MenuPago() {
        this.scan = new Scan();
        this.pagoDAO = new PagoDAO();
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
            case 1 -> historialPago();
            case 2 -> estadoPago();
            case 0 -> System.out.println("Regresando...");
        }
    }

    private void historialPago() {

    }


    private void estadoPago() {

    }


    public void mostrarMenu() {
        System.out.println("\n====================================");
        System.out.println("         MENU PAGOS");
        System.out.println("====================================\n");
        System.out.println("*\t1) Ver historial de pagos");
        System.out.println("*\t1) Mostrar estado de cuenta");
        System.out.println("*\t0) Regresar al menu principal");
        System.out.println("\n====================================");
    } 
}
