package com.ponscio.service;
import com.ponscio.model.Cliente;
import com.ponscio.repository.ClienteDAO;

public class GestorClienteDB {
    private ClienteDAO clienteDAO;

    public GestorClienteDB() {
        this.clienteDAO = new ClienteDAO();
    }

    public Cliente getClienteById(int id_cliente) {
        return clienteDAO.getClienteById(id_cliente);
    }

    public Boolean validarClienteById(int id_cliente) {
        return clienteDAO.validarCliente(id_cliente);
    }

}
