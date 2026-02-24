package test;

import dao.CajeroDAO;
import modelo.Cajero;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OperacionesEmpleadoTest {

    CajeroDAO cajeroDAO = new CajeroDAO();

    @Test
    public void testReponerCajeroLogico() {
        Cajero cajero = cajeroDAO.getCajero(1);
        assertNotNull(cajero, "No se encontró el cajero con ID 1");

        double saldoInicial = cajero.getCantidadDisponible();
        double montoReponer = 5000.00;

        boolean exito = cajeroDAO.reponer(1, montoReponer);
        assertTrue(exito, "No se pudo reponer el cajero");

        Cajero actualizado = cajeroDAO.getCajero(1);
        assertEquals(saldoInicial + montoReponer, actualizado.getCantidadDisponible(), 0.01);
    }
}