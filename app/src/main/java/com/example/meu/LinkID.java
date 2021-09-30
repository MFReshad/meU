package com.example.meu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.meu.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import static com.example.meu.LogIn.TEXT_NAME;

public class LinkID extends AppCompatActivity {

    EditText email1,pass1,pass2;
    Button save;

    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;
    private DatabaseReference mRootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_id);

        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();
        mRootRef = FirebaseDatabase.getInstance().getReference("User");

        email1 = findViewById(R.id.mail);
        pass1 = findViewById(R.id.pass);
        pass2 = findViewById(R.id.pass2);
        save = findViewById(R.id.link);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCurrentUser != null)
                {
                    userLogin();
                    String email = email1.getText().toString();
                    String password = pass1.getText().toString();



                    AuthCredential credential = EmailAuthProvider.getCredential(email, password);
                    mCurrentUser.linkWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                saveDB();
                                Toast.makeText(LinkID.this, "Account has connected with cloud.", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(LinkID.this, ""+task.getException(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });
    }

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
    }

    private void userLogin()
    {
        String name = email1.getText().toString();
        String p1 = pass1.getText().toString();
        String p2 = pass2.getText().toString();

        if(name.isEmpty())
        {
            email1.setError("Enter an email address");
            email1.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(name).matches())
        {
            email1.setError("Enter a valid email address");
            email1.requestFocus();
            return;
        }

        if(p1.isEmpty())
        {
            pass1.setError("Enter a password");
            pass1.requestFocus();
            return;
        }

        if(p1.length()<6)
        {
            pass1.setError("Enter at least 6 characters");
            pass1.requestFocus();
            return;
        }

        if(p2.isEmpty() || !p2.equals(p1))
        {
            pass2.setError("Enter the same password");
            pass2.requestFocus();
            return;
        }

    }
/*
    public void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences("saveUser", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        TEXT_NAME = "";
        editor.putString("name",TEXT_NAME);
        editor.putInt("value",isLogin);
        editor.apply();
    }



    public void updateDB()
    {
        HashMap<String,Object> map = new HashMap<>();
        // map.put("name", TEXT_NAME);
        map.put("email", "email");
        map.put("id",mAuth.getCurrentUser().getUid());
        FirebaseDatabase.getInstance().getReference().child("user").child(mAuth.getCurrentUser().getUid()).updateChildren(map);
        //mRootRef.child("User").child(mAuth.getCurrentUser().getUid()).setValue(map);

        String emaill = email1.getText().toString();
        String passwordd = pass1.getText().toString();
    }

    */


    public void saveDB()
    {
       // String uname = "";
        String mail = email1.getText().toString();
        String uid = mAuth.getCurrentUser().getUid();

       // User user = new User(uname,mail,uid);

        mRootRef.child(uid).child("mail").setValue(mail);
    }
}