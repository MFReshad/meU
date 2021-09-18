package com.example.meu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class NavigationBar extends AppCompatActivity {

    MeowBottomNavigation bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_bar);

        bottomNavigation = findViewById(R.id.bottom_navigation);

        bottomNavigation.add(new MeowBottomNavigation.Model(1,R.drawable.ic_chat));
        bottomNavigation.add(new MeowBottomNavigation.Model(2,R.drawable.ic_goal));
        bottomNavigation.add(new MeowBottomNavigation.Model(3,R.drawable.ic_featured));
        bottomNavigation.add(new MeowBottomNavigation.Model(4,R.drawable.ic_profile));
        bottomNavigation.add(new MeowBottomNavigation.Model(5,R.drawable.ic_settings));

        //bottomNavigation.show(5,true);
        bottomNavigation.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                switch (model.getId()){
                    case 1:
                        System.out.println("Star AI chat");
                        replace(new AIchat());
                        //Intent myIntent = new Intent(NavigationBar.this, AIchat.class);
                       // NavigationBar.this.startActivity(myIntent);
                        System.out.println("Finish");
                        break;

                    case 2:
                        replace(new Goal());
                        break;
                    case 3:
                        replace(new Feature());
                        break;
                    case 4:
                        replace(new Profile());
                        break;
                    case 5:
                        replace(new Settings());
                        break;

                }
                return null;
            }
        });

    }

    public  void replace(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame,fragment);
        transaction.commit();

    }
}