package com.example.meu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.meu.models.Consultant;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class LoginConsultant extends AppCompatActivity {

    private EditText mail,pass;
    private Button btn;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_consultant);

        mail = findViewById((R.id.email_edit_text));
        pass = findViewById((R.id.pass_edit_text));
        btn =  findViewById((R.id.loadbtn));

        mAuth = FirebaseAuth.getInstance();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mail.getText().toString();
                String mpass = pass.getText().toString();
                loginUser(name, mpass);

            }
        });
    }

    private void loginUser(String name, String mpass) {

        mAuth.signInWithEmailAndPassword( name, mpass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {

                Toast.makeText(LoginConsultant.this, "Login Successful.", Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(LoginConsultant.this, ConsultantChatView.class);
                LoginConsultant.this.startActivity(myIntent);
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                Toast.makeText(LoginConsultant.this, ""+e, Toast.LENGTH_SHORT).show();
            }
        });
    }
}