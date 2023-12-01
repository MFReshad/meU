package com.example.meu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {

    private  static  int SPLASH_SCREEN;

    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;

    static {
        SPLASH_SCREEN = 5000;
    }

    Animation topAnim,bottomAnim;
    ImageView image,logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        topAnim= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim= AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        image = findViewById(R.id.imageView);
        logo = findViewById(R.id.imageView3);

        image.setAnimation(topAnim);
        logo.setAnimation(bottomAnim);

        //loadData();

        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(CheckNetwork.isInternetAvailable(MainActivity.this)) //returns true if internet available
                {
                    if(mCurrentUser != null)
                    {
                        checkPeopple();

                    }

                    else
                    {
                        Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                        startActivity(intent);
                        finish();
                    }
                }
                else
                {
                    Toast.makeText(MainActivity.this,"No Internet Connection",Toast.LENGTH_SHORT).show();
                    alert();
                }


            }
        },SPLASH_SCREEN);


    }

    private void checkPeopple() {
        String uId = mCurrentUser.getUid();

        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                if(snapshot.child("User").child(uId).exists())
                {
                    try {
                        Intent myIntent = new Intent(MainActivity.this, NavigationBar.class);
                        MainActivity.this.startActivity(myIntent);
                        finish();
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                        //Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Intent myIntent = new Intent(MainActivity.this, ConsultantChatView.class);
                    MainActivity.this.startActivity(myIntent);
                    finish();
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }

    public void alert()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Connection error");
        builder.setMessage("Unable to connect with the server. Check your internet connection and try again.");
        builder.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {


                startActivity(new Intent(MainActivity.this, MainActivity.class));
                finish();
                dialog.dismiss();
                // stop chronometer here

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
                finish();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.


    }
}