package user;

import javax.swing.*;
import java.util.*;

public class MainFrame extends JFrame {
    public List<String> daftarEventUser = new ArrayList<>();
    public Map<String, Boolean> statusPembayaranEvent = new HashMap<>();

    public MainFrame() {
        setTitle("Aplikasi Pendaftaran Event Seminar");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        gantiPanel(new LoginForm(this));
    }

    public void gantiPanel(JPanel panel) {
        setContentPane(panel);
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
