package com.example.meu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.meu.Fragments.ChatFragment;
import com.example.meu.Fragments.ConsultantFragment;
import com.example.meu.Fragments.UserFragment;
import com.example.meu.models.User;
import com.example.meu.models.mConsultant;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import de.hdodenhof.circleimageview.CircleImageView;

public class ConsultantChatView extends AppCompatActivity {


    TextView username;

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    DatabaseReference mRef;

    BottomNavigationView bn;
    CircleImageView image_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultant_chat_view);

        bn = findViewById(R.id.nv);
        username = findViewById(R.id.username);

        image_profile = findViewById(R.id.profile_image);


        FrameLayout fm = findViewById(R.id.frame1);

        mUser = mAuth.getInstance().getCurrentUser();
        String uID = mUser.getUid();
        mRef = FirebaseDatabase.getInstance().getReference("Consultant").child(uID);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        switchToFragment1();
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                mConsultant user = snapshot.getValue(mConsultant.class);
                username.setText(user.getName());
                String im = user.getImageUrl();
                if (im.equals("noImg")){
                    image_profile.setImageResource(R.mipmap.ic_launcher);
                }
                /*else if(im.equals("1")) {
                    image_profile.setImageResource(R.drawable.av1);

                }else if(im.equals("2")) {
                    image_profile.setImageResource(R.drawable.av2);

                }else if(im.equals("3")) {
                    image_profile.setImageResource(R.drawable.av3);

                }else if(im.equals("4")) {
                    image_profile.setImageResource(R.drawable.av4);

                }else if(im.equals("5")) {
                    image_profile.setImageResource(R.drawable.av5);

                }else if(im.equals("6")) {
                    image_profile.setImageResource(R.drawable.av6);

                }

                 */

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        bn.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.chat:
                        switchToFragment1();
                        break;

                    case R.id.user:
                        switchToFragment2();
                        break;

                }
                return false;
            }
        });
/*
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPager viewPager = findViewById(R.id.view_pager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragement(new ChatFragment(),"Chat");
        viewPagerAdapter.addFragement(new ConsultantFragment(),"Consultant");

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

 */
    }
/*
    class ViewPagerAdapter extends FragmentPagerAdapter {


        private ArrayList<Fragment> fragments;
        private ArrayList<String> titles;

        ViewPagerAdapter(FragmentManager fm) {
            super(fm);
            this.fragments = new ArrayList<>();
            this.titles = new ArrayList<>();
        }

        @Override
        public Fragment getItem(int position) {
            return null;
        }

        @Override
        public int getCount() {
            return 0;
        }

        public void addFragement(Fragment fragment, String title) {
            fragments.add(fragment);
            titles.add(title);
        }

        @Nullable
        @org.jetbrains.annotations.Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }

 */

    public void switchToFragment1() {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.frame1, new ChatFragment()).commit();
    }

    public void switchToFragment2() {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.frame1, new UserFragment()).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                Intent myIntent2 = new Intent(ConsultantChatView.this, MainActivity2.class);
                ConsultantChatView.this.startActivity(myIntent2);
                finish();
                return true;
            case R.id.picPicker:
                Intent myIntent3 = new Intent(ConsultantChatView.this, PickImageConsultant.class);
                ConsultantChatView.this.startActivity(myIntent3);

        }
        return false;
    }
}