package com.ponscio.Facade;

import com.ponscio.repository.ClienteDAO;
import com.ponscio.repository.PaisDAO;
import com.ponscio.repository.PrestamoDAO;
import com.ponscio.repository.TelefonoDAO;
import com.ponscio.util.PrintAdvise;
import com.ponscio.model.Cliente;
import com.ponscio.model.Telefono;
import com.ponscio.model.error.BussinesError;
import com.ponscio.model.error.CrediYaError;
import com.ponscio.model.Pais;
import com.ponscio.model.Prestamo;

import java.util.Map;
import java.util.List;

public class MenuClienteF {

    private ClienteDAO clienteDAO;
    private TelefonoDAO telefonoDAO;
    private PaisDAO paisDAO;
    private Map<String, Pais> paises;
    private PrestamoDAO prestamoDAO;
    
    public MenuClienteF(ClienteDAO clienteDAO, TelefonoDAO telefonoDAO) {
        this.clienteDAO = clienteDAO;
        this.telefonoDAO = telefonoDAO;
        this.paisDAO = new PaisDAO();
        this.paises = paisDAO.getPaises();
        this.prestamoDAO = new PrestamoDAO();
    }

    public Boolean validarInteger(String numero) {
        return numero != null && numero.matches("\\d+");
    }

    public int registrarNumero(Telefono telefono) {
        int idTelefono = telefonoDAO.setTelefono(telefono);
        if (idTelefono > 0 && validarInteger(telefono.getNumero())) {
            return idTelefono;
        }
        return -1;
    }   

    public int getCodigoPais(String codigo) {
        for (Map.Entry<String, Pais> p: paises.entrySet()) {
            if (p.getKey().equals(codigo)) return p.getValue().getId();
        }

        return -1;
    }

    public Boolean validarCodigoTelefono(String codigo) {
        for (String c : paises.keySet()) {
            if (c.equals(codigo)) {
                return true;
            }
        }
        return false;
    }


    public String registrarCliente(Cliente cliente) throws CrediYaError {
        if (clienteDAO.validarCliente(cliente.getDocumentoNumero(), cliente.getDocumentoTipo())) throw new CrediYaError(" Ya fue ingresado un cliente con ese documento.", BussinesError.VALOR_REPETIDO_NUMERO);
        clienteDAO.setCliente(cliente);
        if (clienteDAO.validarCliente(cliente.getCorreo())) throw new CrediYaError("El correo ingresado ya esta siendo usado por otro cliente", BussinesError.VALOR_REPETIDO_STRING);
        throw new CrediYaError("Hubo un problema al intentar registrar el Cliente\nIntentelo de nuevo mas tarde.", BussinesError.ERROR_FALLO_PROCESO);
    }

    public String listarClientes() {
        String lista = "";
        List<Cliente> clientes = clienteDAO.getClientes();
        for (Cliente c : clientes) {
            int pais_id = telefonoDAO.getTelefonoById(c.getTelefonoId()).getPais_id();
            String codigo = paisDAO.getPaisById(pais_id).getCodigo();
            
            lista += c.mostrarInfo(telefonoDAO.getTelefonoById(c.getTelefonoId()), codigo);
        }
        return lista;
    }

    public String mostrarPrestamo(int id_cliente) throws Exception{
        
        if (!clienteDAO.validarCliente(id_cliente)) {
            throw new CrediYaError("El ID del cliente ingresado no coincicde con ninguno en la base de datos", BussinesError.VALOR_INEXISTENTE_NUMERO);
        }

        List<Prestamo> prestamos = prestamoDAO.getPrestamo(id_cliente);
        if (prestamos == null) {
            throw new CrediYaError("No se pudo obtener los prestamos de ese cliente\nVuelva a intentarlo mas tarde", BussinesError.ERROR_DB_OBTENER_OBJETO);
        }

        Cliente cliente = clienteDAO.getClienteById(id_cliente);
        if (cliente == null) {
            throw new CrediYaError("Hubo un problema al intentar obtener el cliente", BussinesError.ERROR_DB_OBTENER_OBJETO);
        }

        String prestamosInfo = "=============== PRESTAMOS DE " + cliente.getNombre() + " ===============\n";
        for (Prestamo prestamo : prestamos) {
            prestamosInfo = prestamo.mostrarInfo(cliente);
        }
        new PrintAdvise("Resultados: " + prestamos.size());
        return prestamosInfo;
    }

}
