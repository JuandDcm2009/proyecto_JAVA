package com.ponscio.repository;
import com.ponscio.configuration.ConnectionDB;
import com.ponscio.model.Pais;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaisDAO {
    
    public Map<String, Pais> getPaises() {
        System.out.println("\nCargando informacion");
        var sql = "SELECT id, nombre, codigo, continente_id FROM paises";
        try (Connection db = new ConnectionDB().connect(); PreparedStatement stmt = db.prepareStatement(sql)) {
            ResultSet result = stmt.executeQuery();
            Map<String, Pais> paises = new HashMap<>();
            while (result.next()) {
                Pais pais = new Pais(
                result.getInt("id"),
                result.getString("nombre"), 
                result.getString("codigo"), 
                result.getInt("continente_id"));
                paises.put(pais.getCodigo(), pais);
            }
            return paises;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("\nError: No se pudo obtener los paises");
            if (e.getErrorCode() == 1048) {
                System.out.println("\nError: Hubo un error al intentar insertar un campo NULO, Porfavor llenar toda la informacion");
            }
            return null;
        }
    }

    public Pais getPaisById(int id) {    
        var sql = "SELECT id, nombre, codigo, continente_id FROM paises WHERE id = ?";
        try (Connection db = new ConnectionDB().connect(); PreparedStatement stmt = db.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet result = stmt.executeQuery()) {
                
                if (result.next()) {
                    return new Pais(
                        result.getInt("id"),
                        result.getString("nombre"),
                        result.getString("codigo"),
                        result.getInt("continente_id")
                    );
                } else return null;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("\nError: Hubo un problema al intentar obtener el pais");
            return null;
        }
    }
    

}
