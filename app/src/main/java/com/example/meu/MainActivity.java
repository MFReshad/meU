package com.example.meu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private  static  int SPLASH_SCREEN;

    static {
        SPLASH_SCREEN = 5000;
    }

    Animation topAnim,bottomAnim;
    ImageView image,logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        topAnim= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim= AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        image = findViewById(R.id.imageView);
        logo = findViewById(R.id.imageView3);

        image.setAnimation(topAnim);
        logo.setAnimation(bottomAnim);

        loadData();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(LogIn.isLogin==0)
                {
                    Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Intent myIntent = new Intent(MainActivity.this, NavigationBar.class);
                    MainActivity.this.startActivity(myIntent);
                    finish();
                }

            }
        },SPLASH_SCREEN);


    }

    public void loadData(){
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("saveUser",MODE_PRIVATE);
        LogIn.TEXT_NAME = sharedPreferences.getString("name","");
        LogIn.isLogin = sharedPreferences.getInt("value",MODE_PRIVATE);

    }
}