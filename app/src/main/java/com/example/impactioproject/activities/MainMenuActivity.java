package com.example.impactioproject.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.impactioproject.projects.ProjectAdapter;
import com.example.impactioproject.projects.Projects;
import com.example.impactioproject.R;

public class MainMenuActivity extends AppCompatActivity {
    private TextView mName;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView mRecyclerView;
    private ProjectAdapter mAdapter;
    private ImageView mProfilePage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        mName = findViewById(R.id.tv_name);
        mRecyclerView = findViewById(R.id.rv_project_list);
        mProfilePage = findViewById(R.id.iv_profile_page);
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

        mProfilePage.setOnClickListener(new View.OnClickListener() {
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
        intent.putExtra("projectSymbol", projectSymbol);
        startActivity(intent);
    }
}