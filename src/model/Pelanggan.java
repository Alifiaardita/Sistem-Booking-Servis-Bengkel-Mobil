package model;

public class Pelanggan {
    private int idPelanggan;
    private String namaPelanggan;
    private String noHp;
    private String alamat;

    // Constructor kosong
    public Pelanggan() {
    }

    // Constructor dengan parameter
    public Pelanggan(int idPelanggan, String namaPelanggan, String noHp, String alamat) {
        this.idPelanggan = idPelanggan;
        this.namaPelanggan = namaPelanggan;
        this.noHp = noHp;
        this.alamat = alamat;
    }

    // Getter dan setter
    public int getIdPelanggan() {
        return idPelanggan;
    }

    public void setIdPelanggan(int idPelanggan) {
        this.idPelanggan = idPelanggan;
    }

    public String getNamaPelanggan() {
        return namaPelanggan;
    }

    public void setNamaPelanggan(String namaPelanggan) {
        this.namaPelanggan = namaPelanggan;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
