package com.example.impactioproject.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.impactioproject.R;

public class DetailActivity extends AppCompatActivity {

    private Button mBtnActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mBtnActivity = findViewById(R.id.btn_activity);
        mBtnActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchProjectUpdates();
            }
        });
    }

    private void launchProjectUpdates() {
        Intent intent = new Intent(this, ProjectUpdateActivity.class);
        startActivity(intent);

    }
}