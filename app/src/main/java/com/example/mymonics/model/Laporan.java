package com.example.mymonics.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Laporan {
    @SerializedName("id_lapor")
    @Expose
    private Integer idLapor;
    @SerializedName("waktu_mulai")
    @Expose
    private String waktuMulai;
    @SerializedName("waktu_lapor")
    @Expose
    private String waktuLapor;
    @SerializedName("gambar_lapor")
    @Expose
    private String gambarLapor;
    @SerializedName("nik")
    @Expose
    private String nik;
    @SerializedName("id_misi")
    @Expose
    private Integer idMisi;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("nama_misi")
    @Expose
    private String namaMisi;
    @SerializedName("point_misi")
    @Expose
    private Integer pointMisi;

    public Integer getIdLapor() {
        return idLapor;
    }

    public void setIdLapor(Integer idLapor) {
        this.idLapor = idLapor;
    }

    public String getWaktuMulai() {
        return waktuMulai;
    }

    public void setWaktuMulai(String waktuMulai) {
        this.waktuMulai = waktuMulai;
    }

    public String getWaktuLapor() {
        return waktuLapor;
    }

    public void setWaktuLapor(String waktuLapor) {
        this.waktuLapor = waktuLapor;
    }

    public String getGambarLapor() {
        return gambarLapor;
    }

    public void setGambarLapor(String gambarLapor) {
        this.gambarLapor = gambarLapor;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public Integer getIdMisi() {
        return idMisi;
    }

    public void setIdMisi(Integer idMisi) {
        this.idMisi = idMisi;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNamaMisi() {
        return namaMisi;
    }

    public void setNamaMisi(String namaMisi) {
        this.namaMisi = namaMisi;
    }

    public Integer getPointMisi() {
        return pointMisi;
    }

    public void setPointMisi(Integer pointMisi) {
        this.pointMisi = pointMisi;
    }

    @SerializedName("message")
    @Expose
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
