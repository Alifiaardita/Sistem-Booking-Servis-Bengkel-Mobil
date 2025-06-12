/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author alifia ardita
**/


import model.Teknisi;
import dbconnection.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeknisiDAO {

    public List<Teknisi> getAllTeknisi() {
        List<Teknisi> list = new ArrayList<>();
        String sql = "SELECT * FROM teknisi";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Teknisi t = new Teknisi();
                t.setIdTeknisi(rs.getInt("teknisi_id"));
                t.setNamaTeknisi(rs.getString("nama"));
                t.setStatus(rs.getString("status"));
                list.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Teknisi getTeknisiById(int id) {
        Teknisi t = null;
        String sql = "SELECT * FROM teknisi WHERE teknisi_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    t = new Teknisi();
                    t.setIdTeknisi(rs.getInt("teknisi_id"));
                    t.setNamaTeknisi(rs.getString("nama"));
                    t.setStatus(rs.getString("status"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return t;
    }

    public void insertTeknisi(Teknisi t) {
        String sql = "INSERT INTO teknisi (nama, status) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, t.getNamaTeknisi());
            stmt.setString(2, t.getStatus());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTeknisi(Teknisi t) {
        String sql = "UPDATE teknisi SET nama = ?, status = ? WHERE teknisi_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, t.getNamaTeknisi());
            stmt.setString(2, t.getStatus());
            stmt.setInt(3, t.getIdTeknisi());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   public boolean deleteTeknisi(int id) {
    String sql = "DELETE FROM teknisi WHERE teknisi_id = ?";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, id);
        int rowsAffected = stmt.executeUpdate();

        return rowsAffected > 0; // hanya true kalau ada baris yang terhapus

    } catch (SQLException e) {
        e.printStackTrace();
        return false; // jika error, dianggap gagal
    }
}


    public List<Teknisi> getTeknisiTersedia() {
    List<Teknisi> list = new ArrayList<>();
    String sql = "SELECT teknisi_id, nama AS nama_teknisi, status FROM teknisi WHERE status = 'tersedia'";
    
    try (Connection conn = DBConnection.getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {

        while (rs.next()) {
            Teknisi t = new Teknisi();
            t.setIdTeknisi(rs.getInt("teknisi_id"));
            t.setNamaTeknisi(rs.getString("nama_teknisi"));
            t.setStatus(rs.getString("status"));
            list.add(t);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
}


}
