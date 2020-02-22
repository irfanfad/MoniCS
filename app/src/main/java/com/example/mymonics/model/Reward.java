package com.example.mymonics.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Reward implements Parcelable
{

    @SerializedName("id_reward")
    @Expose
    private Integer idReward;
    @SerializedName("nama_reward")
    @Expose
    private String namaReward;
    @SerializedName("point_reward")
    @Expose

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @SerializedName("message")
    @Expose


    private Integer pointReward;
    @SerializedName("gambar_reward")
    @Expose
    private Object gambarReward;
    public final static Parcelable.Creator<Reward> CREATOR = new Creator<Reward>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Reward createFromParcel(Parcel in) {
            return new Reward(in);
        }

        public Reward[] newArray(int size) {
            return (new Reward[size]);
        }

    }
            ;

    protected Reward(Parcel in) {
        this.idReward = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.namaReward = ((String) in.readValue((String.class.getClassLoader())));
        this.pointReward = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.gambarReward = ((Object) in.readValue((Object.class.getClassLoader())));
    }

    public Reward() {
    }

    public Integer getIdReward() {
        return idReward;
    }

    public void setIdReward(Integer idReward) {
        this.idReward = idReward;
    }

    public String getNamaReward() {
        return namaReward;
    }

    public void setNamaReward(String namaReward) {
        this.namaReward = namaReward;
    }

    public Integer getPointReward() {
        return pointReward;
    }

    public void setPointReward(Integer pointReward) {
        this.pointReward = pointReward;
    }

    public Object getGambarReward() {
        return gambarReward;
    }

    public void setGambarReward(Object gambarReward) {
        this.gambarReward = gambarReward;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(idReward);
        dest.writeValue(namaReward);
        dest.writeValue(pointReward);
        dest.writeValue(gambarReward);
    }

    public int describeContents() {
        return 0;
    }

}