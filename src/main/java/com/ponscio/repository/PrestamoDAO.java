package com.ponscio.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.ponscio.configuration.ConnectionDB;
import com.ponscio.model.Prestamo;

public class PrestamoDAO {
    
    public Boolean setPrestamo(Prestamo prestamo) {
        String sql = "INSERT INTO prestamos(cliente_id, empleado_id, monto, interes, cuotas, fecha_inicio, estado) VALUES(?, ?, ?, ?, ?, ?, ?)";

        try (Connection db = new ConnectionDB().connect(); PreparedStatement stmt = db.prepareStatement(sql)) {
            stmt.setInt(1, prestamo.getCliente_id());
            stmt.setInt(2, prestamo.getEmpleado_id());
            stmt.setDouble(3, prestamo.getMonto());
            stmt.setDouble(4, prestamo.getInteres());
            stmt.setInt(5, prestamo.getCuotas());
            stmt.setDate(6, Date.valueOf(prestamo.getFecha_inicio()));
            stmt.setString(7, prestamo.getEstado());
            int filas  = stmt.executeUpdate();
            return filas > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public List<Prestamo> getPrestamos(int cliente_id) {
        String sql = "SELECT id, cliente_id, empleado_id, monto, interes, cuotas, fecha_inicio, estado FROM prestamos WHERE cliente_id = ?";
        try (Connection db = new ConnectionDB().connect(); PreparedStatement stmt = db.prepareStatement(sql)) {
            
            stmt.setInt(1, cliente_id);
            
            ResultSet result = stmt.executeQuery();
            List<Prestamo> prestamos = new ArrayList<>();

            while (result.next()) {
                prestamos.add(new Prestamo(
                    result.getInt("id"),
                    result.getInt("cliente_id"),
                    result.getInt("empleado_id"),
                    result.getDouble("monto"),
                    result.getDouble("interes"),
                    result.getInt("cuotas"),
                    result.getDate("fecha_inicio").toLocalDate(),
                    result.getString("estado")
                ));
            }
            return prestamos;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
