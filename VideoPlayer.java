package com.example.videoplayerapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class VideoPlayer extends AppCompatActivity {
    private static final int REQUEST_VIDEO_CAPTURE = 100;
    private static final int REQUEST_CAMERA_PERMISSION = 200;

    private Button b1;
    private VideoView v1;
    private MediaController m1;
    private Uri u1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  // Ensure activity_main.xml is present in your project

        // Initialize UI components
        b1 = findViewById(R.id.button);
        v1 = findViewById(R.id.videoView);
        m1 = new MediaController(this);

        // Set up button click listener
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Check for camera permission
                if (ContextCompat.checkSelfPermission(VideoPlayer.this, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    // Request camera permission
                    ActivityCompat.requestPermissions(VideoPlayer.this,
                            new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
                } else {
                    // Start video capture intent
                    Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                    startActivityForResult(intent, REQUEST_VIDEO_CAPTURE);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
            u1 = data.getData();
            if (u1 != null) {
                v1.setVideoURI(u1);
                v1.setMediaController(m1);
                m1.setAnchorView(v1);
                v1.start();
            }
        } else {
            Toast.makeText(VideoPlayer.this, "Camera is not working", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, start video capture intent
                Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                startActivityForResult(intent, REQUEST_VIDEO_CAPTURE);
            } else {
                Toast.makeText(this, "Camera permission is required to use the camera", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
