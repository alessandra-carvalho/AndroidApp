package com.mobile.androidapp.repository;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.android.material.snackbar.Snackbar;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.mobile.androidapp.model.user.Address;
import com.mobile.androidapp.model.user.AddressGeo;
import com.mobile.androidapp.model.user.Company;
import com.mobile.androidapp.model.user.User;

public class UserRepository implements Listener<JSONArray>,Response.ErrorListener{
    private final String TAG = "UserRepository";
    private List<User> users;
    private static UserRepository instance;
    private Context contexto;

    private UserRepository(Context contexto) {
        super();
        this.contexto = contexto;
        users = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(contexto);
        //usando o proprio objeto como ResponseListener
        JsonArrayRequest jaRequest = new JsonArrayRequest(Request.Method.GET,
                                                       "https://jsonplaceholder.typicode.com/users",
                                                null, this, this);

        //exemplo de uso com injeção do ResponseListener e erro Listener
        JsonArrayRequest jaRequestInject1 = new JsonArrayRequest(Request.Method.GET,
                "https://jsonplaceholder.typicode.com/users",
                null,
                new Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.e(TAG, "onResponse: " + response.length());
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject json = response.getJSONObject(i);
                                Log.d(TAG, "onResponse: " + json.toString());
                                users.add(createUserFromJson(json));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        Log.e(TAG, "onResponse: terminei");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "onErrorResponse: " + error.getMessage());
                    }
                });
        //exemplo de uso com injeção com lambda do ResponseListener e erro Listener
        JsonArrayRequest jaRequestInject2 = new JsonArrayRequest(Request.Method.GET,
                "https://jsonplaceholder.typicode.com/users",
                null,
                (JSONArray response) -> {
                        response = response;
                        Log.e(TAG, "onResponse: " + response.length());
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject json = response.getJSONObject(i);
                                Log.d(TAG, "onResponse: " + json.toString());
                                //isto
                                users.add(createUserFromJson(json));
                                //troca isto abaixo
                                //users.add(new User(json.getInt("id"), json.getString("name"),
                                //json.getString("username"), json.getString("username")));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        Log.e(TAG, "onResponse: terminei");
                    }                ,
                (VolleyError error) ->{
                        Log.e(TAG, "onErrorResponse: " + error.getMessage());
                    });

        queue.add(jaRequest);

        Log.e(TAG, "UserRepository: lancei" );
    }

    public static UserRepository getInstance(Context contexto) {
        if (instance == null) {
            instance = new UserRepository(contexto);
        }
        return instance;
    }

    // metodo para criar um objeto User apartir de um json
    public User createUserFromJson(JSONObject json) {
        try {

            // teste instancias das classes alvo
            AddressGeo objAddressGeo = new AddressGeo("lat xxx", "lng yyyy");
            Address objAddress = new Address("street xxxx", "suite xxxx", "city xxxx", objAddressGeo);
            Company objcompany = new Company("name company xxxx", "catchPhrase xxxx", "bs xxxxx");

            return new User(json.getInt("id"), json.getString("name"),
                    json.getString("username"), json.getString("email"), objAddress,
                    "phone xxxx", "website xxxx", objcompany);

            //return new User(json.getInt("id"), json.getString("name"),
             //   json.getString("username"), json.getString("username"));

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<User> getUsers() {
        return users;
    }

    public User getUserById(int id) {
        User ret = null;
        for(User u : users) {
            if (u.getId() == id) {
                ret = u;
            }
        }
        return ret;
    }

    public User addUser(User user) {return null;}

    public User updateUser(User user) {return null;}

    public User removeUser(User user) {return null;}

    @Override
    public void onResponse(JSONArray response) {

        Log.e(TAG, "onResponse: "+response.length());
        for (int i = 0; i < response.length(); i++) {
            try {
                JSONObject json = response.getJSONObject(i);
                Log.d(TAG, "onResponse: "+json.toString());
                //users.add( new User( json.getInt("id"), json.getString("name"),
                //        json.getString("username"), json.getString("username")));

                // https://www.geeksforgeeks.org/parse-json-java/
                //https://www.digitalocean.com/community/tutorials/jackson-json-java-parser-api-example-tutorial
                // typecasting obj to JSONObject
                JSONObject jo = response.getJSONObject(i);
                // getting firstName and lastName
                String id = (String) jo.getString("id");
                String name = (String) jo.getString("name");

                System.out.println("Id: " +id);
                System.out.println("Name: " +name);

                // getting address
                ObjectMapper mapper = new ObjectMapper();
                Map<String, Object> userData = mapper.readValue(
                        String.valueOf(json), new TypeReference<Map<String, Object>>() {
                        });
                Map address = ((Map)userData.get("address"));

                // iterating address Map
                Iterator<Map.Entry> itr1 = address.entrySet().iterator();
                while (itr1.hasNext()) {
                    Map.Entry pair = itr1.next();
                    System.out.println(pair.getKey() + " : " + pair.getValue());
                }

            } catch (JSONException | IOException e) {
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
