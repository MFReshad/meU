package com.example.meu.Fragments;

import android.media.session.MediaSession;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.meu.R;
import com.example.meu.adapters.ConsultantAdapter;
import com.example.meu.models.Chat;
import com.example.meu.models.Chatlist;
import com.example.meu.models.mConsultant;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ChatFragment extends Fragment {
    private RecyclerView recyclerView;

    private ConsultantAdapter userAdapter;
    private List<mConsultant> mUsers;

    FirebaseUser fuser;
    DatabaseReference reference;

    private List<String> usersList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        fuser = FirebaseAuth.getInstance().getCurrentUser();

        usersList = new ArrayList<>();

        reference = FirebaseDatabase.getInstance().getReference("Chat").child(fuser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                usersList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Chat chat = snapshot.getValue(Chat.class);
                    if (chat.getSender().equals(fuser.getUid()))
                    {
                        usersList.add(chat.getReceiver());
                    }
                    if (chat.getReceiver().equals(fuser.getUid()))
                    {
                        usersList.add(chat.getSender());
                    }

                }

                chatList();
            }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //updateToken(FirebaseInstanceId.getInstance().getToken());

        return view;
    }



    private void chatList() {
        mUsers = new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference("Consultant");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mUsers.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    mConsultant user = snapshot.getValue(mConsultant.class);
                    if (user != null && usersList.contains(user.getId())) {
                        mUsers.add(user);
                    }
                }

                // Set up the adapter and attach it to the RecyclerView
                userAdapter = new ConsultantAdapter(getContext(), mUsers);
                recyclerView.setAdapter(userAdapter);

                // Notify the adapter that the data set has changed
                userAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle onCancelled
            }
        });
    }





}