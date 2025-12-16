package com.ponscio.view;
import com.ponscio.util.Scan;
import com.ponscio.view.interfaz.IMenu;

public class MenuConsole implements IMenu {
    
    private Scan scan;
    private MenuEmpleado mEmpleado;
    private MenuPago mPago;
    private MenuCliente mCliente;
    private MenuPrestamos mPrestamos;
    private MenuReportes mReportes;

    public MenuConsole() {
        this.scan = new Scan();
        this.mEmpleado = new MenuEmpleado();
        this.mCliente = new MenuCliente();
        this.mPrestamos = new MenuPrestamos();
        this.mPago = new MenuPago();
        this.mReportes = new MenuReportes();
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
            case 2 -> mCliente.iniciar();
            case 3 -> mPrestamos.iniciar();
            case 4 -> mPago.iniciar();
            case 5 -> mReportes.iniciar();
            case 0 -> System.out.println("Gracias por elegir a Ponscio Studio's company");
            default -> System.out.println("Opcion invalida");
        }
    }

    public void mostrarMenu() {
        System.out.println("\n==============================================");
        System.out.println("              MENU PRINCIPAL");
        System.out.println("==============================================\n");
        System.out.println("*\t1) Gestionar empleados");
        System.out.println("*\t2) Gestionar clientes");
        System.out.println("*\t3) Gestionar prestamos");
        System.out.println("*\t4) Gestionar pagos");
        System.out.println("*\t5) Gestionar reportes");
        System.out.println("\n==============================================");
    }
    
}
