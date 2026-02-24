package dao;

import conexion.Conexion;
import modelo.Transaccion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransaccionDAO {

    private Transaccion mapear(ResultSet rs) throws SQLException {
        Transaccion t = new Transaccion();
        t.setId(rs.getInt("id"));
        t.setIdCuenta(rs.getInt("id_cuenta"));
        t.setTipo(rs.getString("tipo"));
        t.setMonto(rs.getDouble("monto"));
        t.setFecha(rs.getTimestamp("fecha"));
        t.setDetalle(rs.getString("detalle"));
        return t;
    }

    public void registrarTransaccion(Transaccion t) {
        Connection conn = Conexion.getConnection();
        if (conn == null) {
            System.out.println("Conexión fallida.");
            return;
        }

        String sql = "INSERT INTO transacciones (id_cuenta, tipo, monto, detalle) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, t.getIdCuenta());
            stmt.setString(2, t.getTipo());
            stmt.setDouble(3, t.getMonto());
            stmt.setString(4, t.getDetalle());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al registrar transacción: " + e.getMessage());
        }
    }

    public List<Transaccion> getTransaccionesPorCuenta(int idCuenta) {
        List<Transaccion> lista = new ArrayList<>();
        Connection conn = Conexion.getConnection();
        if (conn == null) return lista;

        String sql = "SELECT * FROM transacciones WHERE id_cuenta = ? ORDER BY fecha DESC";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idCuenta);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener transacciones: " + e.getMessage());
        }
        return lista;
    }

    public List<Transaccion> getTodasLasTransacciones() {
        List<Transaccion> lista = new ArrayList<>();
        Connection conn = Conexion.getConnection();
        if (conn == null) return lista;

        String sql = "SELECT * FROM transacciones ORDER BY fecha DESC";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener transacciones: " + e.getMessage());
        }
        return lista;
    }
}