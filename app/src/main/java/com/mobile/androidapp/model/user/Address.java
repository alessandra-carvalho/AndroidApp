package com.mobile.androidapp.model.user;

import android.os.Parcel;
import android.os.Parcelable;

public class Address implements Parcelable {

    private String street;
    private String suite;
    private String city;
    private AddressGeo addressGeo;

    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public String getSuite() {
        return suite;
    }
    public void setSuite(String suite) {
        this.suite = suite;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public AddressGeo getAddressGeo() {
        return addressGeo;
    }
    public void setAddressGeo(AddressGeo addressGeo) {
        this.addressGeo = addressGeo;
    }

    public Address(String street, String suite, String city, AddressGeo addressGeo) {
        this.street = street;
        this.suite = suite;
        this.city = city;
        this.addressGeo = addressGeo;
    }

    protected Address(Parcel in) {
        street = in.readString();
        suite = in.readString();
        city = in.readString();
        //addressGeo = in.readTypedObject(AddressGeo);
    }

    public static final Creator<Address> CREATOR = new Creator<Address>() {
        @Override
        public Address createFromParcel(Parcel in) {
            return new Address(in);
        }

        @Override
        public Address[] newArray(int size) {
            return new Address[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    //metodo da interface de parcel
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(street);
        parcel.writeString(suite);
        //parcel.writeString(addressGeo);
        parcel.writeString(city);
    }


}
