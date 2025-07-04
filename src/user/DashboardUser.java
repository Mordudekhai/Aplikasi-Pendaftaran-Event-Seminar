package user;

import javax.swing.*;
import java.awt.*;

public class DashboardUser extends JPanel {
    public DashboardUser(MainFrame frame) {
        setLayout(new GridLayout(5, 1, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel titleLabel = new JLabel("Dashboard User", JLabel.CENTER);
        titleLabel.setFont(new Font("Poppins", Font.BOLD, 18));

        JButton btnLihatEvent = new JButton("Lihat Daftar Event/Seminar");
        JButton btnBayar = new JButton("Bayar Biaya Pendaftaran");
        JButton btnLihatPeserta = new JButton("Lihat Daftar Peserta");
        JButton btnLogout = new JButton("Logout");

        btnLihatEvent.addActionListener(e -> frame.gantiPanel(new EventList(frame)));
        btnBayar.addActionListener(e -> frame.gantiPanel(new Payment(frame)));
        btnLihatPeserta.addActionListener(e -> frame.gantiPanel(new ParticipantList(frame)));
        btnLogout.addActionListener(e -> frame.gantiPanel(new LoginForm(frame)));

        add(titleLabel);
        add(btnLihatEvent);
        add(btnBayar);
        add(btnLihatPeserta);
        add(btnLogout);
    }
}
