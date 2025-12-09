package com.ponscio.view;

import com.ponscio.Facade.MenuClienteF;
import com.ponscio.model.Cliente;
import com.ponscio.model.Telefono;
import com.ponscio.repository.ClienteDAO;
import com.ponscio.repository.TelefonoDAO;
import com.ponscio.util.*;

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
            case 0 -> System.out.println("Regresando...");
        }
    }

    private void registrar() {
        String nombre = scan.leerTexto("> Ingrese el nombre del cliente: ");
        int option = scan.leerInt("> Ingrese el tipo de documento del cliente: \n\n> 1) Cedula de Ciudadania\n> 2) Cedula Extranjera\n");
        String tipoD = null;

        if (option == 1) tipoD = "CC";
        else if (option == 2) tipoD = "CE"; 

        String documento_numero = scan.leerTexto("> Ingrese el documento del cliente");
        String correo = scan.leerTexto("> Ingrese el correo del cliente: ");

        String telefono = scan.leerTexto("> Ingrese el telefono del cliente");
        String pais_id = scan.leerTexto("> Ingrese el codigo del pais\nFormato: +123");

        if (!clienteF.validarCodigoTelefono(pais_id)) {
            System.out.println("\nError: Codigo de pais invalido.");
            return;
        }
        int idTelefono = clienteF.registrarNumero(new Telefono(0, telefono, clienteF.getCodigoPais(pais_id)));
        if (idTelefono > 0) {
            Cliente cliente = new Cliente(0, nombre, documento_numero, tipoD, correo, idTelefono);
            System.out.println(clienteF.registrarCliente(cliente));   
            return;
        } 
        System.out.println("\nError: No se pudo validar el telefono");
        System.out.println("Verifique el formato del telefono y que solo contenga valores de tipo numerico\nVuelva a intentarlo mas tarde..");
    }


    public void listar() {
        System.out.println(clienteF.listarClientes());
    }
    
    private void mostrarMenu() {
        System.out.println("\n====================================");
        System.out.println("         MENU CLIENTES");
        System.out.println("====================================\n");
        System.out.println("*\t1) Registrar cliente");
        System.out.println("*\t2) Listar clientes");
        System.out.println("*\t0) Regresar al menu principal");
        System.out.println("\n====================================");
    }
}
