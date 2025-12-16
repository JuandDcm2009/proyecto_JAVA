package com.ponscio.service;

import com.ponscio.model.Pais;
import com.ponscio.repository.PaisDAO;

public class GestorPaisDB {
    private PaisDAO paisDAO;

    public GestorPaisDB() {
        this.paisDAO = new PaisDAO();
    }

    public Pais getPaisById(int id_pais) {
        return paisDAO.getPaisById(id_pais);
    }
}
