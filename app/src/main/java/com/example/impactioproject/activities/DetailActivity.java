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
import com.example.impactioproject.projects.Projects;

public class DetailActivity extends AppCompatActivity {

    private Button mBtnActivity;
    private TextView mDisplayTitle, mDisplayPoints;
    private Integer score = 0;
    private Projects project;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        mDisplayPoints = findViewById(R.id.tv_tracker_points);
        mDisplayTitle = findViewById(R.id.tv_detail_title);

        Intent intent = getIntent();
        String projectSymbolId = intent.getStringExtra("Symbol");
        project = Projects.findProjects(projectSymbolId);
        if (project != null) {
            mDisplayTitle.setText(project.getName());
        }

        SharedPreferences sp = this.getSharedPreferences("TotalPoints", Context.MODE_PRIVATE);
        score = sp.getInt("score", 0);
        mDisplayPoints.setText(Integer.toString(score));

        mBtnActivity = findViewById(R.id.btn_activity);
        mBtnActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchProjectUpdates(projectSymbolId);
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

    private void launchProjectUpdates(String projectSymbol) {
        Intent intent = new Intent(this, ProjectUpdateActivity.class);
        intent.putExtra("projectSymbol", projectSymbol);
        startActivity(intent);
    }

    private void launchProjectTrackers(){
        Intent intent = new Intent(DetailActivity.this, TrackerDetailActivity.class);
        startActivity(intent);
    }

    private void launchProjectFunding(){
        Intent intent = new Intent(DetailActivity.this, TrackerDetailActivity.class);
        startActivity(intent);
    }
}