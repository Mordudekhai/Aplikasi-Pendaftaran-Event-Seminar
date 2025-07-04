package user;

import javax.swing.*;
import java.awt.*;

public class Payment extends JPanel {
    public Payment(MainFrame frame) {
        setLayout(new BorderLayout(10, 10));

        JLabel label = new JLabel("Pembayaran Pendaftaran", JLabel.CENTER);
        label.setFont(new Font("Poppins", Font.BOLD, 18));

        // List Event yang didaftarkan user
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (String event : frame.daftarEventUser) {
            listModel.addElement(event);
        }

        JList<String> eventList = new JList<>(listModel);
        eventList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JTextArea areaInfo = new JTextArea("Silakan pilih event di daftar atas,\nklik 'Bayar' untuk melanjutkan.");
        areaInfo.setEditable(false);

        // Tombol Bayar
        JButton btnBayar = new JButton("Bayar");
        btnBayar.addActionListener(e -> {
            String selectedEvent = eventList.getSelectedValue();
            if (selectedEvent == null) {
                JOptionPane.showMessageDialog(frame, "Pilih salah satu event terlebih dahulu.");
                return;
            }

            if (frame.statusPembayaranEvent.getOrDefault(selectedEvent, false)) {
                JOptionPane.showMessageDialog(frame, "Sudah membayar untuk event ini.");
            } else {
                // Dialog Konfirmasi Pembayaran
                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                panel.add(new JLabel("Pembayaran untuk: " + selectedEvent));
                panel.add(Box.createRigidArea(new Dimension(0, 10)));
                panel.add(new JLabel("Silakan transfer ke rekening:"));
                panel.add(new JLabel("BCA 12345678 a.n Panitia Seminar"));
                panel.add(Box.createRigidArea(new Dimension(0, 5)));
                panel.add(new JLabel("Kirim bukti ke WhatsApp: 08123456789"));
                panel.add(Box.createRigidArea(new Dimension(0, 10)));
                panel.add(new JLabel("Setelah transfer, Anda akan mendapatkan kode 6 digit via WA."));
                panel.add(new JLabel("Masukkan kode di bawah untuk konfirmasi pembayaran."));

                JTextField kodeField = new JTextField();
                kodeField.setMaximumSize(new Dimension(200, 25));
                panel.add(Box.createRigidArea(new Dimension(0, 10)));
                panel.add(new JLabel("Kode Konfirmasi (contoh: 123456):"));
                panel.add(kodeField);

                int result = JOptionPane.showConfirmDialog(frame, panel, "Konfirmasi Pembayaran",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

                if (result == JOptionPane.OK_OPTION) {
                    String kode = kodeField.getText().trim();
                    if (kode.equals("123456")) {
                        frame.statusPembayaranEvent.put(selectedEvent, true);
                        JOptionPane.showMessageDialog(frame, "Pembayaran berhasil untuk " + selectedEvent);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Kode salah. Silakan coba lagi.");
                    }
                }
            }
        });

        // Tombol Kembali
        JButton btnBack = new JButton("Kembali");
        btnBack.addActionListener(e -> frame.gantiPanel(new DashboardUser(frame)));

        // Panel Tombol
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnBayar);
        buttonPanel.add(btnBack);

        // Layout Komponen
        add(label, BorderLayout.NORTH);
        add(new JScrollPane(eventList), BorderLayout.CENTER);
        add(areaInfo, BorderLayout.SOUTH);
        add(buttonPanel, BorderLayout.PAGE_END);
    }
}
