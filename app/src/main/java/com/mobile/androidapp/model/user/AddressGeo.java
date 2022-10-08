package com.mobile.androidapp.model.user;

import android.os.Parcel;
import android.os.Parcelable;

public class AddressGeo implements Parcelable {

    private String lat;
    private String lng;

    public String getLat() {
        return lat;
    }
    public void setLat(String lat) {
        this.lat = lat;
    }
    public String getLng() {
        return lng;
    }
    public void setLng(String lng) {
        this.lng = lng;
    }

    public AddressGeo(String lat, String lng){
        this.lat = lat;
        this.lng = lng;
    }
    protected AddressGeo(Parcel in) {
        //mesma ordem da escrita do parcel faz para mandar na intent mesmo intuito do serializable
        lat = in.readString();
        lng = in.readString();
    }

    public static final Creator<AddressGeo> CREATOR = new Creator<AddressGeo>() {
        @Override
        public AddressGeo createFromParcel(Parcel in) {
            return new AddressGeo(in);
        }

        @Override
        public AddressGeo[] newArray(int size) {
            return new AddressGeo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    //metodo da interface de parcel
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(lat);
        parcel.writeString(lng);
    }
}
