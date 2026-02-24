package test;

import modelo.Cajero;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CajeroTest {

    @Test
    public void testConstructorCompleto() {
        Cajero cajero = new Cajero(1, 50000.00);

        assertEquals(1, cajero.getId());
        assertEquals(50000.00, cajero.getCantidadDisponible());
    }

    @Test
    public void testSettersYGetters() {
        Cajero cajero = new Cajero();
        cajero.setId(2);
        cajero.setCantidadDisponible(100000.00);

        assertEquals(2, cajero.getId());
        assertEquals(100000.00, cajero.getCantidadDisponible());
    }
}