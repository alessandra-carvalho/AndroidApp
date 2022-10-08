package com.mobile.androidapp.view;

import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.mobile.androidapp.R;
import com.mobile.androidapp.repository.UserRepository;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // chama activity main
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("TAG", "onCreate: antes do getInstance");
        UserRepository.getInstance(this);
        Log.e("TAG", "onCreate: depois do getInstance " + UserRepository.getInstance(this).getUsers().size());


        setContentView(R.layout.activity_main);
    }
}