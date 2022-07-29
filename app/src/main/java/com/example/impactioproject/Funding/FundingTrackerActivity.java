package com.example.impactioproject.Funding;

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
import com.example.impactioproject.R;
import com.example.impactioproject.activities.ProjectUpdateActivity;
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

public class FundingTrackerActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    private FloatingActionButton mPopup;
    Dialog popAddPost;
    private ImageView mProfile, mSend;
    private TextView mTitle, mDescription, mAmount, mTestSymbol;
    private ProgressBar mClickProgress;
    private RecyclerView mRecyclerView;
    private FundingAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    List<Fundings> fundingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_funding_tracker);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        mRecyclerView = findViewById(R.id.rv_fundings_list);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        Intent intent = getIntent();
        String projectSymbol = intent.getStringExtra("projectSymbol");

        iniPopup(projectSymbol);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("FundingPost" + projectSymbol);

        mPopup = findViewById(R.id.add_funding);
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
                fundingList = new ArrayList<>();
                for (DataSnapshot fundingSnap: snapshot.getChildren()) {
                    Fundings funding = fundingSnap.getValue(Fundings.class);
                    fundingList.add(funding);
                }

                FundingAdapter.RecyclerViewListener listener = new FundingAdapter.RecyclerViewListener() {
                    @Override
                    public void onClick(View view, String Symbol) {
                        //TODO: make something happen when you click this
                    }
                };
                mAdapter = new FundingAdapter(listener, fundingList);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void iniPopup(String projectSymbol) {
        popAddPost = new Dialog(this);
        popAddPost.setContentView(R.layout.popup_add_funding);
        popAddPost.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popAddPost.getWindow().setLayout(Toolbar.LayoutParams.MATCH_PARENT,Toolbar.LayoutParams.WRAP_CONTENT);
        popAddPost.getWindow().getAttributes().gravity = Gravity.TOP;

        mAmount = popAddPost.findViewById(R.id.edit_funding_amount);
        mProfile = popAddPost.findViewById(R.id.iv_avatar);
        mTitle = popAddPost.findViewById(R.id.edit_Funding_Title);
        mDescription = popAddPost.findViewById(R.id.edit_Funding_Description);
        mClickProgress = popAddPost.findViewById(R.id.progressBar);
        mSend = popAddPost.findViewById(R.id.iv_send);
        mClickProgress.setVisibility(View.INVISIBLE);

        Glide.with(FundingTrackerActivity.this).load(currentUser.getPhotoUrl()).into(mProfile);

        mSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSend.setVisibility(View.INVISIBLE);
                mClickProgress.setVisibility(View.VISIBLE);

                if (!mTitle.getText().toString().isEmpty() && !mDescription.getText().toString().isEmpty()) {

                    Fundings funding = new Fundings(mTitle.getText().toString(), mAmount.getText().toString(), mDescription.getText().toString(), currentUser.getUid(), projectSymbol);


                }else {
                    showMessage("Please fill in everything before sending!");
                    mSend.setVisibility(View.VISIBLE);
                    mClickProgress.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    private void addPost(Fundings funding, String projectSymbol) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("FundingPost" + projectSymbol).push();

        //get posts uID and update posts key
        String key = myRef.getKey();
        funding.setPostKey(key);

        myRef.setValue(funding).addOnSuccessListener(new OnSuccessListener<Void>() {
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

        Toast.makeText(FundingTrackerActivity.this,message,Toast.LENGTH_LONG).show();

    }
}