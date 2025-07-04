package admin;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import user.MainFrame;

public class ManageEvent extends JPanel {

    private static ArrayList<Event> daftarEvent = new ArrayList<>();
    private static DefaultListModel<String> listModel = new DefaultListModel<>();
    private JList<String> eventList;
    private JButton btnEdit, btnHapus;

    public ManageEvent(MainFrame frame) {
        setLayout(new BorderLayout(10, 10));

        JLabel label = new JLabel("Kelola Event Seminar", JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 16));

        // Inisialisasi data dummy hanya sekali
        if (daftarEvent.isEmpty()) {
            daftarEvent.add(new Event("Seminar AI", "Seminar AI membahas tren terbaru AI, machine learning, dan implementasinya di industri.", "Ruang Hall A", 200000));
            daftarEvent.add(new Event("Workshop UI/UX", "Pelatihan UI/UX design modern dengan tools terbaru.", "Ruang Lab 1", 150000));
            daftarEvent.add(new Event("Seminar Cloud Computing", "Bahas cloud platform dan arsitektur cloud di perusahaan.", "Aula Lantai 3", 180000));

            for (Event e : daftarEvent) {
                listModel.addElement(e.getJudul());
            }
        }

        eventList = new JList<>(listModel);
        eventList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(eventList);

        // Tombol
        JPanel buttonPanel = new JPanel();
        JButton btnTambah = new JButton("Tambah");
        btnEdit = new JButton("Edit");
        btnHapus = new JButton("Hapus");
        JButton btnKembali = new JButton("Kembali");

        btnEdit.setEnabled(false);
        btnHapus.setEnabled(false);

        buttonPanel.add(btnTambah);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnHapus);
        buttonPanel.add(btnKembali);

        // Aksi tombol
        btnKembali.addActionListener(e -> frame.gantiPanel(new DashboardAdmin(frame)));

        // Seleksi list aktifkan tombol
        eventList.addListSelectionListener(e -> {
            boolean selected = !eventList.isSelectionEmpty();
            btnEdit.setEnabled(selected);
            btnHapus.setEnabled(selected);
        });

        // Tambah Event
        btnTambah.addActionListener(e -> {
            frame.gantiPanel(new FormEvent(frame, null));
        });

        // Edit Event
        btnEdit.addActionListener(e -> {
            int index = eventList.getSelectedIndex();
            if (index >= 0) {
                frame.gantiPanel(new FormEvent(frame, daftarEvent.get(index)));
            }
        });

        // Hapus Event
        btnHapus.addActionListener(e -> {
            int index = eventList.getSelectedIndex();
            if (index >= 0) {
                int confirm = JOptionPane.showConfirmDialog(frame, "Yakin ingin menghapus event ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    daftarEvent.remove(index);
                    listModel.remove(index);
                }
            }
        });

        add(label, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Form Tambah & Edit Event (inner class biar terhubung)
    class FormEvent extends JPanel {
        public FormEvent(MainFrame frame, Event eventEdit) {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            JLabel labelTitle = new JLabel(eventEdit == null ? "Tambah Event" : "Edit Event", JLabel.CENTER);
            labelTitle.setFont(new Font("Arial", Font.BOLD, 16));
            labelTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
            add(labelTitle);
            add(Box.createRigidArea(new Dimension(0, 15)));

            // Judul
            JPanel judulPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel lblJudul = new JLabel("Judul:");
            JTextField textJudul = new JTextField();
            textJudul.setPreferredSize(new Dimension(200, 25));
            if (eventEdit != null) textJudul.setText(eventEdit.getJudul());
            judulPanel.add(lblJudul);
            judulPanel.add(textJudul);
            add(judulPanel);

            // Detail
            JPanel detailPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel lblDetail = new JLabel("Detail:");
            JTextField textDetail = new JTextField();
            textDetail.setPreferredSize(new Dimension(200, 25));
            if (eventEdit != null) textDetail.setText(eventEdit.getDetail());
            detailPanel.add(lblDetail);
            detailPanel.add(textDetail);
            add(detailPanel);

            // Lokasi
            JPanel lokasiPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel lblLokasi = new JLabel("Lokasi:");
            String[] ruangOptions = {"Ruang Hall A", "Ruang Lab 1", "Aula Lantai 3"};
            JComboBox<String> comboLokasi = new JComboBox<>(ruangOptions);
            if (eventEdit != null) comboLokasi.setSelectedItem(eventEdit.getLokasi());
            lokasiPanel.add(lblLokasi);
            lokasiPanel.add(comboLokasi);
            add(lokasiPanel);

            // Biaya
            JPanel biayaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel lblBiaya = new JLabel("Biaya:");
            JTextField textBiaya = new JTextField();
            textBiaya.setPreferredSize(new Dimension(200, 25));
            if (eventEdit != null) textBiaya.setText(String.valueOf(eventEdit.getBiaya()));
            biayaPanel.add(lblBiaya);
            biayaPanel.add(textBiaya);
            add(biayaPanel);

            add(Box.createRigidArea(new Dimension(0, 10)));

            // Tombol
            JPanel buttonPanel = new JPanel();
            JButton btnSimpan = new JButton("Simpan");
            JButton btnBatal = new JButton("Batal");
            buttonPanel.add(btnSimpan);
            buttonPanel.add(btnBatal);
            add(buttonPanel);

            // Aksi Simpan
            btnSimpan.addActionListener(e -> {
                String judul = textJudul.getText();
                String detail = textDetail.getText();
                String lokasi = (String) comboLokasi.getSelectedItem();
                int biaya;

                try {
                    biaya = Integer.parseInt(textBiaya.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Biaya harus berupa angka.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (eventEdit == null) {
                    daftarEvent.add(new Event(judul, detail, lokasi, biaya));
                    listModel.addElement(judul);
                } else {
                    int index = daftarEvent.indexOf(eventEdit);
                    eventEdit.setJudul(judul);
                    eventEdit.setDetail(detail);
                    eventEdit.setLokasi(lokasi);
                    eventEdit.setBiaya(biaya);
                    listModel.set(index, judul);
                }

                frame.gantiPanel(new ManageEvent(frame));
            });

            btnBatal.addActionListener(e -> frame.gantiPanel(new ManageEvent(frame)));
        }
    }

    // Event class
    static class Event {
        private String judul, detail, lokasi;
        private int biaya;

        public Event(String judul, String detail, String lokasi, int biaya) {
            this.judul = judul;
            this.detail = detail;
            this.lokasi = lokasi;
            this.biaya = biaya;
        }

        public String getJudul() { return judul; }
        public String getDetail() { return detail; }
        public String getLokasi() { return lokasi; }
        public int getBiaya() { return biaya; }

        public void setJudul(String judul) { this.judul = judul; }
        public void setDetail(String detail) { this.detail = detail; }
        public void setLokasi(String lokasi) { this.lokasi = lokasi; }
        public void setBiaya(int biaya) { this.biaya = biaya; }
    }
    
    public static ArrayList<Event> getDaftarEvent() {
    return daftarEvent;
}
    
}
