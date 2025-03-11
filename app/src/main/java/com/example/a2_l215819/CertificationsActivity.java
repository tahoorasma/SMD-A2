package com.example.a2_l215819;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CertificationsActivity extends AppCompatActivity {

    private EditText etCertifications;
    private Button btnSave;
    private Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certifications);

        etCertifications = findViewById(R.id.etCertifications);
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
        if (etCertifications.getText().toString().trim().isEmpty()) {
            etCertifications.setError("Certification is required");
            return;
        }

        Toast.makeText(this, "Certifications saved successfully", Toast.LENGTH_SHORT).show();
        finish();
    }
}