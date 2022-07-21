package com.example.impactioproject.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.impactioproject.R;

public class TrackerAddActivity extends AppCompatActivity {
    private EditText mNameInput,sdgInput;
    private TextView mWarningMessage;
    private String name, sdg;
    private Integer score1 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_tracker_add);
    Button btnLauncher = findViewById(R.id.addtracker);
    mNameInput = findViewById(R.id.et_nameInput);
    sdgInput = findViewById(R.id.sdg_add);
    mWarningMessage = findViewById(R.id.tv_warning_message);

//        btnLauncher.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            name = mNameInput.getText().toString();
//            if (name.isEmpty()) {
//                String loginFailed = "Please enter a tracker first!";
//                Toast.makeText(TrackerAddActivity.this, loginFailed, Toast.LENGTH_SHORT).show();
//            } else {
//                name = mNameInput.getText().toString();
//                sdg = sdgInput.getText().toString();
//                String loginSuccess = "Tracker added!";
//                Toast.makeText(TrackerAddActivity.this, loginSuccess, Toast.LENGTH_SHORT).show();
//            }
//        }
//    });
}

    public void addtracker(View view) {
        name = mNameInput.getText().toString();
        sdg = sdgInput.getText().toString();
        SharedPreferences sp2 = getSharedPreferences("TrackerName", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp2.edit();
        editor.putString("name", name);
        editor.putString("sdg", sdg);
        editor.apply();
        String loginSuccess = "Tracker added!";
        Toast.makeText(TrackerAddActivity.this, loginSuccess, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(TrackerAddActivity.this, TrackerDetailActivity.class);
        startActivity(intent);
    }


    private void launchTrackingDetails(){
        Intent intent = new Intent(TrackerAddActivity.this, MainActivity.class);
        startActivity(intent);
    }
}