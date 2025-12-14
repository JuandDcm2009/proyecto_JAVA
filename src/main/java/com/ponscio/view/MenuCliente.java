package com.ponscio.view;
import com.ponscio.Facade.MenuClienteF;
import com.ponscio.model.Cliente;
import com.ponscio.model.Telefono;
import com.ponscio.model.error.BussinesError;
import com.ponscio.model.error.CrediYaError;
import com.ponscio.model.valueobjects.Email;
import com.ponscio.model.valueobjects.IntegerV;
import com.ponscio.model.valueobjects.Letters;
import com.ponscio.repository.ClienteDAO;
import com.ponscio.repository.TelefonoDAO;
import com.ponscio.util.*;
import com.ponscio.view.interfaz.IMenu;
import com.ponscio.model.valueobjects.TelefonoV;
import com.ponscio.model.valueobjects.TipoCedula;

public class MenuCliente implements IMenu {

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

    public void leerOpcion(int opcion) {
        switch (opcion) {
            case 1 -> registrar();
            case 2 -> listar();
            case 3 -> mostrarPrestamos();
            case 0 -> System.out.println("Regresando...");
        }
    }

    private void registrar() {
        try {
            String nombre = new Letters(scan.leerTexto("> Ingrese el nombre del cliente: ")).getValue();
            String tipoD = new TipoCedula(scan.leerInt("> Ingrese el tipo de documento del cliente: \n\n> 1) Cedula de Ciudadania\n> 2) Cedula Extranjera\n")).getValue();
            String documento_numero = new IntegerV(scan.leerTexto("> Ingrese el documento del cliente")).getValue();
            String correo = new Email(scan.leerTexto("> Ingrese el correo del cliente: ")).getValue();
            String telefono = new TelefonoV(scan.leerTexto("> Ingrese el telefono del cliente")).getValue();
            String pais_id = scan.leerTexto("> Ingrese el codigo del pais\nFormato: +123");
            if (pais_id == null) throw new CrediYaError("El codigo pais no puee ser NULO", BussinesError.VALOR_INVALIDO_NULO);
            if (!clienteF.validarCodigoTelefono(pais_id)) throw new CrediYaError("Codigo de pais invalido.", BussinesError.VALOR_INEXISTENTE_NUMERO);
            int idTelefono = clienteF.registrarNumero(new Telefono(0, telefono, clienteF.getCodigoPais(pais_id)));
            
            if ((idTelefono == -1)) throw new CrediYaError("Hubo un error al registrar el numero\nIntentelo de nuevo mas tarde.", BussinesError.ERROR_FALLO_PROCESO);
            Cliente cliente = new Cliente(0, nombre, documento_numero, tipoD, correo, idTelefono);
            clienteF.registrarCliente(cliente);

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
    
    public void mostrarMenu() {
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
