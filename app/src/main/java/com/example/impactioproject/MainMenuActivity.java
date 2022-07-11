package com.example.impactioproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainMenuActivity extends AppCompatActivity {
    private TextView mName;
    private Button mProfile;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView mRecyclerView;
    private ProjectAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        mName = findViewById(R.id.tv_name);
        mProfile = findViewById(R.id.btn_profile);
        mRecyclerView = findViewById(R.id.rv_list);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        ProjectAdapter.RecyclerViewClickListener listener = new ProjectAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, String projectSymbol) {
                launchDetailActivity(projectSymbol);
            }
        };

        mAdapter = new ProjectAdapter(Projects.getProjects(), listener);
//        mAdapter.sort(ProjectAdapter.SORT_METHOD_NAME);
        mRecyclerView.setAdapter(mAdapter);
        mProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchProfileActivity();
            }
        });
    }


    private void launchProfileActivity() {
        Intent intent = new Intent(MainMenuActivity.this, ProfileActivity.class);
        startActivity(intent);
    }

    private void launchDetailActivity(String projectSymbol) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("Symbol", projectSymbol);
        startActivity(intent);
    }
}