package com.ponscio.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.ponscio.configuration.ConnectionDB;
import com.ponscio.model.PrestamoDos;

public class PrestamoDosDAO {
    
    public int setPrestamoDos(PrestamoDos prestamoDos) {
        String sql = "INSERT INTO prestamosDos(cliente_id, monto, interes_mensual, plazo_meses, estado) VALUES(?, ?, ?, ?, ?)";
        try (Connection db = ConnectionDB.connect(); PreparedStatement stmt = db.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, prestamoDos.getCliente_id());
            stmt.setDouble(2, prestamoDos.getMonto());
            stmt.setDouble(3, prestamoDos.getInteresMensual());
            stmt.setInt(4, prestamoDos.getPlazoMeses());
            stmt.setString(5, prestamoDos.getEstado());
            stmt.executeUpdate();
            try (ResultSet result = stmt.getGeneratedKeys()) {
                if (result.next()) return result.getInt(1);
            } 
            return -1;

        } catch (Exception e) { 
            System.out.println(e.getMessage());
            return -1;
        }

    }


}
