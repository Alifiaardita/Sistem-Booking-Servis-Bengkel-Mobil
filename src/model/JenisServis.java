/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author alifia ardita
 */


public class JenisServis {
    private int idServis;
    private String namaServis;
    private int estimasiWaktu; // dalam menit atau jam sesuai kebutuhan
    private double biaya;


    public JenisServis() {}

    public JenisServis(int idServis, String namaServis, int estimasiWaktu) {
        this.idServis = idServis;
        this.namaServis = namaServis;
        this.estimasiWaktu = estimasiWaktu;
    }

    public int getIdServis() {
        return idServis;
    }

    public void setIdServis(int idServis) {
        this.idServis = idServis;
    }

    public String getNamaServis() {
        return namaServis;
    }

    public void setNamaServis(String namaServis) {
        this.namaServis = namaServis;
    }

    public int getEstimasiWaktu() {
        return estimasiWaktu;
    }

    public void setEstimasiWaktu(int estimasiWaktu) {
        this.estimasiWaktu = estimasiWaktu;
    }
    
    // Getter untuk biaya
    public double getBiaya() {
    return biaya;
}

// Setter untuk biaya
    public void setBiaya(double biaya) {
    this.biaya = biaya;
}

}

