package com.mobile.androidapp.repository;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.mobile.androidapp.model.todos.Todos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TodosRepository implements Response.Listener<JSONArray>,Response.ErrorListener{
    private final String TAG = "TodosRepository";
    private List<Todos> todos;
    private static TodosRepository instance;
    private Context contexto;

    private TodosRepository(Context contexto) {
        super();
        this.contexto = contexto;
        todos = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(contexto);
        //usando o proprio objeto como ResponseListener
        JsonArrayRequest jaRequest = new JsonArrayRequest(Request.Method.GET,
                "https://jsonplaceholder.typicode.com/todos",
                null, this, this);

        queue.add(jaRequest);

        Log.e(TAG, "PostsRepository: lancei" );
    }

    public static TodosRepository getInstance(Context contexto) {
        if (instance == null) {
            instance = new TodosRepository(contexto);
        }
        return instance;
    }

    public List<Todos> getTodos() {
        return todos;
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

                // getting Todos data
                String idUser = (String) jo.getString("userId");
                int intIdUser = Integer.parseInt(idUser);
                String id = (String) jo.getString("id");
                String title = (String) jo.getString("title");
                String completed = (String) jo.getString("completed");

                // printing to test
                System.out.println("IdUser: " +intIdUser);
                System.out.println("IdTodos: " +id);
                System.out.println("Title: " +title);
                System.out.println("Completed: " +completed);


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
