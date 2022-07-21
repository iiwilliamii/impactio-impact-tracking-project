package com.example.impactioproject.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.impactioproject.R;

public class DetailActivity extends AppCompatActivity {

    private Button mBtnActivity;
    private TextView mDisplayName, mDisplayPoints;
    private Integer score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        mDisplayPoints = findViewById(R.id.tv_tracker_points);

        SharedPreferences sp = this.getSharedPreferences("TotalPoints", Context.MODE_PRIVATE);
        score = sp.getInt("score", 0);
        mDisplayPoints.setText(Integer.toString(score));

        mBtnActivity = findViewById(R.id.btn_activity);
        mBtnActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchProjectUpdates();
            }
        });

        Button btnFAQ = findViewById(R.id.btn_update_tracking);
        btnFAQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchProjectTrackers();
            }
        });
    }

    private void launchProjectUpdates() {
        Intent intent = new Intent(this, ProjectUpdateActivity.class);
        startActivity(intent);

    }

    private void launchProjectTrackers(){
        Intent intent = new Intent(DetailActivity.this, TrackerDetailActivity.class);
        startActivity(intent);
    }
}