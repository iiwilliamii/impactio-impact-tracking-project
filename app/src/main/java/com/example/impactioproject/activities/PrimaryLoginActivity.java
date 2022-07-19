package com.example.impactioproject.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.impactioproject.R;
import com.example.impactioproject.projects.ProjectAdapter;
import com.example.impactioproject.projects.Projects;

public class PrimaryLoginActivity extends AppCompatActivity {
    private TextView mName;
    private Button mLogin;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView mRecyclerView;
    private ProjectAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primary_login);

        mLogin = findViewById(R.id.loginBtn);
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchLoginActivity();
            }
        });
    }

    private void launchLoginActivity() {
        Intent intent = new Intent(this, LoginActivityMain.class);
        startActivity(intent);
    }
}