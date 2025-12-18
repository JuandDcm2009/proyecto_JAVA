package com.ponscio.repository;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.ponscio.configuration.ConnectionDB;
import com.ponscio.model.Prestamo;
import com.ponscio.util.PrintAdvise;

public class PrestamoDAO {
    
    public Boolean setPrestamo(Prestamo prestamo) {
        String sql = "INSERT INTO prestamos(cliente_id, empleado_id, monto, interes, cuotas, fecha_prestamo, estado) VALUES(?, ?, ?, ?, ?, ?, ?)";

        try (Connection db = new ConnectionDB().connect(); PreparedStatement stmt = db.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, prestamo.getCliente_id());
            stmt.setInt(2, prestamo.getEmpleado_id());
            stmt.setDouble(3, prestamo.getMonto());
            stmt.setDouble(4, prestamo.getInteres());
            stmt.setInt(5, prestamo.getCuotas());
            stmt.setDate(6, Date.valueOf(prestamo.getFecha_prestamo()));
            stmt.setString(7, prestamo.getEstado());
            int filas  = stmt.executeUpdate();

            if (filas > 0) {
                try (ResultSet result = stmt.getGeneratedKeys()) {
                    if (result.next()) new PrintAdvise("Prestamo registrado con el ID #" + result.getInt(1));
                }
            }            
            return filas > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public List<Prestamo> getPrestamos(String sql, Object param) {
        try (Connection db = new ConnectionDB().connect(); PreparedStatement stmt = db.prepareStatement(sql)) {
            if (param != null ) stmt.setObject(1, param);
            
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
                    result.getDate("fecha_prestamo").toLocalDate(),
                    result.getString("estado")
                ));
            }
            return prestamos;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Prestamo> getPrestamosByClienteId(int cliente_id) {
        return getPrestamos("SELECT id, cliente_id, empleado_id, monto, interes, cuotas, fecha_inicio, estado FROM prestamos WHERE cliente_id = ?", cliente_id);
    }

    public List<Prestamo> getPrestamosById(int id) {
        return getPrestamos("SELECT id, cliente_id, empleado_id, monto, interes, cuotas, fecha_inicio, estado FROM prestamos WHERE id = ?", id);
    }

    public List<Prestamo> getALLPrestamos() {
        return getPrestamos("SELECT id, cliente_id, empleado_id, monto, interes, cuotas, fecha_inicio, estado FROM prestamos", null);
    }


    public Boolean validarPrestamoById(int id) {
        String sql = "SELECT id, cliente_id, empleado_id, monto, interes, cuotas, fecha_inicio, estado FROM prestamos WHERE id = ?";
        try (Connection db = new ConnectionDB().connect(); PreparedStatement stmt = db.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.toString());
            return false;
        }
    }

    public void updateEstadoToPagado(int id_prestamo) {
        String sql = "UPDATE prestamos SET estado = 'VENCIDO' WHERE id = ?";
        try (Connection db = new ConnectionDB().connect(); PreparedStatement stmt = db.prepareStatement(sql)) {
            stmt.setInt(1, id_prestamo);
            int rows = stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());            
        }
    }

}
