package com.mobile.androidapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.mobile.androidapp.R;
import com.mobile.androidapp.repository.UserRepository;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}