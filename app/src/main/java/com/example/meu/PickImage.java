package com.example.meu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PickImage extends AppCompatActivity {

    ImageView i1,i2,i3,i4,i5,i6;
    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;
    private DatabaseReference mRootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_image);

        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();
        mRootRef = FirebaseDatabase.getInstance().getReference("User");

        i1 =findViewById(R.id.imageButton1);
        i2 =findViewById(R.id.imageButton2);
        i3 =findViewById(R.id.imageButton3);
        i4 =findViewById(R.id.imageButton4);
        i5 =findViewById(R.id.imageButton5);
        i6 =findViewById(R.id.imageButton6);
/*
        i1.setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
        i2.setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
        i3.setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
        i4.setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
        i5.setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
        i6.setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);

 */

        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n = "1";
                mRootRef.child(mAuth.getCurrentUser().getUid()).child("imageURL").setValue(n);
                intent();
            }
        });

        i2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n = "2";
                mRootRef.child(mAuth.getCurrentUser().getUid()).child("imageURL").setValue(n);
                intent();
            }
        });

        i3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n = "3";
                mRootRef.child(mAuth.getCurrentUser().getUid()).child("imageURL").setValue(n);
                intent();
            }
        });

        i4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n = "4";
                mRootRef.child(mAuth.getCurrentUser().getUid()).child("imageURL").setValue(n);
                intent();
            }
        });

        i5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n = "5";
                mRootRef.child(mAuth.getCurrentUser().getUid()).child("imageURL").setValue(n);
                intent();
            }
        });

        i6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n = "6";
                mRootRef.child(mAuth.getCurrentUser().getUid()).child("imageURL").setValue(n);
                intent();
            }
        });

    }

    private void intent() {
        Intent myIntent1 = new Intent(PickImage.this, UpdateProfile.class);

        PickImage.this.startActivity(myIntent1);
        finish();
    }
}