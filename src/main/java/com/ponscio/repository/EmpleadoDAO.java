package com.ponscio.repository;

import com.ponscio.configuration.ConnectionDB;
import com.ponscio.model.Empleado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EmpleadoDAO {
    private static EmpleadoDAO instance;

    public static EmpleadoDAO CrediyaDatos() {
        if (instance == null) {
            instance = new EmpleadoDAO();
        }
        return instance;
    }
    
    private Empleado getEmpleadoByID(int id) {
        System.out.println("Cargando datos...");
        var sql = "SELECT id, nombre, documento, rol, correo, salario FROM empleados" + 
        " WHERE id = ?";
        
        try (Connection db = new ConnectionDB().connect(); PreparedStatement stmt = db.prepareStatement(sql)) {
            
            stmt.setInt(1, id);

            try (ResultSet result = stmt.executeQuery()) {
                if (result.next()) {
                    var resultId = result.getInt("id");
                    var resultNombre = result.getString("nombre");
                    var resultDocumento = result.getInt("documento");
                    var resultRol = result.getString("rol");
                    var resultCorreo = result.getString("correo");
                    var resultSalario = result.getDouble("salario");
                    return new Empleado(resultId, resultNombre, resultDocumento, resultRol, resultCorreo, resultSalario);
                } else {
                    System.out.println("\nError: No existe un empleado con ese ID");
                    return null;
                }
            }
            
        } catch (Exception e) {
            System.out.println("\nError: No se pudo realizar la consulta SQL");
            return null;
        }
    }
}
