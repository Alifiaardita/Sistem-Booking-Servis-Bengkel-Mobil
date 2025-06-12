package main;

import service.AuthService;
import ui.AdminMenu;
import ui.UserMenu;

import java.util.Scanner;
import model.UserSession;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("=== SISTEM BOOKING SERVIS ===");
            System.out.println("Pilih jenis login:");
            System.out.println("1. Admin");
            System.out.println("2. Pelanggan");
            System.out.println("0. Keluar");
            System.out.print("Pilihan Anda: ");
            String pilihan = input.nextLine();

            if (pilihan.equals("0")) {
                System.out.println("Terima kasih. Aplikasi ditutup.");
                break;
            }

            String expectedRole = null;
            switch (pilihan) {
                case "1":
                    expectedRole = "admin";
                    break;
                case "2":
                    expectedRole = "pelanggan";
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.\n");
                    continue;
            }

            System.out.print("Username: ");
            String username = input.nextLine();
            System.out.print("Password: ");
            String password = input.nextLine();

           UserSession userSession = AuthService.login(username, password);

                if (userSession != null) {
                String actualRole = userSession.getRole();


                if (expectedRole.equalsIgnoreCase(actualRole)) {
                switch (actualRole.toLowerCase()) {
                      case "admin":
                          AdminMenu.tampilkanMenu(); // bisa diubah juga kalau mau pakai adminId
                          break;
                      case "pelanggan":
                          userSession.getPelangganId();
                          UserMenu.tampilkanMenu(userSession);
                          break;
                  }
                  System.out.println("\n=== LOGOUT BERHASIL ===\n");
              } else {
                  System.out.println("Akun ini bukan role " + expectedRole + ". Silakan login ulang.\n");
              }
           }else {
              System.out.println("Login gagal. Username atau password salah.\n");
           }
        }
    }
}
