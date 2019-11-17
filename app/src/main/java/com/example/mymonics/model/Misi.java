package com.example.mymonics.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Misi {

    @SerializedName("id_misi")
    @Expose
    private String idMisi;
    @SerializedName("nama_misi")
    @Expose
    private String namaMisi;
    @SerializedName("jam_mulai")
    @Expose
    private String jamMulai;
    @SerializedName("jam_selesai")
    @Expose
    private String jamSelesai;
    @SerializedName("lokasi")
    @Expose
    private String lokasi;

    public String getIdMisi() {
        return idMisi;
    }

    public void setIdMisi(String idMisi) {
        this.idMisi = idMisi;
    }

    public String getNamaMisi() {
        return namaMisi;
    }

    public void setNamaMisi(String namaMisi) {
        this.namaMisi = namaMisi;
    }

    public String getJamMulai() {
        return jamMulai;
    }

    public void setJamMulai(String jamMulai) {
        this.jamMulai = jamMulai;
    }

    public String getJamSelesai() {
        return jamSelesai;
    }

    public void setJamSelesai(String jamSelesai) {
        this.jamSelesai = jamSelesai;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

}