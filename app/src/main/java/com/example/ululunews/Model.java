package com.example.ululunews;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Model extends RealmObject {

    @PrimaryKey
    private Integer id;
    private String judul;
    private String deskripsi;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private String sumber;
    private String gambar;
    public  Model(){

    }
    public Model(Integer id, String judul, String deskripsi, String sumber, String gambar) {
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
