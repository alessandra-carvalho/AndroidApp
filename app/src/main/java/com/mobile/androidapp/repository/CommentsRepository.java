package com.mobile.androidapp.repository;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.mobile.androidapp.model.comments.Comments;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CommentsRepository implements Response.Listener<JSONArray>,Response.ErrorListener{
    private final String TAG = "CommentsRepository";
    private List<Comments> comments;
    private static CommentsRepository instance;
    private Context contexto;

    private CommentsRepository(Context contexto) {
        super();
        this.contexto = contexto;
        comments = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(contexto);
        //usando o proprio objeto como ResponseListener
        JsonArrayRequest jaRequest = new JsonArrayRequest(Request.Method.GET,
                "https://jsonplaceholder.typicode.com/comments",
                null, this, this);

        queue.add(jaRequest);

        Log.e(TAG, "CommentsRepository: lancei" );
    }

    public static CommentsRepository getInstance(Context contexto) {
        if (instance == null) {
            instance = new CommentsRepository(contexto);
        }
        return instance;
    }

    public List<Comments> getComments() {
        return comments;
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

                // getting comments data
                String idPost = (String) jo.getString("postId");
                String id = (String) jo.getString("id");
                String name = (String) jo.getString("name");
                String email = (String) jo.getString("email");
                String body = (String) jo.getString("body");

                // printing to test
                System.out.println("idPost: " +idPost);
                System.out.println("idComment: " +id);
                System.out.println("Name: " +name);
                System.out.println("Email: " +email);
                System.out.println("Body: " +body);


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
