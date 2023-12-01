package com.example.meu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.FirebaseDatabase;

public class ProfileSetting extends AppCompatActivity {

    Button link,load,chnImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_setting);

        link = findViewById(R.id.linkbutton);
        load = findViewById(R.id.loadbutton);
        chnImg = findViewById(R.id.changeImg);

        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(ProfileSetting.this, LinkID.class);
                ProfileSetting.this.startActivity(myIntent);
            }
        });

        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(ProfileSetting.this, LoadData.class);
                ProfileSetting.this.startActivity(myIntent);
            }
        });

        chnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(ProfileSetting.this, UpdateProfile.class);
                ProfileSetting.this.startActivity(myIntent);
            }
        });

    }
/*
    @Override
    public void onBackPressed() {


        Intent intent = new Intent(ProfileSetting.this, NavigationBar.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        ProfileSetting.this.startActivity(intent);
        finish();

    }

 */
}