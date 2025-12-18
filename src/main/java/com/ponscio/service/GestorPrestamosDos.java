package com.ponscio.service;
import java.util.ArrayList;
import java.util.List;

import com.ponscio.model.PrestamoDos;
import com.ponscio.model.error.BussinesError;
import com.ponscio.model.error.CrediYaError;
import com.ponscio.repository.PrestamoDosDAO;


public class GestorPrestamosDos {
    
    private List<PrestamoDos> prestamos;
    private PrestamoDosDAO prestamoDosDAO;

    public GestorPrestamosDos() {
        this.prestamos = new ArrayList<>();
        this.prestamoDosDAO = new PrestamoDosDAO();
    }

    public void registrarPrestamo(PrestamoDos prestamoDos) throws CrediYaError {
        if (prestamoDos.getMonto() < 1) 
            throw new CrediYaError("El monto del prestamo debe ser mayor a 0", BussinesError.VALOR_FUERA_DE_RANGO);
        if (prestamoDos.getPlazoMeses() < 1) 
            throw new CrediYaError("El plazo de meses del prestamo debe ser mayor a 0", BussinesError.VALOR_FUERA_DE_RANGO);
        int id = prestamoDosDAO.setPrestamoDos(prestamoDos);

        prestamoDos.setId(id);
        prestamos.add(prestamoDos);
    }

    public String listarPrestamos() {
        StringBuilder lista = new StringBuilder();
        for (PrestamoDos prestamoDos : prestamos) {
            lista.append(prestamoDos.toString());
        }
        if (lista.toString().length() == 0) return "No existen prestamos registrados aun";
        return lista.toString();
    }

    public String buscarPrestamoPorEstado(String estado) throws CrediYaError {
        StringBuilder lista = new StringBuilder();

        if (!(estado.equals("pagado"))|| !(estado.equals("activo")) || !(estado.equals("mora")))
            throw new CrediYaError("El estado ingresado no existe", BussinesError.VALOR_INVALIDO_STRING);

        List<PrestamoDos> prestamosFiltrados = prestamos.stream()
        .filter(e -> e.getEstado().equals(estado)).toList();

        for (PrestamoDos prestamoDos : prestamosFiltrados) {
            lista.append(prestamoDos.toString());
        }


        if (lista.toString().length() == 0) return "No se encontraron prestamos con el estado " + estado;
        return lista.toString();
    } 
    
}
