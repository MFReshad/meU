package com.example.meu;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.tomer.fadingtextview.FadingTextView;

import at.markushi.ui.CircleButton;


//import com.antonionicolaspina.revealtextview.RevealTextView;


public class MainActivity2 extends AppCompatActivity {

/*
    private  static  int SPLASH_SCREEN;

    static {
        SPLASH_SCREEN = 10000;
    }
*/

    Animation fade, blinks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);



        final TypeWriter tw = (TypeWriter) findViewById(R.id.tw);

        String first = "Hey! I'm ";
        String next = "<b><font color='#2196F3'>meU </font></b>";
        tw.setText("");
        tw.setCharacterDelay(150);
        tw.animateText(Html.fromHtml(first + next));

        fade= AnimationUtils.loadAnimation(this,R.anim.fade_in);
        TextView tw3 = findViewById(R.id.textView5);
        tw3.postDelayed(new Runnable() {
            @Override
            public void run() {
                tw3.setVisibility(View.VISIBLE);
                tw3.setAnimation(fade);
            }
        },1500);



        //RevealTextView tw3 = (RevealTextView)  findViewById(R.id.textView5);

/*
        FadingTextView tw5 = findViewById(R.id.textView5);
        //tw5.setTexts("");
        String[] s = {"Tap here to start","I'm here to help you de-stress\n        and nurture yourself."};
        tw5.setTexts(s);
        tw5.setTimeout(3.5,FadingTextView.SECONDS);
/*

        CircleButton button = findViewById(R.id.btm);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this,LogIn.class);
                startActivity(intent);
                finish();
            }
        });


 */
        blinks = AnimationUtils.loadAnimation(this,R.anim.blink);
        Button bt = findViewById(R.id.btn);


        bt.postDelayed(new Runnable() {
            @Override
            public void run() {
                bt.setVisibility(View.VISIBLE);
                bt.setAnimation(blinks);;
            }
        },3000);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this,LogIn.class);
                startActivity(intent);
                finish();
                overridePendingTransition(0, 0);
            }
        });


/*
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity2.this,LogIn.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);
*/
    }
}