package service;

import dbconnection.DBConnection;
import model.UserSession;

import java.sql.*;

public class AuthService {

    public static UserSession login(String username, String password) {
        String sql = "SELECT * FROM user WHERE username = ? AND password = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int userId = rs.getInt("user_id");
                String role = rs.getString("role");

                // Ambil ID pelanggan/admin dari tabel user
                Integer pelangganId = rs.getObject("pelanggan_id") != null ? rs.getInt("pelanggan_id") : null;
                Integer adminId = rs.getObject("admin_id") != null ? rs.getInt("admin_id") : null;

                System.out.println("Login berhasil sebagai " + role);
                return new UserSession(userId, role, pelangganId, adminId);
            } else {
                System.out.println("Username atau password salah!");
                return null;
            }

        } catch (SQLException e) {
            System.out.println("Terjadi kesalahan saat login:");
            e.printStackTrace();
            return null;
        }
    }

    // ⚠️ Tidak perlu method getPelangganIdByUserId karena relasi sudah diambil dari tabel user
}
