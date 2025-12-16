package com.ponscio.repository;

import com.ponscio.configuration.ConnectionDB;
import com.ponscio.model.Empleado;

import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class EmpleadoDAO {
    private Map<Integer, String> roles;

    public EmpleadoDAO() {
        this.roles = new HashMap();
        cargarDatos();
    }

    public Map<Integer, String> getRoles() {
        return roles;
    }

    // Cache
    private void cargarDatos() {
        System.out.println("\nCargando informacion...");
        var sql = "SELECT id, nombre FROM roles";
        try (Connection db = new ConnectionDB().connect(); PreparedStatement stmt = db.prepareStatement(sql)) {
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                this.roles.put(result.getInt("id"), result.getString("nombre"));
            }
        } catch (Exception e) {
          System.out.println("\nError: No se pudo realizar la consulta sql");              
        }
    }

    // Obtener empleados
    private List<Empleado> getEmpleado(String sql, Object param, Boolean especificQuery) {
        System.out.println("Cargando informacion...");
        try (Connection db = new ConnectionDB().connect(); PreparedStatement stmt = db.prepareStatement(sql)) {
            if (param instanceof String && especificQuery) stmt.setObject(1, "%" + param + "%");
            else stmt.setObject(1, param);
            
            List<Empleado> elementos = new ArrayList<>();
            try (ResultSet result = stmt.executeQuery()) {
                while (result.next()) {
                    elementos.add(mapEmpleado(result));
                }
                if (elementos.size() == 0) {
                    return null;
                }
                return elementos;
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            System.out.println("\nError: No se logro encontrar nada...");
            return null;
        }
    }

    private Empleado mapEmpleado(ResultSet result) throws Exception {
        var resultId = result.getInt("id");
        var resultNombre = result.getString("nombre");
        var resultDocumentoNumero = result.getString("documento_numero");
        var resultDocumentoTipo = result.getString("documento_tipo");
        var resultRol = result.getInt("rol_id");
        var resultCorreo = result.getString("correo");
        var resultSalario = result.getDouble("salario");
        System.out.println("\nInformacion cargada correctmente!");
        return new Empleado(resultId, resultNombre, resultDocumentoNumero, resultDocumentoTipo, resultRol, resultCorreo, resultSalario);
    }

    public List<Empleado> getEmpleadoById(int id) {
        return getEmpleado("SELECT id, nombre, documento_numero, documento_tipo, rol_id, correo, salario FROM empleados WHERE id = ?", id, false);
    }

    public List<Empleado> getEmpleadoByNombre(String nombre) {
        return getEmpleado("SELECT id, nombre, documento_numero, documento_tipo, rol_id, correo, salario FROM empleados WHERE nombre LIKE ?", nombre, true);
    }

    public List<Empleado> getEmpleadoByDocumento(String documento) {
        return getEmpleado("SELECT id, nombre, documento_numero, documento_tipo, rol_id, correo, salario FROM empleados WHERE documento_numero = ?", documento, false);
    }
    
    public Boolean setEmpleado(Empleado empleado) {
        System.out.println("Cargando informacion...");
        var sql = "INSERT INTO empleados(nombre, documento_numero, documento_tipo, rol_id, correo, salario) VALUES(?,?,?,?,?,?)";
        try (Connection db = new ConnectionDB().connect(); PreparedStatement stmt = db.prepareStatement(sql)) {
            stmt.setString(1, empleado.getNombre());
            stmt.setString(2, empleado.getDocumentoNumero());
            stmt.setString(3, empleado.getDocumentoTipo());
            stmt.setInt(4, empleado.getRol());
            stmt.setString(5, empleado.getCorreo());
            stmt.setDouble(6, empleado.getSalario());
            int filas = stmt.executeUpdate();
            if (filas > 0 ) System.out.println("\nInformacion cargada correctamente!");
            return filas > 0;

        } catch (SQLException e) {
            System.out.println("Hubo un problema al intentar registrar el empleado.");
            if (e.getErrorCode() == 1264) {
                System.out.println("\nEl salario ingresado esta fuera de RANGO\n Maximo 10 DIGITOS en total");
                return false;
            }
            System.out.println(e.getMessage());
            if (e.getErrorCode() == 1048) System.out.println("\nError: Hubo un error al intentar insertar un campo NULO, Porfavor llenar toda la informacion");
            return false;
        }     
    }

    public Boolean validarEmpleado(int id) {
        System.out.println("Cargando informacion...");
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

    public Boolean validarEmpleado(String documento) {
        System.out.println("Cargando informacion...");
        var sql = "SELECT documento_numero FROM empleados WHERE documento = ?";

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
