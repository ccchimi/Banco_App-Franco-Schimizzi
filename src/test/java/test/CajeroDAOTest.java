package test;

import dao.CajeroDAO;
import modelo.Cajero;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CajeroDAOTest {

    CajeroDAO dao = new CajeroDAO();

    @Test
    public void testGetCajero() {
        Cajero cajero = dao.getCajero(1);
        assertNotNull(cajero);
        assertEquals(1, cajero.getId());
        assertTrue(cajero.getCantidadDisponible() >= 0);
    }

    @Test
    public void testReponer() {
        boolean resultado = dao.reponer(1, 5000.00);
        assertTrue(resultado);
    }
}