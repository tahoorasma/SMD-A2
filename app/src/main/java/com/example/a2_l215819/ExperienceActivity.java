package com.example.a2_l215819;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ExperienceActivity extends AppCompatActivity {

    private EditText etExperience, etStartDate, etEndDate;
    private Button btnSave;
    private Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience);

        etExperience = findViewById(R.id.etExperience);
        etStartDate = findViewById(R.id.etStartDate);
        etEndDate = findViewById(R.id.etEndDate);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void save() {
        String exp = etExperience.getText().toString().trim();
        String startDt = etStartDate.getText().toString().trim();
        String endDt = etEndDate.getText().toString().trim();
        if (exp.isEmpty()) {
            etExperience.setError("Experience is required");
            return;
        }

        if (startDt.isEmpty()) {
            etStartDate.setError("Start date is required");
            return;
        }

        if (endDt.isEmpty()) {
            etEndDate.setText("Present");
            return;
        }

        SharedPreferences sharedPreferences = getSharedPreferences("Experience", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("exp", exp);
        editor.putString("startDt", startDt);
        editor.putString("endDt", endDt);
        editor.apply();

        Toast.makeText(this, "Experience saved successfully", Toast.LENGTH_SHORT).show();
        finish();
    }
}