package com.ponscio.Facade;

import com.ponscio.model.Pago;
import com.ponscio.model.Prestamo;
import com.ponscio.model.error.BussinesError;
import com.ponscio.model.error.CrediYaError;
import com.ponscio.repository.PagoDAO;
import com.ponscio.service.GestorPrestamoDB;
import com.ponscio.util.PrintAdvise;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MenuPagoF {
    
    private PagoDAO pagoDAO;
    private PrintAdvise printAdvise;
    private GestorPrestamoDB gPrestamoDB;

    public MenuPagoF(PagoDAO pagoDAO) {
        this.pagoDAO = pagoDAO;
        this.gPrestamoDB = new GestorPrestamoDB();
    }

    public void abonarPago(Pago pago) throws CrediYaError {
        if (!gPrestamoDB.validarPrestamoById(pago.getPrestamo_id())) throw new CrediYaError("No logro encontrar el ID del prestamo ingresado", BussinesError.VALOR_INEXISTENTE_NUMERO);
        Prestamo prestamo = gPrestamoDB.getPrestamosById(pago.getPrestamo_id()).get(0);
        if (prestamo == null) throw new CrediYaError("Hubo un error al acceder a la informacion del Prestamo", BussinesError.ERROR_FALLO_PROCESO);
        List<Pago> pagos = pagoDAO.getPagos(pago.getPrestamo_id()); 
        if (pagos.size() <= prestamo.getCuotas()) {
            double interesTotal = prestamo.getMonto() + (prestamo.getMonto() * (prestamo.getInteres() / 100.0));
            Pago pagoAPagar = new Pago(pago.getId(), 
            pago.getPrestamo_id(), 
            interesTotal / prestamo.getCuotas(), LocalDate.now());

            pagoDAO.setPago(pagoAPagar);
        }
    }    
}
