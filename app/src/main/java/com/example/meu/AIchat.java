package com.example.meu;


import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class AIchat extends Fragment implements View.OnClickListener {

    Button b1,b2;


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
                Intent myIntent2 = new Intent(getActivity(), ChatWithCons.class);
                AIchat.this.startActivity(myIntent2);
                break;

        }
    }
}
