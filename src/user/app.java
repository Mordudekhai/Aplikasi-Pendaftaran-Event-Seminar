package user;

import javax.swing.*;

public class app {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.gantiPanel(new LoginForm(frame));
            frame.setVisible(true);
        });
    }
}
