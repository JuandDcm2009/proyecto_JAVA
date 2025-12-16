package com.ponscio.service;

import com.ponscio.model.Telefono;
import com.ponscio.repository.TelefonoDAO;

public class GestorTelefonoDB {
    private TelefonoDAO telefonoDAO;

    public GestorTelefonoDB() {
        this.telefonoDAO = new TelefonoDAO();
    }

    public Telefono getTelefonoById(int id_telefono) {
        return telefonoDAO.getTelefonoById(id_telefono);
    }
}

