package com.example.impactioproject.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
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

import com.bumptech.glide.Glide;
import com.example.impactioproject.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProjectUpdateActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    private Button mPopup;
    Dialog popAddPost;
    private ImageView mProfile, mSend;
    private TextView mTitle, mDescription;
    private ProgressBar mClickProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_update);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        iniPopup();

        mPopup = findViewById(R.id.btn_popup);
        mPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popAddPost.show();
            }
        });


    }


    private void iniPopup() {
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

        Glide.with(ProjectUpdateActivity.this).load(currentUser.getPhotoUrl()).into(mProfile);

        mSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSend.setVisibility(View.INVISIBLE);
                mClickProgress.setVisibility(View.VISIBLE);

                if (!mTitle.getText().toString().isEmpty() && !mDescription.getText().toString().isEmpty()) {

                }else {
                    showMessage("Please fill in everything before sending!");
                    mSend.setVisibility(View.VISIBLE);
                    mClickProgress.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    private void showMessage(String message) {

        Toast.makeText(ProjectUpdateActivity.this,message,Toast.LENGTH_LONG).show();

    }
}