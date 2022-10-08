package com.mobile.androidapp.model.comments;

import android.os.Parcel;
import android.os.Parcelable;
import com.mobile.androidapp.model.posts.Posts;

public class Comments implements Parcelable {

    private Posts posts;
    private String id;
    private String name;
    private String email;
    private String body;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }

    public Comments(Posts posts, String id, String name, String email, String body) {
        this.posts = posts;
        this.id = id;
        this.name = name;
        this.email = email;
        this.body = body;
    }

    protected Comments(Parcel in) {
        id = in.readString();
        name = in.readString();
        email = in.readString();
        body = in.readString();
    }

    public static final Creator<Comments> CREATOR = new Creator<Comments>() {

        @Override
        public Comments createFromParcel(Parcel in) {
            return new Comments(in);
        }

        @Override
        public Comments[] newArray(int size) {
            return new Comments[size];
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
        parcel.writeString(name);
        parcel.writeString(email);
        parcel.writeString(body);
    }

}


