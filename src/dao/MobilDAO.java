package dao;

import model.Mobil;

import dbconnection.DBConnection;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class MobilDAO {
  
    public List<Mobil> getMobilByPelangganId(int pelangganId) {
    List<Mobil> mobilList = new ArrayList<>();
    String sql = "SELECT * FROM mobil WHERE pelanggan_id = ?";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, pelangganId);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Mobil mobil = new Mobil();
            mobil.setIdMobil(rs.getInt("mobil_id"));
            mobil.setMerek(rs.getString("merek"));
            mobil.setModel(rs.getString("model"));
            mobil.setPlatNomor(rs.getString("plat_nomor"));
            mobil.setTahun(rs.getInt("tahun"));
            mobil.setPelangganId(rs.getInt("pelanggan_id"));

            mobilList.add(mobil);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return mobilList;
}


    
}
