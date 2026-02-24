package test;

import modelo.Cuenta;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CuentaTest {

    @Test
    public void testConstructorCompleto() {
        Cuenta c = new Cuenta(10, 5, 7500.50);

        assertEquals(10, c.getId());
        assertEquals(5, c.getIdUsuario());
        assertEquals(7500.50, c.getSaldo());
    }

    @Test
    public void testSettersYGetters() {
        Cuenta c = new Cuenta();
        c.setId(20);
        c.setIdUsuario(6);
        c.setSaldo(10000.00);

        assertEquals(20, c.getId());
        assertEquals(6, c.getIdUsuario());
        assertEquals(10000.00, c.getSaldo());
    }
}