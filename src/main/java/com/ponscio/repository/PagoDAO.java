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
        String sql = "INSERT INTO prestamos(prestamo_id, monto, fecha_pago) VALUES(?, ?)";

        try (Connection db = new ConnectionDB().connect(); PreparedStatement stmt = db.prepareStatement(sql)) {
            stmt.setDouble(1, pago.getPrestamo_id());
            stmt.setDouble(2, pago.getMonto());
            stmt.setDate(3, Date.valueOf(pago.getFecha_pago()));
            int filas  = stmt.executeUpdate();
            return filas > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
