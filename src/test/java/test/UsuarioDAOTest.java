package test;

import dao.UsuarioDAO;
import modelo.Usuario;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioDAOTest {

    UsuarioDAO dao = new UsuarioDAO();

    @Test
    public void testLoginExitosoCliente() {
        Usuario usuario = dao.login("cliente@banco.com", "1234");
        assertNotNull(usuario);
        assertEquals("cliente", usuario.getRol());
        assertEquals("cliente@banco.com", usuario.getEmail());
    }

    @Test
    public void testLoginExitosoEmpleado() {
        Usuario usuario = dao.login("empleado@banco.com", "admin");
        assertNotNull(usuario);
        assertEquals("empleado", usuario.getRol());
        assertEquals("empleado@banco.com", usuario.getEmail());
    }

    @Test
    public void testLoginFallido() {
        Usuario usuario = dao.login("inexistente@correo.com", "wrongpass");
        assertNull(usuario);
    }
}