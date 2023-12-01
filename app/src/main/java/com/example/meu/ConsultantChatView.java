package com.example.meu;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.meu.Fragments.ChatFragment;
import com.example.meu.Fragments.UserFragment;
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

    private TextView username;
    private CircleImageView imageProfile;
    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultant_chat_view);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        if (mUser == null) {
            // User is not authenticated, handle accordingly (e.g., redirect to login)
            // Add your code here...
        }

        username = findViewById(R.id.username);
        imageProfile = findViewById(R.id.profile_image);
        bottomNavigationView = findViewById(R.id.nv);
        frameLayout = findViewById(R.id.frame1);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        switchToFragment1();

        mRef = FirebaseDatabase.getInstance().getReference("Consultant").child(mUser.getUid());
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                mConsultant user = snapshot.getValue(mConsultant.class);
                if (user != null) {
                    username.setText(user.getName());
                    String imageUrl = user.getImageUrl();
                    // Set image based on imageUrl
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                // Handle onCancelled
            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.chat:
                    switchToFragment1();
                    return true;

                case R.id.user:
                    switchToFragment2();
                    return true;

                default:
                    return false;
            }
        });
    }

    private void switchToFragment1() {
        replaceFragment(new ChatFragment());
    }

    public void switchToFragment2() {
        FragmentManager manager = getSupportFragmentManager();
        Fragment userFragment = new UserFragment();

        // Use addToBackStack to enable navigation back to the previous fragment
        manager.beginTransaction().replace(R.id.frame1, userFragment).addToBackStack(null).commit();
    }


    private void replaceFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.frame1, fragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                Intent myIntent2 = new Intent(ConsultantChatView.this, MainActivity2.class);
                startActivity(myIntent2);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
