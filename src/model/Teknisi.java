/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author alifia ardita
 */


public class Teknisi {
    private int idTeknisi;
    private String namaTeknisi;
    private String status; // contoh: "tersedia", "sibuk"

    public Teknisi() {}

    public Teknisi(int idTeknisi, String namaTeknisi, String status) {
        this.idTeknisi = idTeknisi;
        this.namaTeknisi = namaTeknisi;
        this.status = status;
    }

    public int getIdTeknisi() {
        return idTeknisi;
    }

    public void setIdTeknisi(int idTeknisi) {
        this.idTeknisi = idTeknisi;
    }

    public String getNamaTeknisi() {
        return namaTeknisi;
    }

    public void setNamaTeknisi(String namaTeknisi) {
        this.namaTeknisi = namaTeknisi;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

