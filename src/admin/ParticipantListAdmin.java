package admin;

import javax.swing.*;
import java.awt.*;
import user.MainFrame;

public class ParticipantListAdmin extends JPanel {

    private JList<String> eventList;
    private DefaultListModel<String> listModel;
    private JButton btnLihat, btnKembali;

    public ParticipantListAdmin(MainFrame frame) {
        setLayout(new BorderLayout(10, 10));

        JLabel label = new JLabel("Daftar Peserta Event", JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 16));

        listModel = new DefaultListModel<>();
        for (ManageEvent.Event e : ManageEvent.getDaftarEvent()) {
            listModel.addElement(e.getJudul());
        }

        eventList = new JList<>(listModel);
        eventList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(eventList);

        // Tombol
        JPanel buttonPanel = new JPanel();
        btnLihat = new JButton("Lihat Peserta");
        btnKembali = new JButton("Kembali");

        btnLihat.setEnabled(false);

        buttonPanel.add(btnLihat);
        buttonPanel.add(btnKembali);

        // Listener list selection
        eventList.addListSelectionListener(e -> {
            boolean selected = !eventList.isSelectionEmpty();
            btnLihat.setEnabled(selected);
        });

        // Tombol kembali
        btnKembali.addActionListener(e -> frame.gantiPanel(new DashboardAdmin(frame)));

        // Tombol lihat peserta
        btnLihat.addActionListener(e -> {
            int index = eventList.getSelectedIndex();
            if (index >= 0) {
                ManageEvent.Event eventDipilih = ManageEvent.getDaftarEvent().get(index);
                frame.gantiPanel(new DaftarPesertaPanel(frame, eventDipilih));
            }
        });

        add(label, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Panel untuk menampilkan daftar peserta event
    class DaftarPesertaPanel extends JPanel {
        public DaftarPesertaPanel(MainFrame frame, ManageEvent.Event event) {
            setLayout(new BorderLayout(10, 10));

            JLabel label = new JLabel("Peserta: " + event.getJudul(), JLabel.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 16));

            DefaultListModel<String> pesertaModel = new DefaultListModel<>();

            // Data dummy peserta (bisa nanti ambil dari real list kalau sudah ada)
            pesertaModel.addElement("Budi Setiawan");
            pesertaModel.addElement("Rina Mulyani");
            pesertaModel.addElement("Ahmad Fahmi");

            JList<String> pesertaList = new JList<>(pesertaModel);
            JScrollPane scrollPane = new JScrollPane(pesertaList);

            JPanel buttonPanel = new JPanel();
            JButton btnKembali = new JButton("Kembali");

            btnKembali.addActionListener(e -> frame.gantiPanel(new ParticipantListAdmin(frame)));

            buttonPanel.add(btnKembali);

            add(label, BorderLayout.NORTH);
            add(scrollPane, BorderLayout.CENTER);
            add(buttonPanel, BorderLayout.SOUTH);
        }
    }
}
