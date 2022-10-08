package com.mobile.androidapp.model.albums;

import android.os.Parcel;
import android.os.Parcelable;
import com.mobile.androidapp.model.user.User;

public class Albums implements Parcelable {

    private User user;
    private String id;
    private String title;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public Albums(User user, String id, String title) {
        this.user = user;
        this.id = id;
        this.title = title;

    }

    protected Albums(Parcel in) {
        id = in.readString();
        title = in.readString();
    }

    public static final Creator<Albums> CREATOR = new Creator<Albums>() {

        @Override
        public Albums createFromParcel(Parcel in) {
            return new Albums(in);
        }

        @Override
        public Albums[] newArray(int size) {
            return new Albums[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    //metodo da interface de parcel
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(title);
    }

}
