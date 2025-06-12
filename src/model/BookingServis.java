package model;

import java.util.Date;
import java.time.LocalDate;
import java.time.LocalTime;


public class BookingServis {
    private int idBooking;

    // 🔁 DIUBAH: idUser ➜ idPelanggan
    private int idPelanggan;

    private int idMobil;
    private int idServis;
    private int idTeknisi;
    private Date tanggalBooking;
    private String status;

    private JenisServis jenisServis;
    private Teknisi teknisi;

    public BookingServis() {}

    public int getIdBooking() {
        return idBooking;
    }

    public void setIdBooking(int idBooking) {
        this.idBooking = idBooking;
    }

    // ✅ DITAMBAHKAN: Getter/Setter untuk idPelanggan
    public int getIdPelanggan() {
        return idPelanggan;
    }

    public void setIdPelanggan(int idPelanggan) {
        this.idPelanggan = idPelanggan;
    }

    // ❌ DIHAPUS:
    // public int getIdUser() { return idUser; }
    // public void setIdUser(int idUser) { this.idUser = idUser; }

    public int getIdMobil() {
        return idMobil;
    }

    public void setIdMobil(int idMobil) {
        this.idMobil = idMobil;
    }

    public int getIdServis() {
        return idServis;
    }

    public void setIdServis(int idServis) {
        this.idServis = idServis;
    }

    public int getIdTeknisi() {
        return idTeknisi;
    }

    public void setIdTeknisi(int idTeknisi) {
        this.idTeknisi = idTeknisi;
    }

    public Date getTanggalBooking() {
        return tanggalBooking;
    }

    public void setTanggalBooking(Date tanggalBooking) {
        this.tanggalBooking = tanggalBooking;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public JenisServis getJenisServis() {
        return jenisServis;
    }

    public void setJenisServis(JenisServis jenisServis) {
        this.jenisServis = jenisServis;
    }

    public Teknisi getTeknisi() {
        return teknisi;
    }

    public void setTeknisi(Teknisi teknisi) {
        this.teknisi = teknisi;
    }
    
    // BookingServis.java
        private String namaServis;
        private String namaTeknisi;

        public String getNamaServis() { return namaServis; }
        public void setNamaServis(String namaServis) { this.namaServis = namaServis; }

        public String getNamaTeknisi() { return namaTeknisi; }
        public void setNamaTeknisi(String namaTeknisi) { this.namaTeknisi = namaTeknisi; }
        
        private LocalDate tanggal;
        private LocalTime jam;

        public LocalDate getTanggal() {
            return tanggal;
        }

        public void setTanggal(LocalDate tanggal) {
            this.tanggal = tanggal;
        }

        public LocalTime getJam() {
            return jam;
        }

        public void setJam(LocalTime jam) {
            this.jam = jam;
        }


}
