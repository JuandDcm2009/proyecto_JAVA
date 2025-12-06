package com.ponscio.repository;

import com.ponscio.configuration.ConnectionDB;
import com.ponscio.model.Empleado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO {
    private static EmpleadoDAO instance;
    private List<String> roles;

    public static EmpleadoDAO CrediyaDatos() {
        if (instance == null) {
            instance = new EmpleadoDAO();
        }
        return instance;
    }

    public EmpleadoDAO() {
        this.roles = new ArrayList();
        cargarDatos();
    }

    private void cargarDatos() {
        var sql = "SELECT nombre FROM roles";
        try (Connection db = new ConnectionDB().connect(); PreparedStatement stmt = db.prepareStatement(sql)) {
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                this.roles.add(result.getString("nombre"));
            }

        } catch (Exception e) {
          System.out.println("\nError: No se pudo realizar la consulta sql");              
        }
    }
    
    private Empleado getEmpleado(int id) {
        System.out.println("Cargando datos...");
        var sql = "SELECT id, nombre, documento, rol, correo, salario FROM empleados" + 
        " WHERE id = ?";

        try (Connection db = new ConnectionDB().connect(); PreparedStatement stmt = db.prepareStatement(sql)) {
            
            stmt.setInt(1, id);

            try (ResultSet result = stmt.executeQuery()) {
                if (result.next()) {
                    var resultId = result.getInt("id");
                    var resultNombre = result.getString("nombre");
                    var resultDocumento = result.getString("documento");
                    var resultRol = result.getInt("rol");
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

    public void setEmpleado(Empleado empleado) {
        var sql = "INSERT INTO empleados(nombre, documento, rol_id, correo, salario) VALUES(?,?,?,?,?)";
        try (Connection db = new ConnectionDB().connect(); PreparedStatement stmt = db.prepareStatement(sql)) {
            stmt.setString(1, empleado.getNombre());
            stmt.setString(2, empleado.getDocumento());
            stmt.setInt(3, empleado.getRol());
            stmt.setString(4, empleado.getCorreo());
            stmt.setDouble(5, empleado.getSalario());
            stmt.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("\nError: No se pudo registrar el empleado.");
        }     
    }

    private Boolean validarEmpleado(int id) {
        System.out.println("Cargando datos...");
        var sql = "SELECT id FROM empleados WHERE id = ?";

        try (Connection db = new ConnectionDB().connect(); PreparedStatement stmt = db.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            try (ResultSet result = stmt.executeQuery()) {
                return result.next();
            }
        } catch (Exception e) {
            return false;
        }
    }

    private Boolean validarEmpleado(String documento) {
        System.out.println("Cargando datos...");
        var sql = "SELECT documento FROM empleados WHERE documento = ?";

        try (Connection db = new ConnectionDB().connect(); PreparedStatement stmt = db.prepareStatement(sql)) {
            
            stmt.setString(1, documento);
            try (ResultSet result = stmt.executeQuery()) {
                return result.next();
            }
        } catch (Exception e) {
            return false;
        }
    }

}
