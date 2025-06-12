/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author alifia ardita
 */


public class Mobil {
    private int idMobil;
    private int idUser;  // Pemilik mobil
    private int tahun;
    private String merk;
    private String model;
    private String platNomor;
    private Integer pelangganId;

    public Mobil() {}

    public Mobil(int idMobil, int idUser, String merk, String model, String platNomor , int tahun) {
        this.idMobil = idMobil;
        this.idUser = idUser;
        this.merk = merk;
        this.model = model;
        this.tahun = tahun;
        this.platNomor = platNomor;
    }

 

    public int getIdMobil() {
        return idMobil;
    }

    public void setIdMobil(int idMobil) {
        this.idMobil = idMobil;
    }

   public Integer getPelangganId() {
        return pelangganId != null ? pelangganId : -1;
    }

    public void setPelangganId(Integer pelangganId) { // ✅ Ditambahkan
        this.pelangganId = pelangganId;
    }

    public String getMerk() {
        return merk;
    }
    
      public String getModel() {
        return model;
    }
      
        public int getTahun() {
        return tahun;
    }

    
    
        public void setModel(String model) {
        this.model = model;
    }

    public String getPlatNomor() {
        return platNomor;
    }

    public void setPlatNomor(String platNomor) {
        this.platNomor = platNomor;
    }

    public void setMerek(String merk) {
        this.merk = merk;
    }
    
    public void setTahun(int tahun) {
        this.tahun = tahun;
    }
    
    
}

