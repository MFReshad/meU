package com.example.meu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.util.ArrayList;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import pl.droidsonroids.gif.GifImageView;

import static com.example.meu.LogIn.TEXT_NAME;
import static com.example.meu.LogIn.isLogin;

public class NavigationBar extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    MeowBottomNavigation bottomNavigation;
    TextView tv;
    GifImageView gif;
    NavigationView nav;
    int vis = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_bar);

        gif = findViewById(R.id.breath);
        tv = findViewById(R.id.tv1);
        nav = findViewById(R.id.nav_view);

        bottomNavigation = findViewById(R.id.bottom_navigation);
        FrameLayout fm = findViewById(R.id.frame);

        nav.setNavigationItemSelectedListener(this);


        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_chat));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.ic_goal));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.ic_featured));
        bottomNavigation.add(new MeowBottomNavigation.Model(4, R.drawable.ic_profile));
        // bottomNavigation.add(new MeowBottomNavigation.Model(5,R.drawable.ic_settings));

        //bottomNavigation.show(5,true);
        bottomNavigation.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                switch (model.getId()) {
                    case 1:
                        //System.out.println("Star AI chat");
                        hide();
                        fm.setBackgroundColor(Color.WHITE);
                        replace(new AIchat());
                        //Intent myIntent = new Intent(NavigationBar.this, AIchat.class);
                        // NavigationBar.this.startActivity(myIntent);
                        //System.out.println("Finish");
                        break;

                    case 2:
                        replace(new Goal());
                        hide();
                        fm.setBackgroundColor(Color.WHITE);
                        break;
                    case 3:
                        replace(new Feature());
                        hide();
                        fm.setBackgroundColor(Color.WHITE);
                        break;
                    case 4:
                        replace(new Profile());
                        fm.setBackgroundColor(Color.WHITE);
                        hide();
                        vis = 1;
                        nav.setVisibility(View.VISIBLE);
                        break;
   /*
                    case 5:
                        replace(new Settings());
                        hide();
                        fm.setBackgroundColor(Color.WHITE);
                        break;

    */

                }
                return null;
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (vis == 1) {
            nav.setVisibility(View.INVISIBLE);
            replace(new AIchat());
            vis = 0;

        } else
            super.onBackPressed();

    }

    public void replace(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, fragment);
        transaction.commit();

    }

    public void hide() {
        tv.setVisibility(View.INVISIBLE);
        gif.setVisibility(View.INVISIBLE);
        nav.setVisibility(View.INVISIBLE);
        vis = 0;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.data:
                Intent myIntent = new Intent(NavigationBar.this, ProfileSetting.class);
                startActivity(myIntent);
                break;
            case R.id.logout:
                finish();
                LogIn.isLogin = 0;
                saveData();
                Intent myIntent1 = new Intent(NavigationBar.this, LogIn.class);
                startActivity(myIntent1);
                break;
            case R.id.about:
                Intent myIntent2 = new Intent(NavigationBar.this, AboutUs.class);
                startActivity(myIntent2);
                break;
            case R.id.contact:

                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"180104040@aust.edu"});
                intent.putExtra(Intent.EXTRA_SUBJECT, "Contact Support #meU");
                intent.setData(Uri.parse("mailto:"));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
                break;


        }
        return false;
    }

    public void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences("saveUser", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        TEXT_NAME = "";
        editor.putString("name",TEXT_NAME);
        editor.putInt("value",isLogin);
        editor.apply();
    }
}