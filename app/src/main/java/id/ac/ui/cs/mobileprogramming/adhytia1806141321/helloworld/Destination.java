package id.ac.ui.cs.mobileprogramming.adhytia1806141321.helloworld;

public class Destination {
    private String nama, lokasi, biaya, deskripsi;
    private int id, foto;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Destination() {
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getBiaya() {
        return biaya;
    }

    public void setBiaya(String biaya) {
        this.biaya = biaya;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }
}