package com.ponscio.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.ponscio.configuration.ConnectionDB;
import com.ponscio.model.Pago;
import com.ponscio.model.Prestamo;


public class PagoDAO {
      public Boolean setPago(Pago pago) {
        String sql = "INSERT INTO pagos(prestamo_id, monto, fecha_pago) VALUES(?, ?, ?)";

        try (Connection db = new ConnectionDB().connect(); PreparedStatement stmt = db.prepareStatement(sql)) {
            stmt.setInt(1, pago.getPrestamo_id());
            stmt.setDouble(2, pago.getMonto());
            stmt.setDate(3, Date.valueOf(pago.getFecha_pago()));
            int filas  = stmt.executeUpdate();
            return filas > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }


    public List<Pago> getPagos(int prestamo_id) {
        String sql = "SELECT id, prestamo_id, monto, fecha_pago FROM pagos WHERE prestamo_id = ?";
    
        try (Connection db = new ConnectionDB().connect();
             PreparedStatement stmt = db.prepareStatement(sql)) {
    
            stmt.setInt(1, prestamo_id);
    
            ResultSet result = stmt.executeQuery();
            List<Pago> pagos = new ArrayList<>();
    
            while (result.next()) {
                pagos.add(new Pago(
                        result.getInt("id"),
                        result.getInt("prestamo_id"),
                        result.getDouble("monto"),
                        result.getDate("fecha_pago").toLocalDate()
                ));
            }
    
            return pagos;
    
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Pago> getPagosByIdCliente(int id_cliente) {
        String sql = 
        "SELECT p.id, p.prestamo_id, p.monto, p.fecha_pago FROM pagos p JOIN prestamos pr ON p.prestamo_id = pr.id WHERE pr.cliente_id = ?";
        try (Connection db = new ConnectionDB().connect(); PreparedStatement stmt = db.prepareStatement(sql)) {
            stmt.setInt(1, id_cliente);
            ResultSet result  = stmt.executeQuery();
            List<Pago> pagos = new ArrayList<>();
            while (result.next()) {
                pagos.add(new Pago(
                    result.getInt("id"),
                    result.getInt("prestamo_id"), 
                    result.getDouble("monto"),
                    result.getDate("fecha_pago").toLocalDate()));
            } 
            return pagos;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
