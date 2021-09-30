package com.example.meu;


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

import pl.droidsonroids.gif.GifImageView;


public class AIchat extends Fragment implements View.OnClickListener {


    GifImageView b2,b1;

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
                Intent myIntent1 = new Intent(getActivity(), ConsConfirm.class);
                AIchat.this.startActivity(myIntent1);
                break;

        }
    }
}
