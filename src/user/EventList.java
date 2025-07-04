package user;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Arrays;

public class EventList extends JPanel {

    public EventList(MainFrame frame) {
        setLayout(new BorderLayout(10, 10));

        JLabel label = new JLabel("Daftar Event/Seminar", JLabel.CENTER);
        label.setFont(new Font("Poppins", Font.BOLD, 18));

        // List Event
        DefaultListModel<String> listModel = new DefaultListModel<>();
        List<String> allEvents = Arrays.asList("Seminar AI", "Workshop UI/UX", "Seminar Cloud Computing");
        allEvents.forEach(listModel::addElement);

        JList<String> eventList = new JList<>(listModel);
        eventList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(eventList);

        // Tombol
        JButton btnLihat = new JButton("Lihat Event");
        JButton btnBack = new JButton("Kembali");

        // Aksi Lihat Event
        btnLihat.addActionListener(e -> {
            String selectedEvent = eventList.getSelectedValue();
            if (selectedEvent == null) {
                JOptionPane.showMessageDialog(frame, "Pilih event terlebih dahulu.");
                return;
            }

            // Panggil panel detail event baru
            frame.gantiPanel(new EventDetail(frame, selectedEvent));
        });

        // Aksi Back
        btnBack.addActionListener(e -> frame.gantiPanel(new DashboardUser(frame)));

        // Panel tombol
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnLihat);
        buttonPanel.add(btnBack);

        // Susun panel
        add(label, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
