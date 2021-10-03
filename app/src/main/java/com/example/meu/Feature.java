package com.example.meu;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

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

import pl.droidsonroids.gif.GifImageView;


public class Feature extends Fragment implements View.OnClickListener {

    GifImageView medi,exc;
    DatabaseReference reference;
    FirebaseUser mUser;

    String url,n,name;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_feature, container, false);

        medi = view.findViewById(R.id.meditation);
        exc = view.findViewById(R.id.exercise);

       medi.setOnClickListener(this);
       exc.setOnClickListener(this);

        Random rn = new Random();
        int answer = rn.nextInt(10) + 1;
        int temp = (Math.random() <= 0.5) ? 1 : 2;
        n = Integer.toString(temp);

        mUser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Video").child("Meditation").child(n);




        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                Video video = snapshot.getValue(Video.class);
                url = video.getUrl();
                name = video.getTitle();


            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        return view;

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.meditation:
                Intent myIntent = new Intent(getActivity(), Meditation.class);
                myIntent.putExtra("url",url);
                myIntent.putExtra("name",name);
                Feature.this.startActivity(myIntent);
                break;
            case R.id.exercise:
                Intent myIntent1 = new Intent(getActivity(), Exercise.class);
                Feature.this.startActivity(myIntent1);
                break;

        }
    }
}