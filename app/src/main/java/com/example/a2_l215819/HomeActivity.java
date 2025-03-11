package com.example.a2_l215819;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView cvProfilePicture;
    private CardView cvPersonalDetails;
    private CardView cvSummary;
    private CardView cvEducation;
    private CardView cvExperience;
    private CardView cvCertifications;
    private CardView cvReferences;
    private CardView cvPreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        cvProfilePicture = findViewById(R.id.cvProfilePicture);
        cvPersonalDetails = findViewById(R.id.cvPersonalDetails);
        cvSummary = findViewById(R.id.cvSummary);
        cvEducation = findViewById(R.id.cvEducation);
        cvExperience = findViewById(R.id.cvExperience);
        cvCertifications = findViewById(R.id.cvCertifications);
        cvReferences = findViewById(R.id.cvReferences);
        cvPreview = findViewById(R.id.cvPreview);

        cvProfilePicture.setOnClickListener(this);
        cvPersonalDetails.setOnClickListener(this);
        cvSummary.setOnClickListener(this);
        cvEducation.setOnClickListener(this);
        cvExperience.setOnClickListener(this);
        cvCertifications.setOnClickListener(this);
        cvReferences.setOnClickListener(this);
        cvPreview.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;

        int viewId = view.getId();
        if (viewId == R.id.cvProfilePicture) {
            intent = new Intent(this, ProfilePictureActivity.class);
        } else if (viewId == R.id.cvPersonalDetails) {
            intent = new Intent(this, PersonalDetailsActivity.class);
        } else if (viewId == R.id.cvSummary) {
            intent = new Intent(this, SummaryActivity.class);
        } else if (viewId == R.id.cvEducation) {
            intent = new Intent(this, EducationActivity.class);
        } else if (viewId == R.id.cvExperience) {
            intent = new Intent(this, ExperienceActivity.class);
        } else if (viewId == R.id.cvCertifications) {
            intent = new Intent(this, CertificationsActivity.class);
        } else if (viewId == R.id.cvReferences) {
            intent = new Intent(this, ReferencesActivity.class);
        } else if (viewId == R.id.cvPreview) {
            intent = new Intent(this, PreviewActivity.class);
        }

        if (intent != null) {
            startActivity(intent);
        }
    }
}
