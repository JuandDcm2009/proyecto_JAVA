package com.ponscio.view;

import com.ponscio.Facade.MenuEmpleadoF;
import com.ponscio.model.Empleado;
import com.ponscio.model.error.BussinesError;
import com.ponscio.model.error.CrediYaError;
import com.ponscio.model.valueobjects.Email;
import com.ponscio.model.valueobjects.Integer;
import com.ponscio.repository.EmpleadoDAO;
import com.ponscio.util.Scan;

import java.util.List;

public class MenuEmpleado {

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
        }
    }

    private void registrar() {
        try {
            var nombre = scan.leerTexto("> Ingrese el nombre del empleado: ");
            int option = scan.leerInt(
                    "> Ingrese el tipo de documento del cliente: \n\n> 1) Cedula de Ciudadania\n> 2) Cedula Extranjera\n");
            String tipoD = null;

            if (option == 1)
                tipoD = "CC";
            else if (option == 2)
                tipoD = "CE";

            var documento = new Integer(scan.leerTexto("> Ingrese el numero de documento del empleado: "));
            System.out.println("\n> Roles disponibles: \n");

            System.out.println(empleadoF.getRoles());
            var rol = scan.leerInt("> Ingrese el Rol del empleado: ");
            var correo = new Email(scan.leerTexto("> Ingrese el correo del empleado: "));
            var salario = scan.leerDouble("> Ingrese el salario del empleado: ");
            if (salario < 1) throw new CrediYaError("El monto del salario debe ser mayor a 0", BussinesError.FORMATO_INVALIDO_NUMERO);

            if (tipoD != null) {
                Empleado empleado = new Empleado(0, nombre, documento.getValue(), tipoD, rol, correo.getValue(), salario);
                empleadoF.registrar(empleado);
            }

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
        switch (option) {
            case 1:
                var nombre = scan.leerTexto("> Ingrese el nombre a consultar: ").trim();
                empleados = empleadoF.consultarByNombre(nombre);
                break;
            case 2:
                var documento = scan.leerTexto("> Ingrese el documento a consultar: ").trim();
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

        if (empleados == null) {
            System.out.println("Error: No se pudo encontrar nada..");
            return;
        }

        System.out.println(empleadoF.mostrarResultados(empleados));
        System.out.println("\nResultados: " + empleados.size());

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
