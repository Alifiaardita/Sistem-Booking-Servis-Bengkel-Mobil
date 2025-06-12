/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package dbconnection;

// Source code is decompiled from a .class file using FernFlower decompiler.
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
   private static final String URL = "jdbc:mysql://localhost:3306/Bengkel?useSSL=false&serverTimezone=UTC";
   private static final String USER = "root";
   private static final String PASSWORD = "Dita1234";

   public DBConnection() {
   }

   public static Connection getConnection() {
      try {
         Class.forName("com.mysql.cj.jdbc.Driver");
         return DriverManager.getConnection("jdbc:mysql://localhost:3306/Bengkel?useSSL=false&serverTimezone=UTC", "root", "Dita1234");
      } catch (ClassNotFoundException var1) {
         System.out.println("Driver MySQL tidak ditemukan!");
         var1.printStackTrace();
      } catch (SQLException var2) {
         System.out.println("Koneksi ke database gagal!");
         var2.printStackTrace();
      }
      return null;
   }
}
