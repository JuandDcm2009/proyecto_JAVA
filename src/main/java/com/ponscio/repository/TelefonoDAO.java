package com.ponscio.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ponscio.configuration.ConnectionDB;
import com.ponscio.model.Telefono;

public class TelefonoDAO {


    
     public int setTelefono(Telefono telefono) {
        var sql = "INSERT INTO telefonos_clientes(numero, pais_id) VALUES(?,?)";
        try (Connection db = new ConnectionDB().connect(); PreparedStatement stmt = db.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, telefono.getNumero());
            stmt.setInt(2, telefono.getPais_id());

            int filas = stmt.executeUpdate();
            if (filas > 0) {
                try (ResultSet result = stmt.getGeneratedKeys()) {
                    if (result.next()) return result.getInt(1);
                }
            }
            
            return -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("\nError: Hubo un problema al intentar registrar el telefono");
            if (e.getErrorCode() == 1048) {
                System.out.println("\nError: Hubo un error al intentar insertar un campo NULO, Porfavor llenar toda la informacion");
            }
            return -1;
        }
    }

     public Telefono getTelefonoById(int id) {    
        var sql = "SELECT id, numero, pais_id FROM telefonos_clientes WHERE id = ?";
        try (Connection db = new ConnectionDB().connect(); PreparedStatement stmt = db.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet result = stmt.executeQuery()) {
                if (result.next()) {
                    return new Telefono(
                        result.getInt("id"),
                        result.getString("numero"),
                        result.getInt("pais_id")
                    );
                } else return null;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("\nError: Hubo un problema al intentar obtener el telefono");
            return null;
        }
    }

    
}
