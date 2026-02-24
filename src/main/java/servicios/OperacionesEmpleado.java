package servicios;

import conexion.Conexion;
import dao.CajeroDAO;
import modelo.Cajero;
import utils.Seguridad;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OperacionesEmpleado {

    public void reponerCajero() {
        String input = JOptionPane.showInputDialog("Ingrese monto a reponer en el cajero:");
        if (input == null) return;

        try {
            double monto = Double.parseDouble(input);
            if (monto <= 0) {
                JOptionPane.showMessageDialog(null, "Monto inválido.");
                return;
            }

            CajeroDAO cajeroDAO = new CajeroDAO();
            boolean exito = cajeroDAO.reponer(1, monto);

            if (exito) {
                JOptionPane.showMessageDialog(null, "Cajero reabastecido correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo actualizar el cajero.");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Entrada inválida.");
        }
    }

    public void verEstadoCajero() {
        CajeroDAO cajeroDAO = new CajeroDAO();
        Cajero cajero = cajeroDAO.getCajero(1);

        if (cajero != null) {
            JOptionPane.showMessageDialog(null,
                    "Estado actual del cajero:\nID: " + cajero.getId() +
                            "\nDisponible: $" + cajero.getCantidadDisponible(),
                    "Estado del Cajero",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null,
                    "No se pudo obtener el estado del cajero.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void registrarCliente() {
        String nombre = JOptionPane.showInputDialog("Nombre del cliente:");
        if (nombre == null || nombre.trim().isEmpty()) return;

        String email = JOptionPane.showInputDialog("Email:");
        if (email == null || email.trim().isEmpty()) return;

        String password = JOptionPane.showInputDialog("Contraseña:");
        if (password == null || password.trim().isEmpty()) return;

        String hash = Seguridad.hashSHA256(password);

        String sqlUsuario = "INSERT INTO usuarios (nombre, email, password, rol) VALUES (?, ?, ?, 'cliente')";
        String sqlCuenta = "INSERT INTO cuentas (id_usuario, saldo) VALUES (?, 0.00)";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmtUser = conn.prepareStatement(sqlUsuario, Statement.RETURN_GENERATED_KEYS)) {

            stmtUser.setString(1, nombre);
            stmtUser.setString(2, email);
            stmtUser.setString(3, hash);
            int filas = stmtUser.executeUpdate();

            if (filas > 0) {
                ResultSet rs = stmtUser.getGeneratedKeys();
                if (rs.next()) {
                    int nuevoIdUsuario = rs.getInt(1);

                    try (PreparedStatement stmtCuenta = conn.prepareStatement(sqlCuenta)) {
                        stmtCuenta.setInt(1, nuevoIdUsuario);
                        stmtCuenta.executeUpdate();
                    }

                    JOptionPane.showMessageDialog(null, "Cliente registrado correctamente con ID " + nuevoIdUsuario);
                }
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo registrar el cliente.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar cliente: " + e.getMessage());
        }
    }
}