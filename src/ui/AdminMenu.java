/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

/**
 *
 * @author alifia ardita
 */

import dao.*;
import java.util.InputMismatchException;
import model.*;
import java.util.List;
import java.util.Scanner;

public class AdminMenu {
    private static Scanner scanner = new Scanner(System.in);
    private static PelangganDAO pelangganDAO = new PelangganDAO();
    private static TeknisiDAO teknisiDAO = new TeknisiDAO();
    private static JenisServisDAO jenisServisDAO = new JenisServisDAO();

    public static void tampilkanMenu() {
        int pilihan;

        do {
            System.out.println("\n=== MENU ADMIN ===");
            System.out.println("1. Lihat Daftar Pelanggan");
            System.out.println("2. Kelola Teknisi");
            System.out.println("3. Kelola Jenis Servis");
            System.out.println("0. Logout");
            System.out.print("Pilih: ");
            pilihan = Integer.parseInt(scanner.nextLine());

            switch (pilihan) {
                case 1:
                    lihatDaftarPelanggan();
                    break;
                case 2:
                    kelolaTeknisi();
                    break;
                case 3:
                    kelolaJenisServis();
                    break;
                case 0:
                    System.out.println("Logout...");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (pilihan != 0);
    }

    private static void lihatDaftarPelanggan() {
        List<Pelanggan> pelangganList = pelangganDAO.getAllPelanggan();
        if (pelangganList.isEmpty()) {
            System.out.println("Belum ada data pelanggan.");
        } else {
            System.out.println("\nDaftar Pelanggan:");
            for (Pelanggan p : pelangganList) {
                System.out.printf("ID: %d | Nama: %s | No HP: %s | Alamat: %s\n",
                    p.getIdPelanggan(), p.getNamaPelanggan(), p.getNoHp(), p.getAlamat());
            }
        }
    }

    private static void kelolaTeknisi() {
        int pilihan;
        do {
            System.out.println("\n=== Kelola Teknisi ===");
            System.out.println("1. Lihat Teknisi");
            System.out.println("2. Tambah Teknisi");
            System.out.println("3. Edit Teknisi");
            System.out.println("4. Hapus Teknisi");
            System.out.println("0. Kembali");
            System.out.print("Pilih: ");
            pilihan = Integer.parseInt(scanner.nextLine());

            switch (pilihan) {
                case 1:
                    lihatTeknisi();
                    break;
                case 2:
                    tambahTeknisi();
                    break;
                case 3:
                    editTeknisi();
                    break;
                case 4:
                    hapusTeknisi();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (pilihan != 0);
    }

    private static void lihatTeknisi() {
        List<Teknisi> teknisiList = teknisiDAO.getAllTeknisi();
        if (teknisiList.isEmpty()) {
            System.out.println("Belum ada data teknisi.");
        } else {
            System.out.println("\nDaftar Teknisi:");
            for (Teknisi t : teknisiList) {
                System.out.printf("ID: %d | Nama: %s | Status: %s\n", t.getIdTeknisi(), 
                        t.getNamaTeknisi(), t.getStatus());
            }
        }
    }

    private static void tambahTeknisi() {
        System.out.print("Masukkan nama teknisi: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan status teknisi (tersedia/sibuk): ");
        String status = scanner.nextLine();

        Teknisi t = new Teknisi();
        t.setNamaTeknisi(nama);
        t.setStatus(status);

        teknisiDAO.insertTeknisi(t);
        System.out.println("Teknisi berhasil ditambahkan.");
    }

    private static void editTeknisi() {
        System.out.print("Masukkan ID teknisi yang ingin diedit: ");
        int id = Integer.parseInt(scanner.nextLine());
        Teknisi t = teknisiDAO.getTeknisiById(id);
        if (t == null) {
            System.out.println("Teknisi tidak ditemukan.");
            return;
        }

        System.out.print("Masukkan nama teknisi baru (" + t.getNamaTeknisi() + "): ");
        String nama = scanner.nextLine();
        if (!nama.isEmpty()) {
            t.setNamaTeknisi(nama);
        }

        System.out.print("Masukkan status teknisi baru (" + t.getStatus() + "): ");
        String status = scanner.nextLine();
        if (!status.isEmpty()) {
            t.setStatus(status);
        }

        teknisiDAO.updateTeknisi(t);
        System.out.println("Teknisi berhasil diperbarui.");
    }

    private static void hapusTeknisi() {
      System.out.print("Masukkan ID teknisi yang ingin dihapus: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 

        boolean berhasil = teknisiDAO.deleteTeknisi(id);
        if (berhasil) {
            System.out.println("Teknisi berhasil dihapus.");
        } else {
            System.out.println("ID tidak ditemukan. Tidak ada yang dihapus.");
        }

    }

   private static void kelolaJenisServis() {
    int pilihan = -1;

    do {
        System.out.println("\n=== Kelola Jenis Servis ===");
        System.out.println("1. Lihat Jenis Servis");
        System.out.println("2. Tambah Jenis Servis");
        System.out.println("3. Edit Jenis Servis");
        System.out.println("4. Hapus Jenis Servis");
        System.out.println("0. Kembali");
        System.out.print("Pilih: ");

        String input = scanner.nextLine();
        if (input.isEmpty()) {
            System.out.println("Input tidak boleh kosong. Silakan coba lagi.");
            continue;
        }

        try {
            pilihan = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Input harus berupa angka. Silakan coba lagi.");
            continue;
        }

        switch (pilihan) {
            case 1:
                lihatJenisServis();
                break;
            case 2:
                tambahJenisServis();
                break;
            case 3:
                editJenisServis();
                break;
            case 4:
                hapusJenisServis();
                break;
            case 0:
                break;
            default:
                System.out.println("Pilihan tidak valid.");
        }
    } while (pilihan != 0);
}


    private static void lihatJenisServis() {
        List<JenisServis> jenisServisList = jenisServisDAO.getAll();
        if (jenisServisList.isEmpty()) {
            System.out.println("Belum ada data jenis servis.");
        } else {
            System.out.println("\nDaftar Jenis Servis:");
            for (JenisServis js : jenisServisList) {
               System.out.printf("ID: %d | Nama: %s | Estimasi Waktu: %d menit | Biaya: Rp %,.2f\n",
               js.getIdServis(), js.getNamaServis(), js.getEstimasiWaktu(), js.getBiaya());

            }
        }
    }

   private static void tambahJenisServis() {
    System.out.print("Masukkan nama jenis servis: ");
    String nama = scanner.nextLine();
    
    System.out.print("Masukkan estimasi waktu (menit): ");
    int estimasi = Integer.parseInt(scanner.nextLine());
    
    System.out.print("Masukkan biaya (angka saja): ");
    double biaya = Double.parseDouble(scanner.nextLine());

    JenisServis js = new JenisServis();
    js.setNamaServis(nama);
    js.setEstimasiWaktu(estimasi);
    js.setBiaya(biaya);  // <-- Tambahkan ini

    jenisServisDAO.insertJenisServis(js);
    System.out.println("Jenis servis berhasil ditambahkan.");
}


 private static void editJenisServis() {
    System.out.print("Masukkan ID jenis servis yang ingin diedit: ");
    int id = Integer.parseInt(scanner.nextLine());
    JenisServis js = jenisServisDAO.getJenisServisById(id);
    if (js == null) {
        System.out.println("Jenis servis tidak ditemukan.");
        return;
    }

    System.out.print("Masukkan nama jenis servis baru (" + js.getNamaServis() + "): ");
    String nama = scanner.nextLine();
    if (!nama.isEmpty()) {
        js.setNamaServis(nama);
    }

    System.out.print("Masukkan estimasi waktu baru (" + js.getEstimasiWaktu() + " menit): ");
    String estimasiStr = scanner.nextLine();
    if (!estimasiStr.isEmpty()) {
        js.setEstimasiWaktu(Integer.parseInt(estimasiStr));
    }

    System.out.print("Masukkan biaya baru (" + js.getBiaya() + "): ");
    String biayaStr = scanner.nextLine();
    if (!biayaStr.isEmpty()) {
        js.setBiaya(Double.parseDouble(biayaStr));
    }

    jenisServisDAO.updateJenisServis(js);
    System.out.println("Jenis servis berhasil diperbarui.");
}


   private static void hapusJenisServis() {
    System.out.print("Masukkan ID jenis servis yang ingin dihapus: ");
    int id;
    try {
        id = scanner.nextInt();
        scanner.nextLine(); // buang newline
    } catch (InputMismatchException e) {
        System.out.println("Input harus berupa angka.");
        scanner.nextLine(); // buang input buruk
        return;
    }

    boolean deleted = jenisServisDAO.deleteJenisServis(id);
    if (deleted) {
        System.out.println("Jenis servis berhasil dihapus.");
    } else {
        System.out.println("ID tidak ditemukan. Tidak ada yang dihapus.");
    }
}

}
