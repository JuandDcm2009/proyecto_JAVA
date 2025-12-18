package com.ponscio.view;

import java.time.LocalDate;

import com.ponscio.Facade.MenuPrestamoF;
import com.ponscio.model.Prestamo;
import com.ponscio.model.error.BussinesError;
import com.ponscio.model.error.CrediYaError;
import com.ponscio.model.valueobjects.IntegerV;
import com.ponscio.model.valueobjects.InteresV;
import com.ponscio.model.valueobjects.MontoV;
import com.ponscio.repository.PrestamoDAO;
import com.ponscio.util.Scan;
import com.ponscio.view.interfaz.IMenu;

public class MenuPrestamos implements IMenu {
    private Scan scan;
    private PrestamoDAO prestamoDAO;
    private MenuPrestamoF prestamoF;

    public MenuPrestamos() {
        this.scan = new Scan();
        this.prestamoDAO = new PrestamoDAO();
        this.prestamoF = new MenuPrestamoF(prestamoDAO);
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
            default -> System.out.println("Opcion invalida");
        }
    }

    private void crearPrestamo() {
        try {
            Prestamo prestamo = null;
            int cliente_id = prestamoF.getClienteByDocumento(new IntegerV(scan.leerTexto("> Ingrese el documento del cliente: ")).getValue());
            int empleado_id = prestamoF.getEmpleadoByDocumento(new IntegerV(scan.leerTexto("> Ingrese el documento del empleado: ")).getValue());

            double monto = new MontoV(scan.leerTexto("> Ingrese el monto del prestamo: ")).getValue();

            double interes = new InteresV(scan.leerTexto("> Ingrese el interes para el prestamo: ")).getValue();
            int cuotas = scan.leerInt("> Ingrese el numero de cuotas que tendra el prestamo:");
            if (cuotas <= 0) throw new CrediYaError("El número de cuotas debe ser mayor que cero.", BussinesError.VALOR_FUERA_DE_RANGO);
            LocalDate fecha_prestamo = LocalDate.now();

            String estado = "pendiente";
            prestamo = new Prestamo(0, cliente_id, empleado_id, monto, interes, cuotas, fecha_prestamo, estado);
            prestamoF.crearPrestamo(prestamo);
        } 
        catch (CrediYaError e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void mostrarMenu() {
        System.out.println("\n==============================================");
        System.out.println("              MENU EMPLEADOS");
        System.out.println("==============================================\n");
        System.out.println("*\t1) Crear prestamo");
        System.out.println("*\t0) Regresar al menu principal");
        System.out.println("\n==============================================");
    }
}
