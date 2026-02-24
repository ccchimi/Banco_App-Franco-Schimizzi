package test;

import modelo.Transaccion;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TransaccionTest {

    @Test
    public void testConstructorCompleto() {
        Transaccion t = new Transaccion(3, "deposito", 1500.00, "Depósito de prueba");

        assertEquals(3, t.getIdCuenta());
        assertEquals("deposito", t.getTipo());
        assertEquals(1500.00, t.getMonto());
        assertEquals("Depósito de prueba", t.getDetalle());
    }

    @Test
    public void testSettersYGetters() {
        Transaccion t = new Transaccion();
        t.setId(99);
        t.setIdCuenta(1);
        t.setTipo("transferencia");
        t.setMonto(2500.00);
        t.setDetalle("Transferencia test");

        assertEquals(99, t.getId());
        assertEquals(1, t.getIdCuenta());
        assertEquals("transferencia", t.getTipo());
        assertEquals(2500.00, t.getMonto());
        assertEquals("Transferencia test", t.getDetalle());
    }
}