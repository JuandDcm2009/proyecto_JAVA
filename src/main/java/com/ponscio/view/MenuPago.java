package com.ponscio.view;
import java.time.LocalDate;

import com.ponscio.Facade.MenuPagoF;
import com.ponscio.model.Pago;
import com.ponscio.model.valueobjects.IntegerV;
import com.ponscio.util.Scan;
import com.ponscio.view.interfaz.IMenu;
import com.ponscio.repository.PagoDAO;

public class MenuPago implements IMenu {
    private Scan scan;
    private PagoDAO pagoDAO;
    private MenuPagoF menuPagoF;

   public MenuPago() {
        this.scan = new Scan();
        this.pagoDAO = new PagoDAO();
        this.menuPagoF = new MenuPagoF(pagoDAO);
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
            case 2 -> pagarCuota();
            case 0 -> System.out.println("Regresando...");
            default -> System.out.println("Opcion invalida");
        }
    }

    private void pagarCuota() {
        try {
            int id_prestamo = scan.leerInt("> Ingrese el ID del prestamo a consignar: ");
            menuPagoF.pagarCuota(new Pago(0, id_prestamo, 0, LocalDate.now()));

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.toString());
        }
    }

    private void historialPago() {
        try {
            int id_cliente = menuPagoF.getClienteByDocumento(new IntegerV(scan.leerTexto("> Ingrese el documento del cliente: ")).getValue());
            System.out.println(menuPagoF.historialPagos(id_cliente));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.toString());
        }
    }

    public void mostrarMenu() {
        System.out.println("\n==============================================");
        System.out.println("              MENU PAGOS");
        System.out.println("==============================================\n");
        System.out.println("*\t1) Ver historial de pagos");
        System.out.println("*\t2) Pagar cuota mensual");
        System.out.println("*\t0) Regresar al menu principal");
        System.out.println("\n==============================================");
    } 
}
