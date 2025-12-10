package com.ponscio.view;
import java.time.LocalDate;
import com.ponscio.model.Pago;
import com.ponscio.util.Scan;
import com.ponscio.repository.PagoDAO;

public class MenuPago{
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
            case 2 -> registrarAbono();
            case 0 -> System.out.println("Regresando...");
        }
    }

    private void historialPago() {
        System.out.println("Haz seleccionado la opcion historial de pagos");
    }

    private void registrarAbono() {
        try {
            int  prestamo_id = scan.leerInt("Ingresa el id del prestamo.");
            LocalDate fecha_pago = LocalDate.now();
            double monto = scan.leerDouble("Ingresa porfavor el monto a pagar.");


            pagoDAO.setPago(new Pago(0, prestamo_id, monto, fecha_pago));
            
        } catch (Exception e) {
            System.out.println("haaaaaa esta mal perrooo hppp, esta mnd exploto");
            // TODO: handle exception
        }
    }

    public void mostrarMenu() {
        System.out.println("\n====================================");
        System.out.println("         MENU PAGOS");
        System.out.println("====================================\n");
        System.out.println("*\t1) Ver historial de pagos");
        System.out.println("*\t2) Registrar abono");
        System.out.println("*\t0) Regresar al menu principal");
        System.out.println("\n====================================");
    } 
}
