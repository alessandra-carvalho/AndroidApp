package com.mobile.androidapp.repository;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.mobile.androidapp.model.albums.Albums;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AlbumsRepository implements Response.Listener<JSONArray>,Response.ErrorListener{
    private final String TAG = "AlbumsRepository";
    private List<Albums> albums;
    private static AlbumsRepository instance;
    private Context contexto;

    private AlbumsRepository(Context contexto) {
        super();
        this.contexto = contexto;
        albums = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(contexto);
        //usando o proprio objeto como ResponseListener
        JsonArrayRequest jaRequest = new JsonArrayRequest(Request.Method.GET,
                "https://jsonplaceholder.typicode.com/albums",
                null, this, this);

        queue.add(jaRequest);

        Log.e(TAG, "CommentsRepository: lancei" );
    }

    public static AlbumsRepository getInstance(Context contexto) {
        if (instance == null) {
            instance = new AlbumsRepository(contexto);
        }
        return instance;
    }

    public List<Albums> getAlbums() {
        return albums;
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

                // getting Albums data
                String idUser = (String) jo.getString("userId");
                String id = (String) jo.getString("id");
                String title = (String) jo.getString("title");

                // printing to test
                System.out.println("idUser: " +idUser);
                System.out.println("idAlbum: " +id);
                System.out.println("Title: " +title);

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
