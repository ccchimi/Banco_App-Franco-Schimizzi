package servicios;

import dao.TransaccionDAO;
import dao.UsuarioDAO;
import modelo.Cuenta;
import modelo.Transaccion;
import dao.CuentaDAO;
import javax.swing.*;

public class OperacionesCliente {

    private final CuentaDAO cuentaDAO = new CuentaDAO();
    private final TransaccionDAO transaccionDAO = new TransaccionDAO();

    public void depositar(Cuenta cuenta) {
        String input = JOptionPane.showInputDialog("Ingrese monto a depositar:");
        if (input == null) return;

        try {
            double monto = Double.parseDouble(input);
            if (monto <= 0) {
                JOptionPane.showMessageDialog(null, "Monto inválido.");
                return;
            }

            double nuevoSaldo = cuenta.getSaldo() + monto;
            if (cuentaDAO.actualizarSaldo(cuenta.getId(), nuevoSaldo)) {
                cuenta.setSaldo(nuevoSaldo);
                Transaccion t = new Transaccion(cuenta.getId(), "deposito", monto, "Depósito en efectivo");
                transaccionDAO.registrarTransaccion(t);
                JOptionPane.showMessageDialog(null, "Depósito realizado correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "Error al actualizar saldo.");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Entrada no válida.");
        }
    }

    public void retirar(Cuenta cuenta) {
        String input = JOptionPane.showInputDialog("Ingrese monto a retirar:");
        if (input == null) return;

        try {
            double monto = Double.parseDouble(input);
            if (monto <= 0 || monto > cuenta.getSaldo()) {
                JOptionPane.showMessageDialog(null, "Fondos insuficientes o monto inválido.");
                return;
            }

            double nuevoSaldo = cuenta.getSaldo() - monto;
            if (cuentaDAO.actualizarSaldo(cuenta.getId(), nuevoSaldo)) {
                cuenta.setSaldo(nuevoSaldo);
                Transaccion t = new Transaccion(cuenta.getId(), "retiro", monto, "Retiro por cajero");
                transaccionDAO.registrarTransaccion(t);
                JOptionPane.showMessageDialog(null, "Retiro realizado con éxito.");
            } else {
                JOptionPane.showMessageDialog(null, "Error al actualizar saldo.");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Entrada no válida.");
        }
    }

    public void transferir(Cuenta cuentaOrigen) {
        String inputId = JOptionPane.showInputDialog("Ingrese ID de cuenta destino:");
        if (inputId == null) return;

        try {
            int idDestino = Integer.parseInt(inputId);

            if (idDestino == cuentaOrigen.getId()) {
                JOptionPane.showMessageDialog(null, "No puedes transferir a tu propia cuenta.");
                return;
            }

            Cuenta cuentaDestino = cuentaDAO.getCuentaById(idDestino);
            if (cuentaDestino == null) {
                JOptionPane.showMessageDialog(null, "Cuenta destino no encontrada.");
                return;
            }

            UsuarioDAO usuarioDAO = new UsuarioDAO();
            int idUsuarioDestino = cuentaDestino.getIdUsuario();
            modelo.Usuario destinatario = usuarioDAO.getUsuarioById(idUsuarioDestino);

            if (destinatario == null || !"cliente".equals(destinatario.getRol())) {
                JOptionPane.showMessageDialog(null, "Solo se permite transferir a cuentas de clientes.");
                return;
            }

            String inputMonto = JOptionPane.showInputDialog("Ingrese monto a transferir:");
            if (inputMonto == null) return;

            double monto = Double.parseDouble(inputMonto);
            if (monto <= 0 || monto > cuentaOrigen.getSaldo()) {
                JOptionPane.showMessageDialog(null, "Fondos insuficientes o monto inválido.");
                return;
            }

            double nuevoSaldoOrigen = cuentaOrigen.getSaldo() - monto;
            double nuevoSaldoDestino = cuentaDestino.getSaldo() + monto;

            boolean debito = cuentaDAO.actualizarSaldo(cuentaOrigen.getId(), nuevoSaldoOrigen);
            boolean credito = cuentaDAO.actualizarSaldo(cuentaDestino.getId(), nuevoSaldoDestino);

            if (debito && credito) {
                cuentaOrigen.setSaldo(nuevoSaldoOrigen);
                Transaccion tOrigen = new Transaccion(cuentaOrigen.getId(), "transferencia", monto, "Transferencia enviada a cuenta " + idDestino);
                Transaccion tDestino = new Transaccion(cuentaDestino.getId(), "transferencia", monto, "Transferencia recibida de cuenta " + cuentaOrigen.getId());
                transaccionDAO.registrarTransaccion(tOrigen);
                transaccionDAO.registrarTransaccion(tDestino);
                JOptionPane.showMessageDialog(null, "Transferencia realizada con éxito.");
            } else {
                JOptionPane.showMessageDialog(null, "Error al procesar la transferencia.");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Entrada no válida.");
        }
    }
}