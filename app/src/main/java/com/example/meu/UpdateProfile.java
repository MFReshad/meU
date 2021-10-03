package com.example.meu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.meu.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class UpdateProfile extends AppCompatActivity {

    CircleImageView image_profile;
    TextView username;

    DatabaseReference reference;
    FirebaseUser fuser;


    Button upload;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        image_profile = findViewById(R.id.profile_image);
        username = findViewById(R.id.username);
        upload = findViewById(R.id.updatebtn);



        fuser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("User").child(fuser.getUid());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                username.setText(user.getName());
                String im = user.getImageURL();
                if (im.equals("default")){
                    image_profile.setImageResource(R.mipmap.ic_launcher);
                } else if(im.equals("1")) {
                    image_profile.setImageResource(R.drawable.av1);

                }else if(im.equals("2")) {
                    image_profile.setImageResource(R.drawable.av2);

                }else if(im.equals("3")) {
                    image_profile.setImageResource(R.drawable.av3);

                }else if(im.equals("4")) {
                    image_profile.setImageResource(R.drawable.av4);

                }else if(im.equals("5")) {
                    image_profile.setImageResource(R.drawable.av5);

                }else if(im.equals("6")) {
                    image_profile.setImageResource(R.drawable.av6);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        image_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent1 = new Intent(UpdateProfile.this, PickImage.class);
                UpdateProfile.this.startActivity(myIntent1);
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent1 = new Intent(UpdateProfile.this, ProfileSetting.class);
                UpdateProfile.this.startActivity(myIntent1);
                finish();
                Toast.makeText(UpdateProfile.this, "Pic updated", Toast.LENGTH_SHORT).show();
            }
        });
    }
}