package com.example.meu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.FirebaseDatabase;

public class ProfileSetting extends AppCompatActivity {

    Button link,load,chnPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_setting);

        link = findViewById(R.id.linkbutton);
        load = findViewById(R.id.loadbutton);
        chnPass = findViewById(R.id.changepassbtn);

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

        chnPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}