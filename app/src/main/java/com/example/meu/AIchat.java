package com.example.meu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;


import com.example.meu.adapters.ChatAdapter;
import com.example.meu.helpers.SendMessageInBg;
import com.example.meu.interfaces.BotReply;
import com.example.meu.models.Message;
import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.dialogflow.v2.DetectIntentResponse;
import com.google.cloud.dialogflow.v2.QueryInput;
import com.google.cloud.dialogflow.v2.SessionName;
import com.google.cloud.dialogflow.v2.SessionsClient;
import com.google.cloud.dialogflow.v2.SessionsSettings;
import com.google.cloud.dialogflow.v2.TextInput;
import com.google.common.collect.Lists;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

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
