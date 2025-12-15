package com.ponscio.Facade;

import com.ponscio.repository.PrestamoDAO;
import com.ponscio.service.GestorClienteDB;
import com.ponscio.model.Cliente;
import com.ponscio.model.Prestamo;
import java.util.List;

public class MenuReporteF {

    private PrestamoDAO prestamoDAO;
    private GestorClienteDB gClienteDB;

    public MenuReporteF() {
        this.prestamoDAO = new PrestamoDAO();
        this.gClienteDB = new GestorClienteDB();
    }
    
    public String consultarPrestamosPagados() {
        String consulta = "";

        List<Prestamo> prestamos = prestamoDAO.getALLPrestamos();
        
        for (Prestamo prestamo : prestamos) {
            Cliente cliente = gClienteDB.getClienteById(prestamo.getCliente_id());
            consulta += prestamo.mostrarInfo(cliente);
        }

        return consulta;
    }

}
