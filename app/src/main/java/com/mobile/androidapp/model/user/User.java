package com.mobile.androidapp.model.user;

import android.os.Parcel;
import android.os.Parcelable;


public class User implements Parcelable {
    private int id;
    private String name;
    private String userName;
    private String email;
    private Address address;
    private String phone;
    private String website;
    private Company company;

    public User(int id, String name, String userName, String email
            , Address address, String phone, String website, Company company) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.website = website;
        this.company = company;
    }

    protected User(Parcel in) {
        //mesma ordem da escrita do parcel faz para mandar na intent mesmo intuito do serializable
        id = in.readInt();
        name = in.readString();
        userName = in.readString();
        email = in.readString();
        //address = in.readString(company.getName());
        phone = in.readString();
        website = in.readString();
        //company = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    //metodo da interface de parcel
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(userName);
        parcel.writeString(email);
        //parcel.writeString(address);
        parcel.writeString(phone);
        parcel.writeString(website);
        parcel.writeString(company.getName());
        parcel.writeString(company.getCatchPhrase());
        parcel.writeString(company.getBs());
    }
}