
package com.example.mymonics.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Misi implements Parcelable
{

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
    @SerializedName("id_lokasi")
    @Expose
    private String idLokasi;
    @SerializedName("point_misi")
    @Expose
    private String point;
    @SerializedName("nama_lokasi")
    @Expose
    private String namaLokasi;
    public final static Parcelable.Creator<Misi> CREATOR = new Creator<Misi>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Misi createFromParcel(Parcel in) {
            return new Misi(in);
        }

        public Misi[] newArray(int size) {
            return (new Misi[size]);
        }

    }
            ;

    protected Misi(Parcel in) {
        this.idMisi = ((String) in.readValue((String.class.getClassLoader())));
        this.namaMisi = ((String) in.readValue((String.class.getClassLoader())));
        this.jamMulai = ((String) in.readValue((String.class.getClassLoader())));
        this.jamSelesai = ((String) in.readValue((String.class.getClassLoader())));
        this.idLokasi = ((String) in.readValue((String.class.getClassLoader())));
        this.point = ((String) in.readValue((String.class.getClassLoader())));
        this.namaLokasi = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Misi() {
    }

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

    public String getIdLokasi() {
        return idLokasi;
    }

    public void setIdLokasi(String idLokasi) {
        this.idLokasi = idLokasi;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getNamaLokasi() {
        return namaLokasi;
    }

    public void setNamaLokasi(String namaLokasi) {
        this.namaLokasi = namaLokasi;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(idMisi);
        dest.writeValue(namaMisi);
        dest.writeValue(jamMulai);
        dest.writeValue(jamSelesai);
        dest.writeValue(idLokasi);
        dest.writeValue(point);
        dest.writeValue(namaLokasi);
    }

    public int describeContents() {
        return 0;
    }

}