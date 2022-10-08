package com.mobile.androidapp.model.user;

import android.os.Parcel;
import android.os.Parcelable;

public class Company implements Parcelable {

    private String name;
    private String catchPhrase;
    private String bs;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCatchPhrase() {
        return catchPhrase;
    }
    public void setCatchPhrase(String catchPhrase) {
        this.catchPhrase = catchPhrase;
    }
    public String getBs() {
        return bs;
    }
    public void setBs(String bs) {
        this.bs = bs;
    }

    public Company(String name, String catchPhrase, String bs){
        this.name = name;
        this.catchPhrase = catchPhrase;
        this.bs = bs;
    }

    protected Company(Parcel in) {
        //mesma ordem da escrita do parcel faz para mandar na intent mesmo intuito do serializable
        name = in.readString();
        catchPhrase = in.readString();
        bs = in.readString();
    }

    public static final Creator<Company> CREATOR = new Creator<Company>() {
        @Override
        public Company createFromParcel(Parcel in) {
            return new Company(in);
        }

        @Override
        public Company[] newArray(int size) {
            return new Company[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    //metodo da interface de parcel
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(catchPhrase);
        parcel.writeString(bs);
    }
}


