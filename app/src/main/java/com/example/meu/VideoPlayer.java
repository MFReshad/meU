package com.example.meu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class VideoPlayer extends AppCompatActivity {

    DatabaseReference reference;
    FirebaseUser mUser;

    Intent intent;

    String url, n;
    VideoView videoView;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_video_player);

        videoView = findViewById(R.id.videoView);

        tv = findViewById(R.id.txtt);


        intent = getIntent();
        url = intent.getStringExtra("url");


        Uri uri = Uri.parse(url);
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();




    }


}

