package com.example.meu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class LoadData extends AppCompatActivity {

    private EditText mail,pass;
    private Button btn;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_data);

        mail = findViewById((R.id.mail));
        pass = findViewById((R.id.pass));
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

                Toast.makeText(LoadData.this, "Login Successful.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}