package user;

import javax.swing.*;
import java.awt.*;
import admin.DashboardAdmin;

public class LoginForm extends JPanel {

    public LoginForm(user.MainFrame frame) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel labelTitle = new JLabel("Aplikasi Pendaftaran Event Seminar", JLabel.CENTER);
        labelTitle.setFont(new Font("Arial", Font.BOLD, 16));
        labelTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(labelTitle);
        add(Box.createRigidArea(new Dimension(0, 20)));

        // Panel form dengan GridBagLayout
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // jarak antar komponen

        // Username
        JLabel labelUsername = new JLabel("Username:");
        JTextField textUsername = new JTextField(15);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(labelUsername, gbc);

        gbc.gridx = 1;
        formPanel.add(textUsername, gbc);

        // Password
        JLabel labelPassword = new JLabel("Password:");
        JPasswordField textPassword = new JPasswordField(15);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(labelPassword, gbc);

        gbc.gridx = 1;
        formPanel.add(textPassword, gbc);

        add(formPanel);
        add(Box.createRigidArea(new Dimension(0, 15)));

        // Tombol Panel
        JPanel buttonPanel = new JPanel();
        JButton buttonLogin = new JButton("Login");
        JButton buttonCancel = new JButton("Batal");
        buttonPanel.add(buttonLogin);
        buttonPanel.add(buttonCancel);
        add(buttonPanel);

        // Aksi Login
        buttonLogin.addActionListener(e -> {
            String username = textUsername.getText();
            String password = new String(textPassword.getPassword());

            if (username.equals("user") && password.equals("1234")) {
                JOptionPane.showMessageDialog(frame, "Login User Berhasil!");
                frame.gantiPanel(new user.DashboardUser(frame));
            } else if (username.equals("admin") && password.equals("1234")) {
                JOptionPane.showMessageDialog(frame, "Login Admin Berhasil!");
                frame.gantiPanel(new admin.DashboardAdmin(frame));
            } else {
                JOptionPane.showMessageDialog(frame, "Username atau Password salah.");
            }
        });

        buttonCancel.addActionListener(e -> System.exit(0));
    }
}
