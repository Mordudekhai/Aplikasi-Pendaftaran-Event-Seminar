package admin;

import javax.swing.*;
import java.awt.*;
import user.MainFrame;

public class DashboardAdmin extends JPanel {
    public DashboardAdmin(MainFrame frame) {
        setLayout(new GridLayout(5, 1, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel label = new JLabel("Dashboard Admin", JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 18));

        JButton btnKelolaEvent = new JButton("Kelola Event");
        JButton btnLihatPeserta = new JButton("Lihat Daftar Peserta");
        JButton btnLogout = new JButton("Logout");

        btnKelolaEvent.addActionListener(e -> frame.gantiPanel(new ManageEvent(frame)));
        btnLihatPeserta.addActionListener(e -> frame.gantiPanel(new ParticipantListAdmin(frame)));
        btnLogout.addActionListener(e -> frame.gantiPanel(new user.LoginForm(frame)));

        add(label);
        add(btnKelolaEvent);
        add(btnLihatPeserta);
        add(btnLogout);
    }
}
