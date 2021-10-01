package com.example.meu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.meu.adapters.CustomAdapter1;
import com.example.meu.models.Consultant;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;

public class ChatWithCons extends AppCompatActivity {

    private ListView listview;
    DatabaseReference ref;
    private List<Consultant> cnslist;
    private CustomAdapter1 customAdapter1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_with_cons);

        ref = FirebaseDatabase.getInstance().getReference("Consultant");
        cnslist = new ArrayList<>();
        customAdapter1 = new CustomAdapter1(ChatWithCons.this, cnslist);

        listview = findViewById(R.id.llistviewId);
    }


    @Override
    protected void onStart() {
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                cnslist.clear();
                for(DataSnapshot dns:snapshot.getChildren()){
                    Consultant cn = dns.getValue(Consultant.class);

                    cnslist.add(cn);
                }
                listview.setAdapter(customAdapter1);

            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });
        super.onStart();
    }


}