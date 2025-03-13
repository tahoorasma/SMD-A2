package com.example.a2_l215819;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class ProfilePictureActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_READ_EXTERNAL_STORAGE = 1;
    private ImageView ivProfilePicture;
    private Button btnChooseImage;
    private Button btnSave;
    private Button btnCancel;
    private Uri selectedImageUri = null;
    private final ActivityResultLauncher<Intent> galleryLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Uri imageUri = result.getData().getData();
                    if (imageUri != null) {
                        getContentResolver().takePersistableUriPermission(
                                imageUri,
                                Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                        );

                        SharedPreferences.Editor editor = getSharedPreferences("ProfilePicture", MODE_PRIVATE).edit();
                        editor.putString("profile_picture", imageUri.toString());
                        editor.apply();

                        Log.d("ProfilePictureActivity", "Saved Image URI: " + imageUri);
                        Log.d("ProfilePictureActivity", "Persisted Permissions Count: " + getContentResolver().getPersistedUriPermissions().size());

                        ivProfilePicture.setImageURI(imageUri);
                        selectedImageUri = imageUri;
                    }
                }
            }
    );


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_picture);

        ivProfilePicture = findViewById(R.id.ivProfilePicture);
        btnChooseImage = findViewById(R.id.btnChooseImage);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);

        loadExistingImage();

        btnChooseImage.setOnClickListener(view -> checkPermissionAndOpenGallery());

        btnSave.setOnClickListener(view -> saveProfilePicture());

        btnCancel.setOnClickListener(view -> finish());
    }

    private void loadExistingImage() {
        SharedPreferences prefs = getSharedPreferences("CVData", MODE_PRIVATE);
        String imageUriString = prefs.getString("profile_picture_uri", null);

        if (imageUriString != null) {
            try {
                selectedImageUri = Uri.parse(imageUriString);
                ivProfilePicture.setImageURI(selectedImageUri);
            } catch (Exception e) {
                Toast.makeText(this, "Error loading saved image", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void checkPermissionAndOpenGallery() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_IMAGES)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_MEDIA_IMAGES}, PERMISSION_REQUEST_READ_EXTERNAL_STORAGE);
            } else {
                openGallery();
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) { // Android 13
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_IMAGES)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_MEDIA_IMAGES}, PERMISSION_REQUEST_READ_EXTERNAL_STORAGE);
            } else {
                openGallery();
            }
        } else {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_READ_EXTERNAL_STORAGE);
            } else {
                openGallery();
            }
        }
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("image/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        galleryLauncher.launch(intent);
    }

    private void saveProfilePicture() {
        if (selectedImageUri != null) {
            SharedPreferences.Editor editor = getSharedPreferences("ProfilePicture", MODE_PRIVATE).edit();
            editor.putString("profile_picture", selectedImageUri.toString());
            editor.apply();

            Toast.makeText(this, "Profile picture saved successfully", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Please select an image first", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_REQUEST_READ_EXTERNAL_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery();
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
                if (shouldShowRequestPermissionRationale(Manifest.permission.READ_MEDIA_IMAGES)) {
                    Toast.makeText(this, "You need to allow access to select a profile picture", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Permission denied. Enable it in settings.", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    intent.setData(Uri.parse("package:" + getPackageName()));
                    startActivity(intent);
                }
            } else {
                Toast.makeText(this, "Permission denied. Cannot access gallery.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}