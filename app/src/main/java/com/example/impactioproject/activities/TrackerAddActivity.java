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
    private EditText mNameInput,sdgInput, mdescInput;
    private TextView mWarningMessage;
    private String c4name, c4sdg, c4description;
    private Integer score1 = 0;
    private Integer score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_tracker_add);
    Button btnLauncher = findViewById(R.id.addtracker);
    mNameInput = findViewById(R.id.et_nameInput);
    sdgInput = findViewById(R.id.sdg_add);
    mdescInput = findViewById(R.id.et_description);

}
    public void addtracker(View view) {
        score += 50;
        String c4title = "Custom Criteria #1";
        c4name = mNameInput.getText().toString();
        c4sdg = sdgInput.getText().toString();
        c4description = mdescInput.getText().toString();
        SharedPreferences sp2 = getSharedPreferences("TrackerName", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp2.edit();
        editor.putString("c4title", c4title);
        editor.putString("c4name", c4name);
        editor.putString("c4sdg", c4sdg);
        editor.putInt("score", score);
        editor.apply();
        String loginSuccess = "Custom Criteria added successfully!";
        Toast.makeText(TrackerAddActivity.this, loginSuccess, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(TrackerAddActivity.this, DetailActivity.class);
        startActivity(intent);
    }


    private void launchTrackingDetails(){
        Intent intent = new Intent(TrackerAddActivity.this, MainActivity.class);
        startActivity(intent);
    }
}