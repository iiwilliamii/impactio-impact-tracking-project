package com.example.impactioproject.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.impactioproject.R;
import com.example.impactioproject.activities.LoginActivityMain;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void launchLoginActivity() {
        Intent intent = new Intent(this, LoginActivityMain.class);
        startActivity(intent);
    }
}