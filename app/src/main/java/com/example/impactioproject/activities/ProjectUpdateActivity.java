package com.example.impactioproject.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.impactioproject.PostModels.Posts;
import com.example.impactioproject.PostModels.PostAdapter;
import com.example.impactioproject.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ProjectUpdateActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    private FloatingActionButton mPopup;
    Dialog popAddPost;
    private ImageView mProfile, mSend;
    private TextView mTitle, mDescription, mTestSymbol;
    private ProgressBar mClickProgress;
    private RecyclerView mRecyclerView;
    private PostAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    List<Posts> postsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_update);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        mRecyclerView = findViewById(R.id.rv_post_list);
        mTestSymbol = findViewById(R.id.tv_testSymbol);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        Intent intent = getIntent();
        String projectSymbol = intent.getStringExtra("projectSymbol");
        mTestSymbol.setText(projectSymbol);

        iniPopup(projectSymbol);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Posts" + projectSymbol);

        mPopup = findViewById(R.id.floatingActionButton);
        mPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popAddPost.show();
            }
        });


    }

    @Override
    public void onStart() {
        super.onStart();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                postsList = new ArrayList<>();
                for (DataSnapshot postSnap: snapshot.getChildren()) {
                    Posts posts = postSnap.getValue(Posts.class);
                    postsList.add(posts);
                }

                PostAdapter.RecyclerViewListener listener = new PostAdapter.RecyclerViewListener() {
                    @Override
                    public void onClick(View view, String Symbol) {
                        //TODO: make something happen when you click this
                    }
                };
                mAdapter = new PostAdapter(listener, postsList);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

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

        Glide.with(ProjectUpdateActivity.this).load(currentUser.getPhotoUrl()).into(mProfile);

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
        DatabaseReference myRef = database.getReference("Posts" + projectSymbol).push();

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

        Toast.makeText(ProjectUpdateActivity.this,message,Toast.LENGTH_LONG).show();

    }
}