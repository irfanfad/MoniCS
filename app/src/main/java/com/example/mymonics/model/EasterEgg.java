package com.example.mymonics.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EasterEgg implements Parcelable
{

    @SerializedName("id_easter_egg")
    @Expose
    private Integer idEasterEgg;
    @SerializedName("bonus_benar")
    @Expose
    private Integer bonusBenar;
    @SerializedName("bonus_salah")
    @Expose
    private Integer bonusSalah;
    @SerializedName("id_pertanyaan")
    @Expose
    private Integer idPertanyaan;
    @SerializedName("soal")
    @Expose
    private String soal;
    @SerializedName("jawaban")
    @Expose
    private String jawaban;
    @SerializedName("salah1")
    @Expose
    private String salah1;
    @SerializedName("salah2")
    @Expose
    private String salah2;
    @SerializedName("salah3")
    @Expose
    private String salah3;
    public final static Parcelable.Creator<EasterEgg> CREATOR = new Creator<EasterEgg>() {


        @SuppressWarnings({
                "unchecked"
        })
        public EasterEgg createFromParcel(Parcel in) {
            return new EasterEgg(in);
        }

        public EasterEgg[] newArray(int size) {
            return (new EasterEgg[size]);
        }

    }
            ;

    protected EasterEgg(Parcel in) {
        this.idEasterEgg = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.bonusBenar = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.bonusSalah = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.idPertanyaan = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.soal = ((String) in.readValue((String.class.getClassLoader())));
        this.jawaban = ((String) in.readValue((String.class.getClassLoader())));
        this.salah1 = ((String) in.readValue((String.class.getClassLoader())));
        this.salah2 = ((String) in.readValue((String.class.getClassLoader())));
        this.salah3 = ((String) in.readValue((String.class.getClassLoader())));
    }

    public EasterEgg() {
    }

    public Integer getIdEasterEgg() {
        return idEasterEgg;
    }

    public void setIdEasterEgg(Integer idEasterEgg) {
        this.idEasterEgg = idEasterEgg;
    }

    public Integer getBonusBenar() {
        return bonusBenar;
    }

    public void setBonusBenar(Integer bonusBenar) {
        this.bonusBenar = bonusBenar;
    }

    public Integer getBonusSalah() {
        return bonusSalah;
    }

    public void setBonusSalah(Integer bonusSalah) {
        this.bonusSalah = bonusSalah;
    }

    public Integer getIdPertanyaan() {
        return idPertanyaan;
    }

    public void setIdPertanyaan(Integer idPertanyaan) {
        this.idPertanyaan = idPertanyaan;
    }

    public String getSoal() {
        return soal;
    }

    public void setSoal(String soal) {
        this.soal = soal;
    }

    public String getJawaban() {
        return jawaban;
    }

    public void setJawaban(String jawaban) {
        this.jawaban = jawaban;
    }

    public String getSalah1() {
        return salah1;
    }

    public void setSalah1(String salah1) {
        this.salah1 = salah1;
    }

    public String getSalah2() {
        return salah2;
    }

    public void setSalah2(String salah2) {
        this.salah2 = salah2;
    }

    public String getSalah3() {
        return salah3;
    }

    public void setSalah3(String salah3) {
        this.salah3 = salah3;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(idEasterEgg);
        dest.writeValue(bonusBenar);
        dest.writeValue(bonusSalah);
        dest.writeValue(idPertanyaan);
        dest.writeValue(soal);
        dest.writeValue(jawaban);
        dest.writeValue(salah1);
        dest.writeValue(salah2);
        dest.writeValue(salah3);
    }

    public int describeContents() {
        return 0;
    }

}