package com.ponscio.view;

import com.ponscio.Facade.MenuClienteF;
import com.ponscio.model.Cliente;
import com.ponscio.model.Telefono;
import com.ponscio.model.error.BussinesError;
import com.ponscio.model.error.CrediYaError;
import com.ponscio.model.valueobjects.Email;
import com.ponscio.repository.ClienteDAO;
import com.ponscio.repository.TelefonoDAO;
import com.ponscio.util.*;
import com.ponscio.model.valueobjects.TelefonoV;

public class MenuCliente {

    private Scan scan;
    private MenuClienteF clienteF;
    private ClienteDAO clienteDAO;
    private TelefonoDAO telefonoDAO;

    public MenuCliente() {
        this.scan = new Scan();
        this.clienteDAO = new ClienteDAO();
        this.telefonoDAO = new TelefonoDAO();
        this.clienteF = new MenuClienteF(clienteDAO, telefonoDAO);
        
    }

    public void iniciar() {
        var option = -1;
        do {
            mostrarMenu();
            option = scan.leerInt("> Ingrese una opcion: ");
            leerOpcion(option);
        } while (option != 0);
    }

    private void leerOpcion(int opcion) {
        switch (opcion) {
            case 1 -> registrar();
            case 2 -> listar();
            case 3 -> mostrarPrestamos();
            case 0 -> System.out.println("Regresando...");
        }
    }

    private void registrar() {
        try {
            String nombre = scan.leerTexto("> Ingrese el nombre del cliente: ");
            if (nombre == null) throw new CrediYaError("El nombre no puede ser NULO", BussinesError.VALOR_INVALIDO_NULO);

            int option = scan.leerInt("> Ingrese el tipo de documento del cliente: \n\n> 1) Cedula de Ciudadania\n> 2) Cedula Extranjera\n");
            String tipoD = null;
            if (option == 1) tipoD = "CC";
            else if (option == 2) tipoD = "CE"; 
            else throw new CrediYaError("La opcion ingresada no es valida.", BussinesError.VALOR_INEXISTENTE_NUMERO);            
            if (tipoD == null) throw new CrediYaError("El tipo de documento no puede ser nulo; Ingrese una opcion valida", BussinesError.VALOR_INVALIDO_NULO);
            
            String documento_numero = scan.leerTexto("> Ingrese el documento del cliente");
            if (documento_numero == null) throw new CrediYaError("El documento ingresado no puede ser NULO", BussinesError.VALOR_INVALIDO_NULO);

            String correo = new Email(scan.leerTexto("> Ingrese el correo del cliente: ")).getValue();
            String telefono = new TelefonoV(scan.leerTexto("> Ingrese el telefono del cliente")).getValue();

            String pais_id = scan.leerTexto("> Ingrese el codigo del pais\nFormato: +123");
            if (pais_id == null) throw new CrediYaError("El codigo pais no puee ser NULO", BussinesError.VALOR_INVALIDO_NULO);
            if (!clienteF.validarCodigoTelefono(pais_id)) throw new CrediYaError("Codigo de pais invalido.", BussinesError.VALOR_INEXISTENTE_NUMERO);
            
            int idTelefono = clienteF.registrarNumero(new Telefono(0, telefono, clienteF.getCodigoPais(pais_id)));
            
            if ((idTelefono == -1)) throw new CrediYaError("Hubo un error al registrar el numero\nIntentelo de nuevo mas tarde.", BussinesError.ERROR_FALLO_PROCESO);
            Cliente cliente = new Cliente(0, nombre, documento_numero, tipoD, correo, idTelefono);
            System.out.println(clienteF.registrarCliente(cliente));   
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println(e.getMessage());
        }
        
    }


    public void listar() {
        System.out.println(clienteF.listarClientes());
    }

    public void mostrarPrestamos() {
        int id_cliente = scan.leerInt("> Ingrese ID del cliente: ");
        try {
            System.out.println(clienteF.mostrarPrestamo(id_cliente));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    private void mostrarMenu() {
        System.out.println("\n====================================");
        System.out.println("         MENU CLIENTES");
        System.out.println("====================================\n");
        System.out.println("*\t1) Registrar cliente");
        System.out.println("*\t2) Listar clientes");
        System.out.println("*\t3) Mostrar prestamos");
        System.out.println("*\t0) Regresar al menu principal");
        System.out.println("\n====================================");
    }
}
