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
import com.example.impactioproject.PostModels.Posts;
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
    private String name,sdg;
    private TextView mDisplayTitle, mDisplayPoints, mDisplayDescription, mNewSDG;
    private ProgressBar mClickProgress;
    private Integer score = 0;
    private Integer score2 = 0;
    private Projects project;
    private ImageView mProfile, mSend;
    private  TextView mTitle, mDescription;
    private TextView mC1name,mC1sdg, mC1description;
    private String c1name,c1sdg,c1description;
    private TextView mC2name,mC2sdg, mC2description;
    private String c2name,c2sdg,c2description;
    private TextView mC3name,mC3sdg, mC3description;
    private String c3name,c3sdg,c3description;
    private TextView mc4Title,mC4name, mC4sdg, mC4description;
    private String c4title,c4name,c4sdg,c4description;
    Dialog popAddPost;

    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    List<Posts> postsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        mDisplayPoints = findViewById(R.id.tv_tracker_points);
        mDisplayTitle = findViewById(R.id.tv_funding_title);
        mBtnPost = findViewById(R.id.btn_post_activity);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        //Criteria's
        mC1name = findViewById(R.id.c1_display_name);
        mC1sdg = findViewById(R.id.c1_display_sdg);
        mC1description = findViewById(R.id.c1_display_description);
        mC2name = findViewById(R.id.c2_display_name);
        mC2sdg = findViewById(R.id.c2_display_sdg);
        mC2description = findViewById(R.id.c2_display_description);
        mC3name = findViewById(R.id.c3_display_name);
        mC3sdg = findViewById(R.id.c3_display_sdg);
        mC3description = findViewById(R.id.c3_display_description);
        mc4Title = findViewById(R.id.c4_title);
        mC4name = findViewById(R.id.c4_display_name);
        mC4sdg = findViewById(R.id.c4_display_sdg);
        mC4description = findViewById(R.id.c4_display_description);

        Intent intent = getIntent();
        String projectSymbolId = intent.getStringExtra("projectSymbol");
        project = Projects.findProjects(projectSymbolId);
        if (project != null) {
            mDisplayTitle.setText(project.getName());
            mDisplayDescription.setText(project.getSymbol());
        }

        SharedPreferences sp = this.getSharedPreferences("TotalPoints", Context.MODE_PRIVATE);
        score = sp.getInt("score", 0);
        c1name = sp.getString("c1name", " ");
        c1sdg = sp.getString("c1sdg", " ");
        c1description = sp.getString("c1description", " ");
        c2name = sp.getString("c2name", " ");
        c2sdg = sp.getString("c2sdg", " ");
        c2description = sp.getString("c2description", " ");
        c3name = sp.getString("c3name", " ");
        c3sdg = sp.getString("c3sdg", " ");
        c3description = sp.getString("c3description", " ");
        mDisplayPoints.setText(Integer.toString(score));
        mC1name.setText(c1name);
        mC1sdg.setText(c1sdg);
        mC1description.setText(c1description);
        mC2name.setText(c2name);
        mC2sdg.setText(c2sdg);
        mC2description.setText(c2description);
        mC3name.setText(c3name);
        mC3sdg.setText(c3sdg);
        mC3description.setText(c3description);

        SharedPreferences sp2 = this.getSharedPreferences("TrackerName", Context.MODE_PRIVATE);
        String c4title = sp2.getString("c4title", " ");
        String c4name = sp2.getString("c4name", " ");
        String c4description = sp2.getString("c4description", " ");
        String c4sdg = sp2.getString("c4sdg", " ");
        score2 = sp2.getInt("score", 0);
        mDisplayPoints.setText(Integer.toString(score));
        mc4Title.setText(c4title);
        mC4name.setText(c4name);
        mC4sdg.setText(c4sdg);
        mC4description.setText(c4description);


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
        mTitle = popAddPost.findViewById(R.id.edit_Funding_Title);
        mDescription = popAddPost.findViewById(R.id.edit_Funding_Description);
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

                    Posts posts = new Posts(mTitle.getText().toString(), mDescription.getText().toString(), projectSymbol, currentUser.getUid());
                    addPost(posts, projectSymbol);

                }else {
                    showMessage("Please fill in everything before sending!");
                    mSend.setVisibility(View.VISIBLE);
                    mClickProgress.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    private void addPost(Posts posts, String projectSymbol) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Posts").push();

        //get posts uID and update posts key
        String key = myRef.getKey();
        posts.setPostKey(key);

        myRef.setValue(posts).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                showMessage("Posts Added successfully");
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