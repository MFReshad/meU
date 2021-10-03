package com.example.meu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.meu.adapters.MessageAdapter;
import com.example.meu.models.Chat;
import com.example.meu.models.User;
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
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MessageActivity2 extends AppCompatActivity {

    CircleImageView profile_image;
    TextView username;

    ImageButton btn_send;
    EditText text_send;

    MessageAdapter messageAdapter;
    List<Chat> mchat;

    RecyclerView recyclerView;

    FirebaseUser mUser;
    DatabaseReference mRef;

    Intent intent;
    String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message2);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
/*
        recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);



 */
        username = findViewById(R.id.username);
        btn_send = findViewById(R.id.btn_send1);
        text_send = findViewById(R.id.text_send);
        profile_image = findViewById(R.id.profile_image);

        intent = getIntent();
        userid = intent.getStringExtra("userId");
        mUser = FirebaseAuth.getInstance().getCurrentUser();
        mRef = FirebaseDatabase.getInstance().getReference("User").child(userid);

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = text_send.getText().toString();
                if(!msg.equals(""))
                {
                    sendMessage(mUser.getUid(),userid,msg);
                }
                else
                {
                    Toast.makeText(MessageActivity2.this, "You can't send empty message", Toast.LENGTH_SHORT).show();
                }
                text_send.setText("");
            }
        });



        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                User con = snapshot.getValue(User.class);
                username.setText(con.getName());

                if(con.getImageURL().equals("default"))
                {
                    profile_image.setImageResource(R.mipmap.ic_launcher);
                }
                else if(con.getImageURL().equals("1")) {
                    profile_image.setImageResource(R.drawable.av1);

                }else if(con.getImageURL().equals("2")) {
                    profile_image.setImageResource(R.drawable.av2);

                }else if(con.getImageURL().equals("3")) {
                    profile_image.setImageResource(R.drawable.av3);

                }else if(con.getImageURL().equals("4")) {
                    profile_image.setImageResource(R.drawable.av4);

                }else if(con.getImageURL().equals("5")) {
                    profile_image.setImageResource(R.drawable.av5);

                }else if(con.getImageURL().equals("6")) {
                    profile_image .setImageResource(R.drawable.av6);

                }

                //readMesagges(mUser.getUid(), userid, con.getImageURL());

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });


    }

    private void sendMessage(String sender, String receiver, String message)
    {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("sender",sender);
        hashMap.put("receiver",receiver);
        hashMap.put("message",message);

        reference.child("Chats").push().setValue(hashMap);
/*
        // add user to chat fragment
        final DatabaseReference chatRef = FirebaseDatabase.getInstance().getReference("Chatlist")
                .child(mUser.getUid())
                .child(userid);

        chatRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()){
                    chatRef.child("id").setValue(userid);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        final DatabaseReference chatRefReceiver = FirebaseDatabase.getInstance().getReference("Chatlist")
                .child(userid)
                .child(mUser.getUid());
        chatRefReceiver.child("id").setValue(mUser.getUid());

        final String msg = message;

        reference = FirebaseDatabase.getInstance().getReference("Users").child(mUser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mConsultant user = dataSnapshot.getValue(mConsultant.class);

                if (notify) {
                    sendNotifiaction(receiver, user.getUsername(), msg);
                }
                notify = false;


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
*/

    }

    private void readMesagges(final String myid, final String userid, final String imageurl){
        mchat = new ArrayList<>();

        mRef = FirebaseDatabase.getInstance().getReference("Chats");
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mchat.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Chat chat = snapshot.getValue(Chat.class);
                    if (chat.getReceiver().equals(myid) && chat.getSender().equals(userid) ||
                            chat.getReceiver().equals(userid) && chat.getSender().equals(myid)){
                        mchat.add(chat);
                    }

                    messageAdapter = new MessageAdapter(MessageActivity2.this, mchat, imageurl);
                    recyclerView.setAdapter(messageAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}