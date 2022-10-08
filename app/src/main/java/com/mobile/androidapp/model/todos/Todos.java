package com.mobile.androidapp.model.todos;

import android.os.Parcel;
import android.os.Parcelable;

import com.mobile.androidapp.model.user.User;

public class Todos implements Parcelable {

    private User user;
    private String id;
    private String title;
    private Boolean completed;
    
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
    public Boolean getCompleted() {
        return completed;
    }
    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Todos(User user, String id, String title, Boolean completed) {
        this.user = user;
        this.id = id;
        this.title = title;
        this.completed = completed;

    }

    protected Todos(Parcel in) {
        id = in.readString();
        title = in.readString();
        //completed = in.readBoolean();
    }

    public static final Creator<Todos> CREATOR = new Creator<Todos>() {

        @Override
        public Todos createFromParcel(Parcel in) {
            return new Todos(in);
        }

        @Override
        public Todos[] newArray(int size) {
            return new Todos[size];
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
        //parcel.writeString(parcel.readString(completed)));
    }
    
}
