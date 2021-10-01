package com.example.meu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class ConsultantChatView extends AppCompatActivity {


    Button sngOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultant_chat_view);

        sngOut = findViewById(R.id.sign_out);

        sngOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent myIntent1 = new Intent(ConsultantChatView.this, MainActivity2.class);
                ConsultantChatView.this.startActivity(myIntent1);
                finish();

            }
        });
    }
}