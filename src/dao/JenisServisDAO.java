/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author alifia ardita
 */




import model.JenisServis;
import dbconnection.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JenisServisDAO {

    public List<JenisServis> getAll() {
        List<JenisServis> list = new ArrayList<>();
        String sql = "SELECT * FROM jenis_servis";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                JenisServis js = new JenisServis();
                js.setIdServis(rs.getInt("jenis_servis_id"));
                js.setNamaServis(rs.getString("nama_servis"));
                js.setEstimasiWaktu(rs.getInt("estimasi_menit"));
                js.setBiaya(rs.getDouble("biaya"));
                list.add(js);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public JenisServis getJenisServisById(int id) {
        JenisServis js = null;
        String sql = "SELECT * FROM jenis_servis WHERE jenis_servis_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    js = new JenisServis();
                    js.setIdServis(rs.getInt("jenis_servis_id"));
                    js.setNamaServis(rs.getString("nama_servis"));
                    js.setEstimasiWaktu(rs.getInt("estimasi_menit"));
                    js.setBiaya(rs.getDouble("biaya"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return js;
    }

    public void insertJenisServis(JenisServis js) {
        String sql = "INSERT INTO jenis_servis (nama_servis, estimasi_menit, biaya) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, js.getNamaServis());
            stmt.setInt(2, js.getEstimasiWaktu());
            stmt.setDouble(3, js.getBiaya());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   public void updateJenisServis(JenisServis js) {
    String sql = "UPDATE jenis_servis SET nama_servis = ?, estimasi_menit = ?, biaya = ? WHERE jenis_servis_id = ?";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, js.getNamaServis());
        stmt.setInt(2, js.getEstimasiWaktu());
        stmt.setDouble(3, js.getBiaya());
        stmt.setInt(4, js.getIdServis()); // ✔️ ini harus parameter ke-4

        stmt.executeUpdate();

    } catch (SQLException e) {
        e.printStackTrace();
    }
}


  public boolean deleteJenisServis(int id) {
    String sql = "DELETE FROM jenis_servis WHERE jenis_servis_id = ?";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, id);
        int affectedRows = stmt.executeUpdate();

        return affectedRows > 0;

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

}
