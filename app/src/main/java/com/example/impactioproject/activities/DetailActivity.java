package com.example.impactioproject.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.impactioproject.Funding.FundingTrackerActivity;
import com.bumptech.glide.Glide;
import com.example.impactioproject.PostModels.Post;
import com.example.impactioproject.R;
import com.example.impactioproject.projects.Projects;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private Button mBtnActivity, mBtnPost;
    private TextView mDisplayTitle, mDisplayPoints, mDisplayDescription;
    private ProgressBar mClickProgress;
    private Integer score = 0;
    private Projects project;
    private ImageView mProfile, mSend;
    private  TextView mTitle, mDescription;
    Dialog popAddPost;

    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    List<Post> postList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        mDisplayPoints = findViewById(R.id.tv_tracker_points);
        mDisplayTitle = findViewById(R.id.tv_detail_title);
        mBtnPost = findViewById(R.id.btn_post_activity);
        mDisplayDescription = findViewById(R.id.tv_project_detail);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        Intent intent = getIntent();
        String projectSymbolId = intent.getStringExtra("projectSymbol");
        project = Projects.findProjects(projectSymbolId);
        if (project != null) {
            mDisplayTitle.setText(project.getName());
            mDisplayDescription.setText(project.getSymbol());
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

        Button btnFunding = findViewById(R.id.btn_timeline);
        btnFunding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchProjectFunding();
            }
        });

        mBtnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popAddPost.show();
            }
        });

        iniPopup(projectSymbolId);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Posts");

    }

    private void iniPopup(String projectSymbol) {
        popAddPost = new Dialog(this);
        popAddPost.setContentView(R.layout.popup_add_post);
        popAddPost.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popAddPost.getWindow().setLayout(Toolbar.LayoutParams.MATCH_PARENT,Toolbar.LayoutParams.WRAP_CONTENT);
        popAddPost.getWindow().getAttributes().gravity = Gravity.TOP;

        mProfile = popAddPost.findViewById(R.id.iv_avatar);
        mTitle = popAddPost.findViewById(R.id.editTitle);
        mDescription = popAddPost.findViewById(R.id.editDescription);
        mClickProgress = popAddPost.findViewById(R.id.progressBar);
        mSend = popAddPost.findViewById(R.id.iv_send);
        mClickProgress.setVisibility(View.INVISIBLE);

        Glide.with(DetailActivity.this).load(currentUser.getPhotoUrl()).into(mProfile);

        mSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSend.setVisibility(View.INVISIBLE);
                mClickProgress.setVisibility(View.VISIBLE);

                if (!mTitle.getText().toString().isEmpty() && !mDescription.getText().toString().isEmpty()) {

                    Post post = new Post(mTitle.getText().toString(), mDescription.getText().toString(), projectSymbol, currentUser.getUid());
                    addPost(post, projectSymbol);

                }else {
                    showMessage("Please fill in everything before sending!");
                    mSend.setVisibility(View.VISIBLE);
                    mClickProgress.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    private void addPost(Post post, String projectSymbol) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Posts").push();

        //get post uID and update post key
        String key = myRef.getKey();
        post.setPostKey(key);

        myRef.setValue(post).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                showMessage("Post Added successfully");
                mClickProgress.setVisibility(View.INVISIBLE);
                mSend.setVisibility(View.VISIBLE);
                popAddPost.dismiss();
            }
        });
    }

    private void showMessage(String message) {
        Toast.makeText(DetailActivity.this,message,Toast.LENGTH_LONG).show();
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
        Intent intent = new Intent(DetailActivity.this, FundingTrackerActivity.class);
        startActivity(intent);
    }
}