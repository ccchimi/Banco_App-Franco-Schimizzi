package ui;

import dao.UsuarioDAO;
import modelo.Usuario;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private final JTextField emailField;
    private final JPasswordField passwordField;
    private final JLabel messageLabel;

    public LoginFrame() {
        setTitle("BancoApp - Login");
        setSize(400, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel contenedor con GridBagLayout (mas centrado)
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        formPanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        JLabel title = new JLabel("Iniciar Sesión", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        formPanel.add(title, gbc);

        // Email
        gbc.gridy++;
        gbc.gridwidth = 1;
        JLabel emailLabel = new JLabel("Email:");
        formPanel.add(emailLabel, gbc);

        gbc.gridx = 1;
        emailField = new JTextField();
        emailField.setPreferredSize(new Dimension(200, 30));
        formPanel.add(emailField, gbc);

        // Contraseña
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel passwordLabel = new JLabel("Contraseña:");
        formPanel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(200, 30));
        formPanel.add(passwordField, gbc);

        // Boton
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        JButton loginButton = new JButton("Iniciar sesión");
        loginButton.setPreferredSize(new Dimension(150, 40));
        formPanel.add(loginButton, gbc);

        // Mensaje de error
        gbc.gridy++;
        messageLabel = new JLabel("", SwingConstants.CENTER);
        messageLabel.setForeground(Color.RED);
        formPanel.add(messageLabel, gbc);

        loginButton.addActionListener(e -> iniciarSesion());

        add(formPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    private void iniciarSesion() {
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.login(email, password);

        if (usuario != null) {
            dispose();
            if ("cliente".equals(usuario.getRol())) {
                new MenuClienteFrame(usuario);
            } else {
                new MenuEmpleadoFrame(usuario);
            }
        } else {
            messageLabel.setText("Credenciales incorrectas.");
        }
    }
}