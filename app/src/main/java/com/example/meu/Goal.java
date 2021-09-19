package com.example.meu;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class Goal extends Fragment implements View.OnClickListener {

    Button b1,b2;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_goal, container, false);

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
                Intent myIntent = new Intent(getActivity(), ToDoList.class);
                Goal.this.startActivity(myIntent);
                break;

            case R.id.button1:
                Intent myIntent2 = new Intent(getActivity(), ScheduleOrGoal.class);
                Goal.this.startActivity(myIntent2);
                break;

        }
    }
}