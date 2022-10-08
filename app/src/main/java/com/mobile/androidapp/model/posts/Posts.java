package com.mobile.androidapp.model.posts;

import android.os.Parcel;
import android.os.Parcelable;
import com.mobile.androidapp.model.user.User;

public class Posts implements Parcelable {

    private User user;
    private String id;
    private String title;
    private String body;

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
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }

    public Posts(User user, String id, String title, String body) {
        this.user = user;
        this.id = id;
        this.title = title;
        this.body = body;

    }

    protected Posts(Parcel in) {
        id = in.readString();
        title = in.readString();
        body = in.readString();
    }

    public static final Creator<Posts> CREATOR = new Creator<Posts>() {

        @Override
        public Posts createFromParcel(Parcel in) {
            return new Posts(in);
        }

        @Override
        public Posts[] newArray(int size) {
            return new Posts[size];
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
        //parcel.writeString(addressGeo);
        parcel.writeString(body);
    }

}
