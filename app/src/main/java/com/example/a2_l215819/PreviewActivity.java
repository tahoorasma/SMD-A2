package com.example.a2_l215819;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PreviewActivity extends AppCompatActivity {

    private TextView tvName, tvEmail, tvPhone, backBtn, tvSummary, tvEducation, tvExperience;
    private TextView tvDuration, tvCertifications, tvReferneces;
    private ImageView ivProfilePicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);

        tvName = findViewById(R.id.name);
        tvEmail = findViewById(R.id.email);
        tvPhone = findViewById(R.id.phone);
        backBtn = findViewById(R.id.backBtn);
        tvSummary = findViewById(R.id.summary);
        tvEducation = findViewById(R.id.education);
        tvExperience = findViewById(R.id.company_name);
        tvDuration = findViewById(R.id.exp_duration);
        tvCertifications = findViewById(R.id.certifications);
        tvReferneces = findViewById(R.id.references);
        ivProfilePicture = findViewById(R.id.ivProfilePicture);

        SharedPreferences spProfilePicture = getSharedPreferences("ProfilePicture", MODE_PRIVATE);
        String profilePicture = spProfilePicture.getString("profile_picture", null);
        SharedPreferences spUserDetails = getSharedPreferences("UserDetails", MODE_PRIVATE);
        String fullName = spUserDetails.getString("fullName", "Firstname Lastname");
        String email = spUserDetails.getString("email", "name@example.com");
        String phone = spUserDetails.getString("phone", "phoneno.");
        SharedPreferences spSummary = getSharedPreferences("Summary", MODE_PRIVATE);
        String sum = spSummary.getString("summary", "Summary not added yet");
        SharedPreferences spEdu = getSharedPreferences("Education", MODE_PRIVATE);
        String edu = spEdu.getString("edu", "Education not added yet");
        SharedPreferences spExp = getSharedPreferences("Experience", MODE_PRIVATE);
        String exp = spExp.getString("exp", "Experience not added yet");
        String startDt = spExp.getString("startDt", "Start Date");
        String endDt = spExp.getString("endDt", "End Date");
        SharedPreferences spCert = getSharedPreferences("Certifications", MODE_PRIVATE);
        String cert = spCert.getString("cert", "Certifications not added yet");
        SharedPreferences spRef = getSharedPreferences("References", MODE_PRIVATE);
        String ref = spRef.getString("ref", "References not added yet");

        tvName.setText(fullName);
        tvEmail.setText(email);
        tvPhone.setText(phone);
        tvSummary.setText(sum);
        tvEducation.setText(edu);
        tvExperience.setText(exp);
        tvDuration.setText(startDt + " - " + endDt);
        tvCertifications.setText(cert);
        tvReferneces.setText(ref);
        if (profilePicture!= null) {
            try {
                Uri imageUri = Uri.parse(profilePicture);
                if (imageUri != null) {
                    getContentResolver().takePersistableUriPermission(
                            imageUri,
                            Intent.FLAG_GRANT_READ_URI_PERMISSION
                    );
                    ivProfilePicture.setImageURI(imageUri);
                }
            } catch (Exception e) {
                Toast.makeText(this, "Error loading profile picture", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "No profile picture found", Toast.LENGTH_SHORT).show();
        }

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
