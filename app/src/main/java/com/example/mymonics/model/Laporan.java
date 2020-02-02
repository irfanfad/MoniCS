package com.example.mymonics.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Laporan implements Parcelable
{

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
    public final static Parcelable.Creator<Laporan> CREATOR = new Creator<Laporan>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Laporan createFromParcel(Parcel in) {
            return new Laporan(in);
        }

        public Laporan[] newArray(int size) {
            return (new Laporan[size]);
        }

    }
            ;

    protected Laporan(Parcel in) {
        this.idLapor = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.waktuMulai = ((String) in.readValue((String.class.getClassLoader())));
        this.waktuLapor = ((String) in.readValue((String.class.getClassLoader())));
        this.gambarLapor = ((String) in.readValue((String.class.getClassLoader())));
        this.nik = ((String) in.readValue((String.class.getClassLoader())));
        this.idMisi = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.status = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.nama = ((String) in.readValue((String.class.getClassLoader())));
        this.namaMisi = ((String) in.readValue((String.class.getClassLoader())));
        this.pointMisi = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public Laporan() {
    }

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

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(idLapor);
        dest.writeValue(waktuMulai);
        dest.writeValue(waktuLapor);
        dest.writeValue(gambarLapor);
        dest.writeValue(nik);
        dest.writeValue(idMisi);
        dest.writeValue(status);
        dest.writeValue(nama);
        dest.writeValue(namaMisi);
        dest.writeValue(pointMisi);
    }

    public int describeContents() {
        return 0;
    }

}