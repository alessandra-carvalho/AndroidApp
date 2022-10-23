package com.mobile.androidapp.repository;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.mobile.androidapp.model.photos.Photos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PhotosRepository implements Response.Listener<JSONArray>,Response.ErrorListener{
    private final String TAG = "PhotosRepository";
    private List<Photos> photos;
    private static PhotosRepository instance;
    private Context contexto;

    private PhotosRepository(Context contexto) {
        super();
        this.contexto = contexto;
        photos = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(contexto);
        //usando o proprio objeto como ResponseListener
        JsonArrayRequest jaRequest = new JsonArrayRequest(Request.Method.GET,
                "https://jsonplaceholder.typicode.com/photos",
                null, this, this);

        queue.add(jaRequest);

        Log.e(TAG, "CommentsRepository: lancei" );
    }

    public static PhotosRepository getInstance(Context contexto) {
        if (instance == null) {
            instance = new PhotosRepository(contexto);
        }
        return instance;
    }

    public List<Photos> getPhotos() {
        return photos;
    }

    @Override
    public void onResponse(JSONArray response) {

        Log.e(TAG, "onResponse: "+response.length());
        for (int i = 0; i < response.length(); i++) {
            try {
                JSONObject json = response.getJSONObject(i);
                Log.d(TAG, "onResponse: "+json.toString());

                // typecasting obj to JSONObject
                JSONObject jo = response.getJSONObject(i);

                // getting Photos data
                String idAlbum = (String) jo.getString("albumId");
                String id = (String) jo.getString("id");
                String title = (String) jo.getString("title");
                String url = (String) jo.getString("url");
                String thumbnailUrl = (String) jo.getString("thumbnailUrl");

                // printing to test
                System.out.println("idAlbum: " +idAlbum);
                System.out.println("idPhoto: " +id);
                System.out.println("Title: " +title);
                System.out.println("URL: " +url);
                System.out.println("ThumbnailUrl: " +thumbnailUrl);

                // create object Comments (Success!)
                //posts.add(new User());


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        Log.e(TAG, "onResponse: END" );
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.e(TAG, "onErrorResponse: "+error.getMessage() );
    }
}
