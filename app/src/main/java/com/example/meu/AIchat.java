package com.example.meu;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import pl.droidsonroids.gif.GifImageView;


public class AIchat extends Fragment implements View.OnClickListener {


    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private DatabaseReference mRootRef;
    private FirebaseDatabase mFDB;
    private FirebaseAuth.AuthStateListener mAuthLis;
    GifImageView b2,b1;

    String uID;

    Animation leftright,rightleft;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_aichat, container, false);

        mAuth = FirebaseAuth.getInstance();
        mFDB = FirebaseDatabase.getInstance();
        mRootRef = mFDB.getReference();
        mUser = mAuth.getCurrentUser();
        uID = mUser.getUid();



        b1 = view.findViewById(R.id.button);
        b2 = view.findViewById(R.id.button1);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        leftright= AnimationUtils.loadAnimation(getActivity(),R.anim.left_right);
        rightleft= AnimationUtils.loadAnimation(getActivity(),R.anim.right_left);
        b1.setAnimation(leftright);
        b2.setAnimation(leftright);
        b1.setVisibility(View.VISIBLE);
        b2.setVisibility(View.VISIBLE);

        return view;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                Intent myIntent = new Intent(getActivity(), ChatWithAI.class);
                AIchat.this.startActivity(myIntent);
                break;

            case R.id.button1:
                checkPaid();
                break;

        }
    }

    private void checkPaid() {

        String uId = mUser.getUid();

        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                if(snapshot.child("User").child(uId).child("Payment").exists())
                {
                    Intent myIntent1 = new Intent(getActivity(), ChatWithCons.class);
                    startActivity(myIntent1);
                }
                else
                {
                    Intent myIntent1 = new Intent(getActivity(), ConsConfirm.class);
                    AIchat.this.startActivity(myIntent1);
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }
}
