package dao;

import conexion.Conexion;
import modelo.Cuenta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CuentaDAO {

    private Cuenta mapearCuenta(ResultSet rs) throws SQLException {
        Cuenta c = new Cuenta();
        c.setId(rs.getInt("id"));
        c.setIdUsuario(rs.getInt("id_usuario"));
        c.setSaldo(rs.getDouble("saldo"));
        return c;
    }

    public Cuenta getCuentaByUsuario(int idUsuario) {
        String sql = "SELECT * FROM cuentas WHERE id_usuario = ?";
        Connection conn = Conexion.getConnection();

        if (conn == null) {
            System.out.println("Conexión nula en getCuentaByUsuario");
            return null;
        }

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapearCuenta(rs);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener cuenta: " + e.getMessage());
        }
        return null;
    }

    public Cuenta getCuentaById(int idCuenta) {
        String sql = "SELECT * FROM cuentas WHERE id = ?";
        Connection conn = Conexion.getConnection();

        if (conn == null) {
            System.out.println("Conexión nula en getCuentaById");
            return null;
        }

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idCuenta);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapearCuenta(rs);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener cuenta por ID: " + e.getMessage());
        }
        return null;
    }

    public boolean actualizarSaldo(int idCuenta, double nuevoSaldo) {
        String sql = "UPDATE cuentas SET saldo = ? WHERE id = ?";
        Connection conn = Conexion.getConnection();

        if (conn == null) {
            System.out.println("Conexión nula en actualizarSaldo");
            return false;
        }

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, nuevoSaldo);
            stmt.setInt(2, idCuenta);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar saldo: " + e.getMessage());
        }
        return false;
    }

    public double getSaldo(int idCuenta) {
        String sql = "SELECT saldo FROM cuentas WHERE id = ?";
        Connection conn = Conexion.getConnection();

        if (conn == null) {
            System.out.println("Conexión nula en getSaldo");
            return 0.0;
        }

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idCuenta);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getDouble("saldo");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener saldo: " + e.getMessage());
        }
        return 0.0;
    }
}
