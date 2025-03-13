package com.example.a2_l215819;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EducationActivity extends AppCompatActivity {

    private EditText etEducation;
    private Button btnSave;
    private Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education);

        etEducation = findViewById(R.id.etEducation);
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
        String edu = etEducation.getText().toString().trim();
        if (edu.isEmpty()) {
            etEducation.setError("Education is required");
            return;
        }

        SharedPreferences sharedPreferences = getSharedPreferences("Education", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("edu", edu);
        editor.apply();

        Toast.makeText(this, "Education saved successfully", Toast.LENGTH_SHORT).show();
        finish();
    }
}