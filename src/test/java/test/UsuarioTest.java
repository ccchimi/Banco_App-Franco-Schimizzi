package test;

import modelo.Usuario;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioTest {

    @Test
    public void testConstructorCompleto() {
        Usuario u = new Usuario(1, "Franco", "franco@banco.com", "admin123", "empleado");

        assertEquals(1, u.getId());
        assertEquals("Franco", u.getNombre());
        assertEquals("franco@banco.com", u.getEmail());
        assertEquals("admin123", u.getPassword());
        assertEquals("empleado", u.getRol());
    }

    @Test
    public void testSettersYGetters() {
        Usuario u = new Usuario();
        u.setId(2);
        u.setNombre("Marcos");
        u.setEmail("marcos@banco.com");
        u.setPassword("1234");
        u.setRol("cliente");

        assertEquals(2, u.getId());
        assertEquals("Marcos", u.getNombre());
        assertEquals("marcos@banco.com", u.getEmail());
        assertEquals("1234", u.getPassword());
        assertEquals("cliente", u.getRol());
    }
}