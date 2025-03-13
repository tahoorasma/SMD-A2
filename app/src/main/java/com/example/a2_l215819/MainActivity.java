package com.example.a2_l215819;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.os.Handler;
import android.os.Looper;
import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ImageView logo = findViewById(R.id.logo);
        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.logo_fade_in);
        logo.startAnimation(fadeIn);
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }, 3000);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.splash), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        SharedPreferences spProfPic = getSharedPreferences("ProfilePicture", MODE_PRIVATE);
        SharedPreferences.Editor ppEditor = spProfPic.edit();
        ppEditor.clear();
        ppEditor.apply();
        SharedPreferences spUserDetails = getSharedPreferences("UserDetails", MODE_PRIVATE);
        SharedPreferences.Editor udEditor = spUserDetails.edit();
        udEditor.clear();
        udEditor.apply();
        SharedPreferences spSummary = getSharedPreferences("Summary", MODE_PRIVATE);
        SharedPreferences.Editor sEditor = spSummary.edit();
        sEditor.clear();
        sEditor.apply();
        SharedPreferences spEdu = getSharedPreferences("Education", MODE_PRIVATE);
        SharedPreferences.Editor eduEditor = spEdu.edit();
        eduEditor.clear();
        eduEditor.apply();
        SharedPreferences spExp = getSharedPreferences("Experience", MODE_PRIVATE);
        SharedPreferences.Editor expEditor = spExp.edit();
        expEditor.clear();
        expEditor.apply();
        SharedPreferences spCert = getSharedPreferences("Certifications", MODE_PRIVATE);
        SharedPreferences.Editor cEditor = spCert.edit();
        cEditor.clear();
        cEditor.apply();
        SharedPreferences spRef = getSharedPreferences("References", MODE_PRIVATE);
        SharedPreferences.Editor rEditor = spRef.edit();
        rEditor.clear();
        rEditor.apply();
    }
}