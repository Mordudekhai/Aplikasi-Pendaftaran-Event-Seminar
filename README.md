# Aplikasi-Pendaftaran-Event-Seminar
Aplikasi Pendaftaran Event Seminar dibuat menggunakan NetBeans.

# 1. Struktur Proyek
Package user:
- Berisi kelas–kelas untuk alur pengguna (user):
	- app.java
	- MainFrame.java
	- LoginForm.java
	- DashboardUser.java
	- EventList.java
	- EventDetail.java
	- ParticipantList.java
	- Payment.java
Package admin:
- Berisi kelas–kelas untuk alur administrator (admin):
	- DashboardAdmin.java
	- ManageEvent.java
	- ParticipantListAdmin.java

# 2. Alur Aplikasi
Start & Login:
- Entry point ada di user.app, yang memanggil MainFrame dan langsung menampilkan panel LoginForm.
- LoginForm meminta username/password; validasi sederhana:
	- user / 1234 → masuk ke DashboardUser
	- admin / 1234 → masuk ke DashboardAdmin
	- selain itu: munculkan pesan kesalahan.

Panel Utama (MainFrame):
- Kelas MainFrame mewarisi JFrame dan memiliki metode gantiPanel(JPanel panel) untuk menukar konten layar.
- Juga menyimpan dua struktur data global:
	- List<String> daftarEventUser — daftar judul event yang sudah didaftarkan user.
	- Map<String, Boolean> statusPembayaranEvent — status bayar tiap judul event (true = sudah bayar).

Alur User:
- DashboardUser: menampilkan tombol “Lihat Daftar Event”, “Daftar Peserta Saya” dan “Logout”.
- EventList:
	- Daftar judul event (mengambil data statis dari ManageEvent.getDaftarEvent() di paket admin).
	- Tombol Lihat Detail → EventDetail.
- EventDetail:
	- Menampilkan judul, detail, lokasi, dan biaya.
	- Tombol Daftar → menambah judul ke daftarEventUser dan set status bayar jadi false, lalu ganti ke panel Payment.
- Payment:
	- Menampilkan ringkasan (judul & biaya), form input “Jumlah Bayar” dan tombol Bayar.
	- Jika jumlah sama atau lebih, set statusPembayaranEvent.put(judul, true) dan kembalikan ke DashboardUser.
- ParticipantList:
	- Menampilkan daftar event yang sudah didaftarkan user beserta kolom status (“Lunas” atau “Belum Lunas”).

Alur Admin:
- DashboardAdmin: tombol “Kelola Event”, “Daftar Peserta” dan “Logout”.
- ManageEvent:
	- Menyimpan list ArrayList<Event> (kelas Event berisi judul, detail, lokasi, biaya).
	- Menampilkan hanya judul event di JList<String> (sesuai permintaan agar daftar event hanya menampilkan judul).
	- Tombol Tambah → form input (judul, detail, lokasi, biaya) untuk menambah event.
	- Tombol Edit → form edit data Event terpilih.
	- Tombol Hapus → menghapus event terpilih.
	- Setelah operasi, list judul langsung di-refresh.
- ParticipantListAdmin:
	- Pilih event dari JList, lalu tampilkan list peserta (judul) yang mendaftar di daftarEventUser (yang tersimpan di MainFrame) dan status pembayarannya.

# 3. Penyimpanan Data
In-Memory: Semua data (daftar event, daftar pendaftar, status pembayaran) disimpan di memori (list dan map), sehingga tidak persisten—data akan hilang saat aplikasi ditutup.

# 4. Teknologi & UI
- Java Swing digunakan untuk seluruh antarmuka:
	- JFrame, JPanel, JLabel, JButton, JList, JScrollPane, JOptionPane, dll.
	- Layout manager: BorderLayout (di banyak panel), BoxLayout (di LoginForm), GridLayout (beberapa form).
- Navigasi antar “layanan” di-handle oleh MainFrame.gantiPanel(...), sehingga UI tetap dalam satu jendela utama.
