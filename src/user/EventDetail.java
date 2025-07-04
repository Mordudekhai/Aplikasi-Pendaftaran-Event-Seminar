package user;

import javax.swing.*;
import java.awt.*;

public class EventDetail extends JPanel {

    public EventDetail(MainFrame frame, String eventName) {
        setLayout(new BorderLayout(10, 10));

        JLabel label = new JLabel("Detail Event: " + eventName, JLabel.CENTER);
        label.setFont(new Font("Poppins", Font.BOLD, 18));

        // Deskripsi event
        JTextArea areaDetail = new JTextArea();
        areaDetail.setEditable(false);
        areaDetail.setLineWrap(true);
        areaDetail.setWrapStyleWord(true);

        String detail = "";
        switch (eventName) {
            case "Seminar AI":
                detail = "Seminar AI membahas tren terbaru AI, machine learning, dan implementasinya di industri.\n" +
                         "Waktu: 20 Juli 2025\nLokasi: Ruang Hall A, Gedung Informatika\nBiaya: Rp 200.000";
                break;
            case "Workshop UI/UX":
                detail = "Workshop UI/UX membahas pembuatan desain antarmuka interaktif dan pengalaman pengguna.\n" +
                         "Waktu: 25 Juli 2025\nLokasi: Lab Multimedia\nBiaya: Rp 150.000";
                break;
            case "Seminar Cloud Computing":
                detail = "Seminar Cloud Computing membahas layanan cloud terkini untuk bisnis dan startup.\n" +
                         "Waktu: 30 Juli 2025\nLokasi: Auditorium Teknik\nBiaya: Rp 180.000";
                break;
        }
        areaDetail.setText(detail);

        JScrollPane detailScrollPane = new JScrollPane(areaDetail);

        // Tombol
        JButton btnDaftar = new JButton("Daftar Seminar");
        JButton btnBack = new JButton("Kembali");

        // Aksi Daftar
        btnDaftar.addActionListener(e -> {
            if (frame.daftarEventUser.contains(eventName)) {
                JOptionPane.showMessageDialog(frame, "Anda sudah mendaftar di event ini.");
            } else {
                frame.daftarEventUser.add(eventName);
                frame.statusPembayaranEvent.put(eventName, false);
                JOptionPane.showMessageDialog(frame, "Berhasil mendaftar ke " + eventName);
            }
        });

        // Aksi Back
        btnBack.addActionListener(e -> frame.gantiPanel(new EventList(frame)));

        // Panel tombol
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnDaftar);
        buttonPanel.add(btnBack);

        // Susun panel
        add(label, BorderLayout.NORTH);
        add(detailScrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
