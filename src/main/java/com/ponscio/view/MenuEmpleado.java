package com.ponscio.view;

import com.ponscio.Facade.MenuEmpleadoF;
import com.ponscio.model.Empleado;
import com.ponscio.model.error.BussinesError;
import com.ponscio.model.error.CrediYaError;
import com.ponscio.model.valueobjects.Email;
import com.ponscio.model.valueobjects.IntegerV;
import com.ponscio.model.valueobjects.Letters;
import com.ponscio.model.valueobjects.MontoV;
import com.ponscio.model.valueobjects.TipoCedula;
import com.ponscio.repository.EmpleadoDAO;
import com.ponscio.util.Scan;
import com.ponscio.view.interfaz.IMenu;

import java.util.List;

public class MenuEmpleado implements IMenu {

    private Scan scan;
    private EmpleadoDAO empleadoDAO;
    private MenuEmpleadoF empleadoF;

    public MenuEmpleado() {
        this.scan = new Scan();
        this.empleadoDAO = new EmpleadoDAO();
        this.empleadoF = new MenuEmpleadoF(empleadoDAO);
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
            case 1 -> registrar();
            case 2 -> consultar();
            case 0 -> System.out.println("Regresando...");
            default -> System.out.println("Opcion invalida");
        }
    }

    private void registrar() {
        try {
            String nombre = new Letters(scan.leerTexto("> Ingrese el nombre del empleado: ")).getValue();
            String tipoD = new TipoCedula(scan.leerInt("> Ingrese el tipo de documento del cliente: \n\n> 1) Cedula de Ciudadania\n> 2) Cedula Extranjera\n")).getValue();
            String documento = new IntegerV(scan.leerTexto("> Ingrese el numero de documento del empleado: ")).getValue();
            if (documento.length() > 10 || documento.length() < 8)
                throw new CrediYaError("El numero documento debe ser entre 8 y 10 digitos", BussinesError.VALOR_FUERA_DE_RANGO);

            System.out.println("\n> Roles disponibles: ");
            System.out.println(empleadoF.getRoles());
            int rol = Integer.parseInt(new IntegerV(scan.leerTexto("> Ingrese el Rol del empleado: ")).getValue());
            
            var correo = new Email(scan.leerTexto("> Ingrese el correo del empleado: ")).getValue();

            var salario = new MontoV(scan.leerTexto("> Ingrese el salario del empleado: ")).getValue();

            Empleado empleado = new Empleado(0, nombre, documento, tipoD, rol, correo, salario);
            empleadoF.registrar(empleado);

        } catch (CrediYaError e) {
            System.out.println(e.toString());
            System.out.println(e.getMessage());
        }
    }

    private void consultar() {
        System.out.println("\n====================================");
        System.out.println("         CONSULTAR POR: ");
        System.out.println("====================================\n");
        System.out.println("*\t1) Consultar por nombre");
        System.out.println("*\t2) Consultar por documento");
        System.out.println("*\t3) Consultar por ID");
        System.out.println("*\t0) Regresar");
        System.out.println("\n====================================");
        var option = scan.leerInt("> Ingrese una opcion: ");
        List<Empleado> empleados = null;

        try {
            switch (option) {
                case 1:
                    var nombre = new Letters(scan.leerTexto("> Ingrese el nombre a consultar: ").trim()).getValue();
                    empleados = empleadoF.consultarByNombre(nombre);
                    break;
                case 2:
                    var documento = new IntegerV(scan.leerTexto("> Ingrese el documento a consultar: ")).getValue();
                    empleados = empleadoF.consultarByDocumento(documento);
                    break;
                case 3:
                    var id = scan.leerInt("> Ingrese el ID a consultar: ");
                    empleados = empleadoF.consultarById(id);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("\nError: Opcion no valida.");
                    break;
            }

            if (empleados == null) throw new CrediYaError("No se logro encontrar nada...", BussinesError.ERROR_DB_OBTENER_OBJETO);

            System.out.println(empleadoF.mostrarResultados(empleados));
            System.out.println("\nResultados: " + empleados.size());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void mostrarMenu() {
        System.out.println("\n====================================");
        System.out.println("         MENU EMPLEADOS");
        System.out.println("====================================\n");
        System.out.println("*\t1) Registrar empleado");
        System.out.println("*\t2) Consultar empleado");
        System.out.println("*\t0) Regresar al menu principal");
        System.out.println("\n====================================");
    }

}
