package test;

import dao.CuentaDAO;
import modelo.Cuenta;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CuentaDAOTest {

    CuentaDAO dao = new CuentaDAO();

    @Test
    public void testGetCuentaByUsuario() {
        Cuenta cuenta = dao.getCuentaByUsuario(1);
        assertNotNull(cuenta);
        assertEquals(1, cuenta.getIdUsuario());
    }

    @Test
    public void testActualizarSaldo() {
        boolean actualizado = dao.actualizarSaldo(1, 9999.99);
        assertTrue(actualizado);
    }

    @Test
    public void testGetSaldo() {
        double saldo = dao.getSaldo(1);
        assertTrue(saldo >= 0);
    }
}