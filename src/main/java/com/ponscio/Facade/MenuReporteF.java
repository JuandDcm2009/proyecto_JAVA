package com.ponscio.Facade;

import com.ponscio.repository.PrestamoDAO;
import com.ponscio.service.GestorClienteDB;
import com.ponscio.service.GestorPaisDB;
import com.ponscio.service.GestorTelefonoDB;
import com.ponscio.model.Cliente;
import com.ponscio.model.Prestamo;
import com.ponscio.model.Telefono;

import java.util.List;

public class MenuReporteF {

    private PrestamoDAO prestamoDAO;
    private GestorClienteDB gClienteDB;
    private GestorTelefonoDB gTelefonoDB;
    private GestorPaisDB gPaisDB;

    public MenuReporteF() {
        this.prestamoDAO = new PrestamoDAO();
        this.gClienteDB = new GestorClienteDB();
        this.gTelefonoDB = new GestorTelefonoDB();
        this.gPaisDB = new GestorPaisDB();
    }
    
    public String consultarPrestamosEstado(String estado) {
        String consulta = "";

        List<Prestamo> prestamos = prestamoDAO.getALLPrestamos().stream()
        .filter(p -> p.getEstado().equals(estado)).toList();
        
        for (Prestamo prestamo : prestamos) {
            Cliente cliente = gClienteDB.getClienteById(prestamo.getCliente_id());
            consulta += prestamo.mostrarInfo(cliente);
        }

        return consulta;
    }

    public String consultarClientesC(String tipoC) {
        String consulta = "";

        List<Cliente> clientes = gClienteDB.getClientes();
        if (clientes.size() < 1) return "No se logro encontrar nada...";

        clientes = clientes.stream()
        .filter(c -> c.getDocumentoTipo().equals(tipoC)).toList();


        for (Cliente cliente : clientes) {
            Telefono telefono = gTelefonoDB.getTelefonoById(cliente.getTelefonoId());
            String codigo_pais = gPaisDB.getPaisById(telefono.getPais_id()).getCodigo();
            consulta += cliente.mostrarInfo(telefono, codigo_pais);
        }

        return consulta;
    }

}
