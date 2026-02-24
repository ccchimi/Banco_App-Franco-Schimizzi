package dao;

import conexion.Conexion;
import modelo.Cajero;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CajeroDAO {

    public Cajero getCajero(int id) {
        String sql = "SELECT * FROM cajero WHERE id = ?";
        Connection conn = Conexion.getConnection();

        if (conn == null) return null;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Cajero(rs.getInt("id"), rs.getDouble("cantidad_disponible"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener cajero: " + e.getMessage());
        }

        return null;
    }

    // Reponer dinero al cajero
    public boolean reponer(int id, double monto) {
        String sql = "UPDATE cajero SET cantidad_disponible = cantidad_disponible + ? WHERE id = ?";
        Connection conn = Conexion.getConnection();

        if (conn == null) return false;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, monto);
            stmt.setInt(2, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al reponer cajero: " + e.getMessage());
        }

        return false;
    }
}