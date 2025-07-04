package user;

import javax.swing.*;
import java.awt.*;

public class ParticipantList extends JPanel {
    public ParticipantList(MainFrame frame) {
        setLayout(new BorderLayout(10, 10));

        JLabel label = new JLabel("Daftar Peserta Terdaftar", JLabel.CENTER);
        label.setFont(new Font("Poppins", Font.BOLD, 18));

        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (String event : frame.daftarEventUser) {
            listModel.addElement(event);
        }

        JList<String> listEvent = new JList<>(listModel);
        listEvent.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JTextArea areaPeserta = new JTextArea();
        areaPeserta.setEditable(false);

        listEvent.addListSelectionListener(e -> {
            String selectedEvent = listEvent.getSelectedValue();
            if (selectedEvent != null && frame.statusPembayaranEvent.getOrDefault(selectedEvent, false)) {
                areaPeserta.setText("1. Anda (terdaftar)\n2. Budi\n3. Siti\n4. Maya");
            } else {
                areaPeserta.setText("Belum ada peserta terdaftar atau belum melakukan pembayaran.");
            }
        });

        JButton btnBack = new JButton("Kembali");
        btnBack.addActionListener(e -> frame.gantiPanel(new DashboardUser(frame)));

        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.add(new JScrollPane(listEvent), BorderLayout.NORTH);
        listPanel.add(new JScrollPane(areaPeserta), BorderLayout.CENTER);

        add(label, BorderLayout.NORTH);
        add(listPanel, BorderLayout.CENTER);
        add(btnBack, BorderLayout.SOUTH);
    }
}
