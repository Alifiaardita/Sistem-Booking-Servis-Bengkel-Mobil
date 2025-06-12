/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author alifia ardita
 */


import model.Pelanggan;
import dbconnection.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PelangganDAO {

    public List<Pelanggan> getAllPelanggan() {
        List<Pelanggan> list = new ArrayList<>();
        String sql = "SELECT * FROM pelanggan";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Pelanggan p = new Pelanggan();
                p.setIdPelanggan(rs.getInt("pelanggan_id"));
                p.setNamaPelanggan(rs.getString("nama"));
                p.setNoHp(rs.getString("no_hp"));
                p.setAlamat(rs.getString("alamat"));
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}

