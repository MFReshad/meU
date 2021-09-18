package com.example.meu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LogIn extends AppCompatActivity {

    Animation top,tp,fade;

    //TextView tw1,tw2,tw3;
    Button button;
    EditText username;
    ImageView iiv;

    public static final String SHARED_PREFS = "sharedPref";
    public static final String TEXT = "text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        iiv = findViewById(R.id.iV2);
        username = findViewById(R.id.Name);
        TextView tw =  findViewById(R.id.tw);
        TextView tw1 = findViewById(R.id.tw1);
        TextView tw2 = findViewById(R.id.tw2);
        TextView tw3 = findViewById(R.id.tw3);


       // tw1.setText(s+s1);
        tp = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        fade = AnimationUtils.loadAnimation(this,R.anim.fade_in_little);
        tw2.setAnimation(tp);
        tw3.setAnimation(tp);
        iiv.setAnimation(tp);
        tw1.setAnimation(fade);
        username.setAnimation(fade);


        String first = "Hey! I'm ";
        String next = "<b><font color='#2196F3'>meU </font></b>";
        tw.setText(Html.fromHtml(first + next));

        top = AnimationUtils.loadAnimation(this,R.anim.top_up);
        tw.setAnimation(top);


        //password = findViewById(R.id.password);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = username.getText().toString();
                //String pass = password.getText().toString();

                if(name.length()>1)
                {
                    //Toast.makeText(LogIn.this, "Login Successful.", Toast.LENGTH_SHORT).show();
                    Intent myIntent = new Intent(LogIn.this, NavigationBar.class);
                    LogIn.this.startActivity(myIntent);
                }
                else
                    Toast.makeText(LogIn.this, "Short name.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(TEXT,username.getText().toString());
    }

    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        
    }
}