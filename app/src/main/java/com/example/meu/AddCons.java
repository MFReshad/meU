package com.example.meu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.meu.models.Consultant;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

public class AddCons extends AppCompatActivity {

    CheckBox a, b;
    String gender;
    Button bn;
    EditText name,mail,pass;

    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;
    private DatabaseReference mRootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cons);

        a = findViewById(R.id.checkBox);
        b = findViewById(R.id.checkBox2);
        bn = findViewById(R.id.loadbtn);
        name =findViewById(R.id.name_edit_text);
        pass = findViewById(R.id.pass_edit_text);
        mail = findViewById(R.id.mail_edit_text);

        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();
        mRootRef = FirebaseDatabase.getInstance().getReference("Consultant");

        a.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                b.setChecked(false);
                gender = "male";
            }
        });
        b.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                a.setChecked(false);
                gender = "female";
            }
        });

        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
                String txt_mail = mail.getText().toString();
                String txt_pass = pass.getText().toString();
                if(mCurrentUser==null)
                {
                    register(txt_mail,txt_pass);
                }
            }
        });


    }

    private void register(String email, String pass) {
        mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {

                    String uname = name.getText().toString();
                    String uid = mAuth.getCurrentUser().getUid();
                    String gen = gender;
                    Consultant cons = new Consultant(uname,email,uid,gender);

                    mRootRef.child(uid).setValue(cons);

                    Toast.makeText(AddCons.this, "Consultant added", Toast.LENGTH_SHORT).show();
                    Intent myIntent1 = new Intent(AddCons.this, ConsultantReqOrLogin.class);
                    startActivity(myIntent1);
                }
                else
                    Toast.makeText(AddCons.this, "failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void userLogin()
    {
        String name = mail.getText().toString();
        String p1 = pass.getText().toString();


        if(name.isEmpty())
        {
            mail.setError("Enter an email address");
            mail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(name).matches())
        {
            mail.setError("Enter a valid email address");
            mail.requestFocus();
            return;
        }

        if(p1.isEmpty())
        {
            pass.setError("Enter a password");
            pass.requestFocus();
            return;
        }

        if(p1.length()<6)
        {
            pass.setError("Enter at least 6 characters");
            pass.requestFocus();
            return;
        }


    }

}
