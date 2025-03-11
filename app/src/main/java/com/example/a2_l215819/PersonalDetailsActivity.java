package com.example.a2_l215819;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PersonalDetailsActivity extends AppCompatActivity {

    private EditText etFullName;
    private EditText etEmail;
    private EditText etPhone;
    private Button btnSave;
    private Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);

        etFullName = findViewById(R.id.etFullName);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePersonalDetails();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void savePersonalDetails() {
        if (etFullName.getText().toString().trim().isEmpty()) {
            etFullName.setError("Full name is required");
            return;
        }

        if (etEmail.getText().toString().trim().isEmpty()) {
            etEmail.setError("Email is required");
            return;
        }

        if (etPhone.getText().toString().trim().isEmpty()) {
            etPhone.setError("Phone no. is required");
            return;
        }

        if (etPhone.getText().toString().trim().length()!=11) {
            etPhone.setError("Invalid Phone no");
            return;
        }

        Toast.makeText(this, "Personal details saved successfully", Toast.LENGTH_SHORT).show();
        finish();
    }
}