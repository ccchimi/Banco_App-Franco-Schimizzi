package test;

import dao.TransaccionDAO;
import modelo.Transaccion;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TransaccionDAOTest {

    TransaccionDAO dao = new TransaccionDAO();

    @Test
    public void testRegistrarTransaccion() {
        Transaccion t = new Transaccion(1, "deposito", 1234.56, "Test unitario");
        dao.registrarTransaccion(t);

        List<Transaccion> lista = dao.getTransaccionesPorCuenta(1);
        boolean encontrada = lista.stream().anyMatch(tx -> "Test unitario".equals(tx.getDetalle()));
        assertTrue(encontrada);
    }

    @Test
    public void testGetTransaccionesPorCuenta() {
        List<Transaccion> lista = dao.getTransaccionesPorCuenta(1);
        assertNotNull(lista);
    }

    @Test
    public void testGetTodasLasTransacciones() {
        List<Transaccion> todas = dao.getTodasLasTransacciones();
        assertNotNull(todas);
    }
}