package ui;

import dao.*;
import model.*;

import dbconnection.DBConnection;
import model.BookingServis;
import model.JenisServis;
import model.Teknisi;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class UserMenu {
    private static Scanner scanner = new Scanner(System.in);
    private static BookingServisDAO bookingDAO = new BookingServisDAO();
    private static MobilDAO mobilDAO = new MobilDAO();
    private static JenisServisDAO jenisServisDAO = new JenisServisDAO();
    private static TeknisiDAO teknisiDAO = new TeknisiDAO();
    

   public static void tampilkanMenu(UserSession session) {
    Integer pelangganId = session.getPelangganId(); // ✅ Pastikan ambil pelangganId

    int pilihan;

    do {
        System.out.println("\n=== MENU PELANGGAN ===");
        System.out.println("1. Cek Jadwal Booking Tanggal Tertentu"); // Cek jadwal 
        System.out.println("2. Tambah Mobil");                        // Daftarkan mobil pelanggan
        System.out.println("3. Booking Servis Baru");                 // Booking servis mobil
        System.out.println("4. Lihat Riwayat Booking Anda");          // Lihat hanya booking milik pelanggan ini
        System.out.println("0. Logout");
        System.out.print("Pilih: ");
        pilihan = Integer.parseInt(scanner.nextLine());

        switch (pilihan) {
            case 1:
                UserMenu menu = new UserMenu();
                menu.cekJadwalBookingHariIni(scanner); 
                break;
            case 2:
                tambahMobil(pelangganId);
                break;
            case 3:
                bookingServisBaru(pelangganId);
                break;
            case 4:
                lihatRiwayatBooking(pelangganId);
                break;
            case 0:
                System.out.println("Logout...");
                break;
            default:
                System.out.println("Pilihan tidak valid.");
        }
    } while (pilihan != 0);
}


    private void cekJadwalBookingHariIni(Scanner scanner) {
    System.out.print("Masukkan tanggal (yyyy-mm-dd) untuk melihat jadwal booking: ");
    String inputTanggal = scanner.nextLine();
    LocalDate tanggal = LocalDate.parse(inputTanggal);

    BookingServisDAO bookingDAO = new BookingServisDAO();
    List<BookingServis> daftarBooking = bookingDAO.getBookingByTanggal(tanggal);

    if (daftarBooking.isEmpty()) {
        System.out.println("Belum ada booking pada tanggal tersebut.");
    } else {
        System.out.println("Daftar Booking pada " + tanggal + ":");
        for (BookingServis b : daftarBooking) {
            System.out.println("ID: " + b.getIdBooking() +
                               " | Jam: " + b.getJam() +
                               " | Servis: " + b.getNamaServis() +
                               " | Teknisi: " + b.getNamaTeknisi() +
                               " | Status: " + b.getStatus());
        }
    }
}


   private static void tambahMobil(int pelangganId) {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Masukkan merk mobil: ");
    String merk = scanner.nextLine();
    
    System.out.print("Masukkan model mobil: ");
    String model = scanner.nextLine();
    
    System.out.print("Masukkan plat nomor: ");
    String platNomor = scanner.nextLine();
    
    System.out.print("Masukkan tahun mobil: ");
    int tahun = Integer.parseInt(scanner.nextLine());

    try (Connection conn = DBConnection.getConnection()) {
        String sql = "INSERT INTO mobil (pelanggan_id, merek, model, plat_nomor, tahun) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, pelangganId);
        stmt.setString(2, merk);
        stmt.setString(3, model);
        stmt.setString(4, platNomor);
        stmt.setInt(5, tahun);

        stmt.executeUpdate();
        System.out.println("Mobil berhasil ditambahkan.");
    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("Gagal menambahkan mobil.");
    }
}


    
   private static void bookingServisBaru(int pelangganId) {
       
    try {
        MobilDAO mobilDAO = new MobilDAO();
        JenisServisDAO jenisServisDAO = new JenisServisDAO();
        TeknisiDAO teknisiDAO = new TeknisiDAO();
        BookingServisDAO bookingDAO = new BookingServisDAO();

        System.out.println("\nPilih Mobil Anda:");
        List<Mobil> mobilList = mobilDAO.getMobilByPelangganId(pelangganId);
        if (mobilList.isEmpty()) {
            System.out.println("Anda belum memiliki mobil terdaftar.");
            return;
        }

        for (int i = 0; i < mobilList.size(); i++) {
            Mobil m = mobilList.get(i);
            System.out.printf("%d. %s - %s\n", i + 1, m.getMerk(), m.getPlatNomor());
        }

        System.out.print("Pilih mobil (nomor): ");
        int mobilIndex = Integer.parseInt(scanner.nextLine()) - 1;
        if (mobilIndex < 0 || mobilIndex >= mobilList.size()) {
            System.out.println("Pilihan mobil tidak valid.");
            return;
        }
        Mobil mobilTerpilih = mobilList.get(mobilIndex);

        System.out.println("\nPilih Jenis Servis:");
        List<JenisServis> jenisServisList = jenisServisDAO.getAll();
        for (int i = 0; i < jenisServisList.size(); i++) {
            JenisServis js = jenisServisList.get(i);
            System.out.printf(
            "%d. %s (Estimasi: %d menit, Biaya: Rp%.0f)\n",
            i + 1,
            js.getNamaServis(),
            js.getEstimasiWaktu(),
            js.getBiaya()
        );

        }

        System.out.print("Pilih jenis servis (nomor): ");
        int servisIndex = Integer.parseInt(scanner.nextLine()) - 1;
        if (servisIndex < 0 || servisIndex >= jenisServisList.size()) {
            System.out.println("Pilihan jenis servis tidak valid.");
            return;
        }
        JenisServis jenisServisTerpilih = jenisServisList.get(servisIndex);

        System.out.println("\nPilih Teknisi:");
        List<Teknisi> teknisiList = teknisiDAO.getTeknisiTersedia();
        if (teknisiList.isEmpty()) {
            System.out.println("Maaf, tidak ada teknisi tersedia saat ini.");
            return;
        }

        for (int i = 0; i < teknisiList.size(); i++) {
            Teknisi t = teknisiList.get(i);
            System.out.printf("%d. %s\n", i + 1, t.getNamaTeknisi());
        }

        System.out.print("Pilih teknisi (nomor): ");
        int teknisiIndex = Integer.parseInt(scanner.nextLine()) - 1;
        if (teknisiIndex < 0 || teknisiIndex >= teknisiList.size()) {
            System.out.println("Pilihan teknisi tidak valid.");
            return;
        }
        Teknisi teknisiTerpilih = teknisiList.get(teknisiIndex);

     // Buat dan simpan booking
        BookingServis bookingBaru = new BookingServis();
        bookingBaru.setIdPelanggan(pelangganId);
        bookingBaru.setIdMobil(mobilTerpilih.getIdMobil());
        bookingBaru.setIdServis(jenisServisTerpilih.getIdServis());
        bookingBaru.setIdTeknisi(teknisiTerpilih.getIdTeknisi());
        bookingBaru.setStatus("aktif");

        bookingDAO.insertBooking(bookingBaru);

        // 🔧 Update status teknisi jadi "sibuk"
        teknisiTerpilih.setStatus("sibuk");
        teknisiDAO.updateTeknisi(teknisiTerpilih);

        System.out.println("Booking servis berhasil dibuat!");

    } catch (NumberFormatException e) {
        System.out.println("Input harus berupa angka.");
    } catch (Exception e) {
        System.out.println("Terjadi kesalahan tak terduga: " + e.getMessage());
    }
}

    
    public static void lihatRiwayatBooking(int pelangganId) {
    System.out.println("\n--- Riwayat Booking Anda ---");
    BookingServisDAO dao = new BookingServisDAO();
    List<BookingServis> list = dao.getBookingByPelangganId(pelangganId);

    if (list.isEmpty()) {
        System.out.println("Belum ada riwayat booking.");
    } else {
        for (BookingServis b : list) {
            System.out.println("Tanggal: " + b.getTanggalBooking());
            System.out.println("Status: " + b.getStatus());
            System.out.println("Servis: " + b.getJenisServis().getNamaServis());
            System.out.println("Estimasi (menit): " + b.getJenisServis().getEstimasiWaktu());
            System.out.println("Teknisi: " + b.getTeknisi().getNamaTeknisi());
            System.out.println("------------------------");
        }
    }
}
}
