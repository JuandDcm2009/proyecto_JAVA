package com.ponscio.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.ponscio.configuration.ConnectionDB;
import com.ponscio.model.Cliente;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public Cliente getClienteById(int id) {    
        var sql = "SELECT id, nombre, documento_numero, documento_tipo, correo, telefono_id FROM clientes WHERE id = ?";
        try (Connection db = new ConnectionDB().connect(); PreparedStatement stmt = db.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet result = stmt.executeQuery()) {
                if (result.next()) {
                    return new Cliente(
                        result.getInt("id"),
                        result.getString("nombre"),
                        result.getString("documento_numero"),
                        result.getString("documento_tipo"),
                        result.getString("correo"),
                        result.getInt("telefono_id")
                    );
                } else return null;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("\nError: Hubo un problema al intentar obtener el cliente");
            if (e.getErrorCode() == 1048) {
                System.out.println("\nError: Hubo un error al intentar insertar un campo NULO, Porfavor llenar toda la informacion");
            }
            return null;
        }
    }
    
    public List<Cliente> getClientes() {
        System.out.println("\nCargando informacion...");
        var sql = "SELECT id, nombre, documento_numero, documento_tipo, correo, telefono_id FROM clientes";
        try (Connection db = new ConnectionDB().connect(); PreparedStatement stmt = db.prepareStatement(sql)) {
            ResultSet result = stmt.executeQuery();
            List<Cliente> clientes = new ArrayList<>();
            while (result.next()) {
                Cliente cliente = new Cliente(
                        result.getInt("id"),
                        result.getString("nombre"),
                        result.getString("documento_numero"),
                        result.getString("documento_tipo"),
                        result.getString("correo"),
                        result.getInt("telefono_id")
                    );
                clientes.add(cliente);
            }
            if (clientes.size() == 0) {
                System.out.println("No se logro encontrar nada...");
                return clientes;
            }

            return clientes;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("\nError: Hubo un problema al intentar obtener los clientes\n Intentelo de nuevo mas tarde");
            return null;
        }
    }

    public Boolean setCliente(Cliente cliente) {
        var sql = "INSERT INTO clientes(nombre, documento_numero, documento_tipo, correo, telefono_id) VALUES(?,?,?,?,?)";
        try (Connection db = new ConnectionDB().connect(); PreparedStatement stmt = db.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getDocumentoNumero());            
            stmt.setString(3, cliente.getDocumentoTipo());
            stmt.setString(4, cliente.getCorreo());
            stmt.setInt(5, cliente.getTelefonoId());
            int filas = stmt.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("\nError: Hubo un problema al intentar registrar el cliente");
            if (e.getErrorCode() == 1048) {
                System.out.println("\nError: Hubo un error al intentar insertar un campo NULO, Porfavor llenar toda la informacion");
            }
            return false;
        }
    }

    public Boolean validarCliente(String documento, String tipo) {
        System.out.println("\nCargando Informacion....");
        var sql = "SELECT documento_numero, documento_tipo FROM clientes WHERE documento_numero = ? AND documento_tipo = ?";
        try (Connection db = new ConnectionDB().connect(); PreparedStatement stmt = db.prepareStatement(sql)) {
            stmt.setString(1, documento);
            stmt.setString(2, tipo);
            ResultSet result = stmt.executeQuery();
            if (!result.next()) {
                return false;
            }
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("\nError: Hubo un problema al intentar validar el cliente");
            return false;
        }
    }

    public Boolean validarCliente(int id) {
        System.out.println("\nCargando Informacion....");
        var sql = "SELECT id FROM clientes WHERE id = ?";
        try (Connection db = new ConnectionDB().connect(); PreparedStatement stmt = db.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();
            
            if (!result.next()) {
                return false;
            }
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("\nError: Hubo un problema al intentar validar el cliente");
            return false;
        }
    }

    public Boolean validarCliente(String correo) {
        System.out.println("\nCargando Informacion....");
        var sql = "SELECT correo FROM clientes WHERE correo = ?";
        try (Connection db = new ConnectionDB().connect(); PreparedStatement stmt = db.prepareStatement(sql)) {
            stmt.setString(1, correo);
            ResultSet result = stmt.executeQuery();
            
            if (!result.next()) {
                return false;
            }
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("\nError: Hubo un problema al intentar validar el correo");
            return false;
        }
    }

}
