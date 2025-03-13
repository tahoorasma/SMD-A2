package com.example.a2_l215819;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SummaryActivity extends AppCompatActivity {

    private EditText etSummary;
    private Button btnSave;
    private Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        etSummary = findViewById(R.id.etSummary);
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
        String summary = etSummary.getText().toString().trim();
        if (summary.isEmpty()) {
            etSummary.setError("Summary is required");
            return;
        }

        SharedPreferences sharedPreferences = getSharedPreferences("Summary", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("summary", summary);
        editor.apply();

        Toast.makeText(this, "Summary saved successfully", Toast.LENGTH_SHORT).show();
        finish();
    }
}