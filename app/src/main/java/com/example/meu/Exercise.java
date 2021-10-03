package com.example.meu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.meu.models.Video;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class Exercise extends AppCompatActivity {

    DatabaseReference reference;
    FirebaseUser mUser;
    String url,n,name;
    ImageView i1,i2,i3,i4,i5,i6,i7,i8,i9,i10,i11,i12,i13;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        i1 = findViewById(R.id.imageView1);
        i2 = findViewById(R.id.imageView2);
        i3 = findViewById(R.id.imageView3);
        i4 = findViewById(R.id.imageView4);
        i5 = findViewById(R.id.imageView5);
        i6 = findViewById(R.id.imageView6);
        i7 = findViewById(R.id.imageView7);
        i8 = findViewById(R.id.imageView8);
        i9 = findViewById(R.id.imageView9);
        i10 = findViewById(R.id.imageView10);
        i11 = findViewById(R.id.imageView11);
        i12 = findViewById(R.id.imageView12);
        i13 = findViewById(R.id.imageView13);


        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url = "https://firebasestorage.googleapis.com/v0/b/my-project-1565721779873.appspot.com/o/1.mp4?alt=media&token=948f9387-dc64-4aec-a74c-7421cb6bf762";
                inttent();
            }
        });
        i2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url = "https://firebasestorage.googleapis.com/v0/b/my-project-1565721779873.appspot.com/o/2.mp4?alt=media&token=30fe75ef-0a9b-44b2-b24e-babd74ff6de7";
                inttent();
            }
        });
        i3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url = "https://firebasestorage.googleapis.com/v0/b/my-project-1565721779873.appspot.com/o/3.mp4?alt=media&token=3c2f2009-b756-4f09-8a62-ad53dd0dfea1";
                inttent();
            }
        });
        i4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url = "https://firebasestorage.googleapis.com/v0/b/my-project-1565721779873.appspot.com/o/4.mp4?alt=media&token=7828466a-536b-48b1-910e-4b95c94912dd";
                inttent();
            }
        });
        i5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url = "https://firebasestorage.googleapis.com/v0/b/my-project-1565721779873.appspot.com/o/5.mp4?alt=media&token=d6d600c9-4d48-456b-8e59-24187054059d";
                inttent();
            }
        });
        i6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url = "https://firebasestorage.googleapis.com/v0/b/my-project-1565721779873.appspot.com/o/6.mp4?alt=media&token=133813ab-3b92-4d6f-9461-8115eba6b7f1";
                inttent();
            }
        });
        i7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url = "https://firebasestorage.googleapis.com/v0/b/my-project-1565721779873.appspot.com/o/7.mp4?alt=media&token=64e4dae6-5515-44c8-835a-11ccddda2d1e";
                inttent();
            }
        });
        i8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url = "https://firebasestorage.googleapis.com/v0/b/my-project-1565721779873.appspot.com/o/8.mp4?alt=media&token=b0d50bee-c4e0-4f1e-9e46-d0f9b02eeb58";
                inttent();
            }
        });
        i9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url ="https://firebasestorage.googleapis.com/v0/b/my-project-1565721779873.appspot.com/o/9.mp4?alt=media&token=885c5151-e777-4bcb-a702-618c6a6fc5bf" ;
                inttent();
            }
        });
        i10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url = "https://firebasestorage.googleapis.com/v0/b/my-project-1565721779873.appspot.com/o/10.mp4?alt=media&token=3d251cea-e78c-4da0-a626-31b9f495d4c2";
                inttent();
            }
        });
        i11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url ="https://firebasestorage.googleapis.com/v0/b/my-project-1565721779873.appspot.com/o/11.mp4?alt=media&token=ace5254d-d0ba-4cd1-afc6-74e861f007e2";
                inttent();
            }
        });
        i12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url = "https://firebasestorage.googleapis.com/v0/b/my-project-1565721779873.appspot.com/o/12.mp4?alt=media&token=20531c6e-0fe4-42f4-81d4-437abc06b3ea";
                inttent();
            }
        });
        i13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url = "https://firebasestorage.googleapis.com/v0/b/my-project-1565721779873.appspot.com/o/13.mp4?alt=media&token=0aecbe23-75ed-43a3-89b6-1b25f51e9007";
                inttent();
            }
        });


    }

    private void inttent() {
        Intent myIntent = new Intent(Exercise.this, VideoPlayer.class);
        myIntent.putExtra("url",url);
        Exercise.this.startActivity(myIntent);
    }
}