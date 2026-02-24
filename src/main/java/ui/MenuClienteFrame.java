package ui;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import dao.CuentaDAO;
import dao.TransaccionDAO;
import dao.UsuarioDAO;
import modelo.Cuenta;
import modelo.Transaccion;
import modelo.Usuario;
import servicios.OperacionesCliente;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MenuClienteFrame extends JFrame {

    private final Cuenta cuenta;
    private final CuentaDAO cuentaDAO = new CuentaDAO();
    private final TransaccionDAO transaccionDAO = new TransaccionDAO();
    private final OperacionesCliente operaciones = new OperacionesCliente();

    public MenuClienteFrame(Usuario usuario) {
        FlatMacDarkLaf.setup();

        setTitle("BancoApp - Cliente");
        setSize(400, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cuenta = cuentaDAO.getCuentaByUsuario(usuario.getId());

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuarioActualizado = usuarioDAO.getUsuarioById(usuario.getId());
        String nombre = (usuarioActualizado != null) ? usuarioActualizado.getNombre() : usuario.getNombre();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        panel.setBackground(UIManager.getColor("Panel.background"));

        JLabel lblTitulo = new JLabel("Bienvenido, " + nombre, SwingConstants.CENTER);
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        panel.add(lblTitulo);

        JButton btnSaldo = crearBoton("Consultar saldo");
        JButton btnMovimientos = crearBoton("Ver movimientos");
        JButton btnDeposito = crearBoton("Realizar depósito");
        JButton btnRetiro = crearBoton("Realizar retiro");
        JButton btnTransferencia = crearBoton("Transferir dinero");
        JButton btnSalir = crearBoton("Cerrar sesión");

        panel.add(btnSaldo);
        panel.add(btnMovimientos);
        panel.add(btnDeposito);
        panel.add(btnRetiro);
        panel.add(btnTransferencia);
        panel.add(btnSalir);

        btnSaldo.addActionListener(e -> mostrarSaldo());
        btnMovimientos.addActionListener(e -> mostrarMovimientos());
        btnDeposito.addActionListener(e -> operaciones.depositar(cuenta));
        btnRetiro.addActionListener(e -> operaciones.retirar(cuenta));
        btnTransferencia.addActionListener(e -> operaciones.transferir(cuenta));
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

    private void mostrarSaldo() {
        double saldo = cuentaDAO.getSaldo(cuenta.getId());
        JOptionPane.showMessageDialog(this, "Tu saldo actual es: $" + saldo);
    }

    private void mostrarMovimientos() {
        List<Transaccion> lista = transaccionDAO.getTransaccionesPorCuenta(cuenta.getId());
        StringBuilder sb = new StringBuilder();

        if (lista.isEmpty()) {
            sb.append("No hay movimientos registrados.");
        } else {
            for (Transaccion t : lista) {
                sb.append(t.getFecha())
                        .append(" - ").append(t.getTipo())
                        .append(" $").append(t.getMonto())
                        .append(" | ").append(t.getDetalle())
                        .append("\n");
            }
        }

        JTextArea area = new JTextArea(sb.toString());
        area.setEditable(false);
        JScrollPane scroll = new JScrollPane(area);

        JOptionPane.showMessageDialog(this, scroll, "Movimientos", JOptionPane.INFORMATION_MESSAGE);
    }
}