package test;

import dao.CuentaDAO;
import dao.TransaccionDAO;
import modelo.Cuenta;
import modelo.Transaccion;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OperacionesClienteTest {

    CuentaDAO cuentaDAO = new CuentaDAO();
    TransaccionDAO transaccionDAO = new TransaccionDAO();

    @Test
    public void testDepositoLogico() {
        Cuenta cuenta = cuentaDAO.getCuentaByUsuario(1);
        assertNotNull(cuenta, "La cuenta con ID de usuario 1 no existe");

        double saldoInicial = cuenta.getSaldo();
        double monto = 500.00;

        boolean actualizado = cuentaDAO.actualizarSaldo(cuenta.getId(), saldoInicial + monto);
        assertTrue(actualizado);

        Transaccion t = new Transaccion(cuenta.getId(), "deposito", monto, "Depósito test lógico");
        transaccionDAO.registrarTransaccion(t);

        List<Transaccion> transacciones = transaccionDAO.getTransaccionesPorCuenta(cuenta.getId());
        boolean encontrada = transacciones.stream().anyMatch(tx -> "Depósito test lógico".equals(tx.getDetalle()));
        assertTrue(encontrada);

        Cuenta actualizada = cuentaDAO.getCuentaByUsuario(1);
        assertEquals(saldoInicial + monto, actualizada.getSaldo(), 0.01);
    }
}