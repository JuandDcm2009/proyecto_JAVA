package com.ponscio.Facade;

import com.ponscio.model.Cliente;
import com.ponscio.model.Pago;
import com.ponscio.model.Prestamo;
import com.ponscio.model.error.BussinesError;
import com.ponscio.model.error.CrediYaError;
import com.ponscio.repository.PagoDAO;
import com.ponscio.service.GestorClienteDB;
import com.ponscio.service.GestorPrestamoDB;
import com.ponscio.util.PrintAdvise;
import java.util.List;

public class MenuPagoF {
    
    private PagoDAO pagoDAO;
    private GestorClienteDB gClienteDB;
    private GestorPrestamoDB gPrestamoDB;

    public MenuPagoF(PagoDAO pagoDAO) {
        this.pagoDAO = pagoDAO;
        this.gPrestamoDB = new GestorPrestamoDB();
        this.gClienteDB = new GestorClienteDB();
    }

    public void pagarCuota(Pago pago) throws CrediYaError { 
        Prestamo prestamo = obtenerPrestamoById(pago.getPrestamo_id());
        List<Pago> pagos = pagoDAO.getPagos(pago.getPrestamo_id());

        if (pagos.size() < prestamo.getCuotas()) {
            double interesTotal = prestamo.getMonto() + (prestamo.getMonto() * (prestamo.getInteres() / 100.0));
            Pago pagoAPagar = new Pago(pago.getId(), 
            pago.getPrestamo_id(), 
            interesTotal / prestamo.getCuotas(),
            pago.getFecha_pago());
            pagoDAO.setPago(pagoAPagar);
            new PrintAdvise("Consignacion correctamente registrada");
            new PrintAdvise(pago.mostrarInfo(gClienteDB.getClienteById(prestamo.getCliente_id())));

            pagos = pagoDAO.getPagos(pago.getPrestamo_id());
            if (pagos.size() == prestamo.getCuotas()) {
                new PrintAdvise("\n* Felicidades, le Prestamo ha sido pagado en su totalidad! *\nGracias por usar con CrediYa.");
                return;
            }
            return;
        }
        gPrestamoDB.updateEstadoToPagado(pago.getPrestamo_id());
        new PrintAdvise("Al parecer el prestamo ya ha sido pagado en su totalidad");
    }    

    public String historialPagos(int cliente_id) throws CrediYaError {
        String historial = "";
        Cliente cliente = gClienteDB.getClienteById(cliente_id);
        if (cliente == null ) 
            throw new CrediYaError("Hubo un error al intentar obtener el cliente.", BussinesError.VALOR_INEXISTENTE_NUMERO);
        List<Pago> pagos = pagoDAO.getPagosByIdCliente(cliente_id);
        if (pagos.isEmpty()) return "El cliente no tiene pagos registrados";

        for (Pago pago : pagos) historial += pago.mostrarInfo(cliente);
        return historial;
    }   


    public Prestamo obtenerPrestamoById(int prestamo_id) throws CrediYaError {
        if (!gPrestamoDB.validarPrestamoById(prestamo_id)) 
            throw new CrediYaError("No logro encontrar el ID del prestamo ingresado", BussinesError.VALOR_INEXISTENTE_NUMERO);
        
        Prestamo prestamo = gPrestamoDB.getPrestamosById(prestamo_id).get(0);
        if (prestamo == null) 
            throw new CrediYaError("Hubo un error al acceder a la informacion del Prestamo", BussinesError.ERROR_FALLO_PROCESO);

        return prestamo;
    }
    
}
