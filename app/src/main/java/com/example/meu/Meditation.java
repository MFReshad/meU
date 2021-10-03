package com.example.meu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.meu.models.Video;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class Meditation extends AppCompatActivity {

    DatabaseReference reference;
    FirebaseUser mUser;

    Intent intent;

    String url,n;
    VideoView videoView;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_meditation);
        videoView = findViewById(R.id.videoView);

        tv = findViewById(R.id.txt);


        intent = getIntent();
        url = intent.getStringExtra("url");
        n = intent.getStringExtra("name");

        tv.setText(n);


        Uri uri =Uri.parse(url);
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();
    }
}