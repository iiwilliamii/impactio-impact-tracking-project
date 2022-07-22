package com.example.impactioproject.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.impactioproject.R;


public class TrackerDetailActivity extends AppCompatActivity {
    private Integer score = 0;
    private String name,sdg;
    private TextView mCurrentPoints, mNewName, mNewSDG;
    private CardView mCardView10, mCardView25, mCardView50, mCardView100, mCardView250;
    private Button returnshop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracker_detail);

        mCurrentPoints = findViewById(R.id.shopCurrentPoints);
        mNewName = findViewById(R.id.test_display);
        mNewSDG = findViewById(R.id.test_display2);
        mCardView10 = findViewById(R.id.cv_10);
        mCardView25 = findViewById(R.id.cv_25);
        mCardView50 = findViewById(R.id.cv_50);
        mCardView100 = findViewById(R.id.cv_100);
        mCardView250 = findViewById(R.id.cv_250);

//        Animation animation = new AnimationUtils().loadAnimation(getApplicationContext(), R.anim.fadein);
//        mCardView10.setAnimation(animation);
//        mCardView25.setAnimation(animation);
//        mCardView50.setAnimation(animation);
//        mCardView100.setAnimation(animation);
//        mCardView250.setAnimation(animation);

        SharedPreferences sp = this.getSharedPreferences("TotalPoints", Context.MODE_PRIVATE);
        score = sp.getInt("score", 0);
        mCurrentPoints.setText("Current Points: " + Integer.toString(score) +" " + "/250");

        SharedPreferences sp2 = this.getSharedPreferences("TrackerName", Context.MODE_PRIVATE);
        name = sp2.getString("name", " ");
        sdg = sp2.getString("sdg", " ");
        mNewName.setText(name);
        mNewSDG.setText(sdg);

        Button btnShop = findViewById(R.id.returnBack);
        btnShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                returnShop();
            }
        });

        Button btnAdd = findViewById(R.id.btn_add_tracker);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchCreateNewTracker();
            }
        });
    }


    private void returnShop(){
        Intent intent = new Intent(TrackerDetailActivity.this, DetailActivity.class);
        startActivity(intent);
    }

    public void add1(View view) {
        score += 25;
        SharedPreferences sp = getSharedPreferences("TotalPoints", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("score", score);
        editor.apply();
        String purchaseSuccess = "Purchase Successful!";
        Toast.makeText(TrackerDetailActivity.this, purchaseSuccess, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(TrackerDetailActivity.this, DetailActivity.class);
        startActivity(intent);
    }
    public void add2(View view) {
        score += 30;
        SharedPreferences sp = getSharedPreferences("TotalPoints", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("score", score);
        editor.apply();
        String purchaseSuccess = "Criteria has been added successfully!";

        Intent intent = new Intent(TrackerDetailActivity.this, DetailActivity.class);
        Toast.makeText(TrackerDetailActivity.this, purchaseSuccess, Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }
    public void add3(View view) {
        score += 50;
        SharedPreferences sp = getSharedPreferences("TotalPoints", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("score", score);
        editor.apply();
        String purchaseSuccess = "Criteria has been added successfully!";

        Intent intent = new Intent(TrackerDetailActivity.this, DetailActivity.class);
        Toast.makeText(TrackerDetailActivity.this, purchaseSuccess, Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }
    public void add4(View view) {
        score += 10;
        SharedPreferences sp = getSharedPreferences("TotalPoints", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("score", score);
        editor.apply();
        String purchaseSuccess = "Criteria has been added successfully!";
        Toast.makeText(TrackerDetailActivity.this, purchaseSuccess, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(TrackerDetailActivity.this, DetailActivity.class);
        startActivity(intent);
    }
    public void add5(View view) {
        score += 75;
        SharedPreferences sp = getSharedPreferences("TotalPoints", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("score", score);
        editor.apply();
        String purchaseSuccess = "Criteria has been added successfully!";
        Toast.makeText(TrackerDetailActivity.this, purchaseSuccess, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(TrackerDetailActivity.this, DetailActivity.class);
        startActivity(intent);
    }

    private void launchCreateNewTracker(){
        Intent intent = new Intent(TrackerDetailActivity.this, TrackerAddActivity.class);
        startActivity(intent);
    }
}