package com.example.a2_l215819;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ReferencesActivity extends AppCompatActivity {

    private EditText etReferences;
    private Button btnSave;
    private Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_references);

        etReferences = findViewById(R.id.etReferences);
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
        String ref = etReferences.getText().toString().trim();
        if (ref.isEmpty()) {
            etReferences.setError("References required");
            return;
        }

        SharedPreferences sharedPreferences = getSharedPreferences("References", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("ref", ref);
        editor.apply();

        Toast.makeText(this, "References saved successfully", Toast.LENGTH_SHORT).show();
        finish();
    }
}