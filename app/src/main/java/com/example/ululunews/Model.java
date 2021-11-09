package com.example.ululunews;

public class Model {
    private String judul, deskripsi, sumber, gambar;
    public Model(String judul, String deskripsi, String sumber, String gambar) {
        this.judul = judul;
        this.deskripsi = deskripsi;
        this.sumber = sumber;
        this.gambar = gambar;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getSumber() {
        return sumber;
    }

    public void setSumber(String sumber) {
        this.sumber = sumber;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }


}
