package ui;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import dao.TransaccionDAO;
import dao.UsuarioDAO;
import modelo.Cuenta;
import modelo.Transaccion;
import modelo.Usuario;
import servicios.OperacionesEmpleado;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MenuEmpleadoFrame extends JFrame {

    private final TransaccionDAO transaccionDAO = new TransaccionDAO();
    private final OperacionesEmpleado operacionesEmpleado = new OperacionesEmpleado();

    public MenuEmpleadoFrame(Usuario usuario) {
        FlatMacDarkLaf.setup();

        setTitle("BancoApp - Empleado");
        setSize(400, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        panel.setBackground(UIManager.getColor("Panel.background"));

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuarioActualizado = usuarioDAO.getUsuarioById(usuario.getId());
        String nombre = (usuarioActualizado != null) ? usuarioActualizado.getNombre() : usuario.getNombre();

        JLabel lblTitulo = new JLabel("Bienvenido, " + nombre, SwingConstants.CENTER);
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        panel.add(lblTitulo);

        JButton btnVerTransacciones = crearBoton("Ver transacciones del día");
        JButton btnReponerCajero = crearBoton("Reponer cajero");
        JButton btnVerEstadoCajero = crearBoton("Ver estado del cajero");
        JButton btnTestCuenta = crearBoton("Simular cuenta de test");
        JButton btnTestUsuario = crearBoton("Simular usuario de test");
        JButton btnTestCajero = crearBoton("Simular cajero");
        JButton btnRegistrarCliente = crearBoton("Registrar nuevo cliente");
        JButton btnSalir = crearBoton("Cerrar sesión");

        panel.add(btnVerTransacciones);
        panel.add(btnReponerCajero);
        panel.add(btnVerEstadoCajero);
        panel.add(btnTestCuenta);
        panel.add(btnTestUsuario);
        panel.add(btnTestCajero);
        panel.add(btnRegistrarCliente);
        panel.add(btnSalir);

        btnVerTransacciones.addActionListener(e -> mostrarTransacciones());
        btnReponerCajero.addActionListener(e -> operacionesEmpleado.reponerCajero());
        btnVerEstadoCajero.addActionListener(e -> operacionesEmpleado.verEstadoCajero());
        btnTestCuenta.addActionListener(e -> testCuentaConstructor());
        btnTestUsuario.addActionListener(e -> testUsuarioConstructor());
        btnTestCajero.addActionListener(e -> testCajeroConstructor());
        btnRegistrarCliente.addActionListener(e -> operacionesEmpleado.registrarCliente());
        btnSalir.addActionListener(e -> {
            dispose();
            new LoginFrame();
        });

        add(panel);
        setVisible(true);
    }

    private JButton crearBoton(String texto) {
        JButton button = new JButton(texto);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(250, 40));
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBackground(UIManager.getColor("Button.background"));
        return button;
    }

    private void mostrarTransacciones() {
        List<Transaccion> lista = transaccionDAO.getTodasLasTransacciones();
        StringBuilder sb = new StringBuilder();

        if (lista.isEmpty()) {
            sb.append("No hay transacciones registradas.");
        } else {
            for (Transaccion t : lista) {
                sb.append("ID ").append(t.getId())
                        .append(" | ").append(t.getFecha())
                        .append(" - Cuenta ").append(t.getIdCuenta())
                        .append(" - ").append(t.getTipo())
                        .append(" $").append(t.getMonto())
                        .append(" | ").append(t.getDetalle())
                        .append("\n");
            }
        }

        JTextArea area = new JTextArea(sb.toString());
        area.setEditable(false);
        JScrollPane scroll = new JScrollPane(area);

        JOptionPane.showMessageDialog(this, scroll, "Transacciones del Día", JOptionPane.INFORMATION_MESSAGE);
    }

    private void testCuentaConstructor() {
        Cuenta cuentaTest = new Cuenta(999, 5555, 100000.00);
        String mensaje = "Cuenta de test creada:\n"
                + "- ID Cuenta: " + cuentaTest.getId() + "\n"
                + "- ID Usuario: " + cuentaTest.getIdUsuario() + "\n"
                + "- Saldo: $" + cuentaTest.getSaldo();

        JOptionPane.showMessageDialog(this, mensaje, "Simulación de Cuenta", JOptionPane.INFORMATION_MESSAGE);
    }

    private void testUsuarioConstructor() {
        Usuario u = new Usuario(100, "TestUser", "test@correo.com", "1234", "cliente");

        String info = "Usuario simulado:\n"
                + "- ID: " + u.getId() + "\n"
                + "- Nombre: " + u.getNombre() + "\n"
                + "- Email: " + u.getEmail() + "\n"
                + "- Password: " + u.getPassword() + "\n"
                + "- Rol: " + u.getRol();

        JOptionPane.showMessageDialog(this, info, "Simulación de Usuario", JOptionPane.INFORMATION_MESSAGE);
    }

    private void testCajeroConstructor() {
        modelo.Cajero cajero = new modelo.Cajero(1, 250000.00);

        String info = "Cajero simulado:\n"
                + "- ID: " + cajero.getId() + "\n"
                + "- Disponible: $" + cajero.getCantidadDisponible();

        JOptionPane.showMessageDialog(this, info, "Simulación de Cajero", JOptionPane.INFORMATION_MESSAGE);
    }
}