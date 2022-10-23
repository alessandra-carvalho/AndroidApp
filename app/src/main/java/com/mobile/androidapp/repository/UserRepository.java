package com.mobile.androidapp.repository;

import android.content.Context;
import android.util.Log;

import androidx.viewpager.widget.ViewPager;

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
import java.nio.channels.InterruptedByTimeoutException;
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

    @Override
    public void onResponse(JSONArray response) {

        Log.e(TAG, "onResponse: "+response.length());
        for (int i = 0; i < response.length(); i++) {
            try {
                JSONObject json = response.getJSONObject(i);
                Log.d(TAG, "onResponse: "+json.toString());

                // https://www.geeksforgeeks.org/parse-json-java/
                //https://www.digitalocean.com/community/tutorials/jackson-json-java-parser-api-example-tutorial
                // typecasting obj to JSONObject
                JSONObject jo = response.getJSONObject(i);

                // getting personal data
                String id = (String) jo.getString("id");
                int intId = Integer.parseInt(id);
                String name = (String) jo.getString("name");
                String userName = (String) jo.getString("username");
                String email = (String) jo.getString("email");
                String phone = (String) jo.getString("phone");
                String website = (String) jo.getString("website");

                String street = "";
                String suite = "";
                String city = "";

                String companyName = "";
                String companyCatchPhrase = "";
                String companyBs = "";


                // printing to test
                System.out.println("Id: " +id);
                System.out.println("Name: " +name);
                System.out.println("Phone: " +phone);
                System.out.println("Website: " +website);

                // getting address
                ObjectMapper objMapperAddress = new ObjectMapper();
                Map<String, Object> addressData = objMapperAddress.readValue(
                        String.valueOf(json), new TypeReference<Map<String, Object>>() {
                        });
                Map address = ((Map)addressData.get("address"));

                // iterating address Map
                Iterator<Map.Entry> itrAddress = address.entrySet().iterator();
                while (itrAddress.hasNext()) {
                    Map.Entry pair = itrAddress.next();
                    //System.out.println(pair.getKey() + " : " + pair.getValue());

                    if (pair.getKey() == "street"){
                        street = (String) pair.getValue();
                    } else if (pair.getKey() == "suite") {
                        suite = (String)  pair.getValue();
                    } else if (pair.getKey() == "city") {
                        city = (String)  pair.getValue();
                    }

                }

                // create object AdressGeo (NAO ROLOU)
                AddressGeo objAddressGeo = new AddressGeo("lat: NAO CONSEGUI ler a collectation interna de GEO ", "lng: NAO CONSEGUI ler a collectation interna de GEO");

                // create object Adress (Success!)
                Address objAddress = new Address(street, suite, city, objAddressGeo);

                // getting geo
                /*ObjectMapper objMapperGeo = new ObjectMapper();
                Map<String, Object> geoData = objMapperGeo.readValue(
                        String.valueOf(json), new TypeReference<Map<String, Object>>() {
                        });
                Map geo = ((Map)geoData.get("geo"));

                // iterating address Map
                Iterator<Map.Entry> itrGeo = geo.entrySet().iterator();
                while (itrGeo.hasNext()) {
                    Map.Entry pair = itrGeo.next();
                    System.out.println(pair.getKey() + " : " + pair.getValue());
                }
                */

                // getting company
                ObjectMapper objMapperCompany = new ObjectMapper();
                Map<String, Object> companyData = objMapperCompany.readValue(
                        String.valueOf(json), new TypeReference<Map<String, Object>>() {
                        });
                Map company = ((Map)companyData.get("company"));

                // iterating company Map
                Iterator<Map.Entry> itrCompany = company.entrySet().iterator();
                while (itrCompany.hasNext()) {
                    Map.Entry pair = itrCompany.next();
                    //System.out.println(pair.getKey() + " : " + pair.getValue());

                    if (pair.getKey() == "name"){
                        companyName = (String) pair.getValue();
                    } else if (pair.getKey() == "catchPhrase") {
                        companyCatchPhrase = (String)  pair.getValue();
                    } else if (pair.getKey() == "bs") {
                        companyBs = (String)  pair.getValue();
                    }
                }

                // create object Company (Success!)
                Company objCompany = new Company(companyName, companyCatchPhrase, companyBs);

                // create object User (Success!)
                users.add(new User(intId, name, userName, email, objAddress, phone, website, objCompany));


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
