package com.mobile.androidapp.repository;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mobile.androidapp.model.posts.Posts;
import com.mobile.androidapp.model.user.Address;
import com.mobile.androidapp.model.user.AddressGeo;
import com.mobile.androidapp.model.user.Company;
import com.mobile.androidapp.model.user.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PostsRepository implements Response.Listener<JSONArray>,Response.ErrorListener{
    private final String TAG = "PostsRepository";
    private List<Posts> posts;
    private static PostsRepository instance;
    private Context contexto;

    private PostsRepository(Context contexto) {
        super();
        this.contexto = contexto;
        posts = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(contexto);
        //usando o proprio objeto como ResponseListener
        JsonArrayRequest jaRequest = new JsonArrayRequest(Request.Method.GET,
                "https://jsonplaceholder.typicode.com/posts",
                null, this, this);

        queue.add(jaRequest);

        Log.e(TAG, "PostsRepository: lancei" );
    }

    public static PostsRepository getInstance(Context contexto) {
        if (instance == null) {
            instance = new PostsRepository(contexto);
        }
        return instance;
    }

    public List<Posts> getPosts() {
        return posts;
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

                // getting posts data
                String iduser = (String) jo.getString("userId");
                int intIdUser = Integer.parseInt(iduser);
                String id = (String) jo.getString("id");
                String title = (String) jo.getString("title");
                String body = (String) jo.getString("body");

                // printing to test
                System.out.println("IdUser: " +iduser);
                System.out.println("IdPosts: " +id);
                System.out.println("Title: " +title);
                System.out.println("Body: " +body);


                // create object User (Success!)
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
