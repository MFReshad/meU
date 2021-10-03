package com.example.meu.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.meu.MessageActivity;
import com.example.meu.R;
import com.example.meu.models.Chat;
import com.example.meu.models.mConsultant;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    public static  final int MSG_TYPE_LEFT = 0;
    public static  final int MSG_TYPE_RIGHT = 1;

    private Context mContext;
    private List<Chat> mChat;
    private String imageUrl;

    FirebaseUser fuser;

    public MessageAdapter(Context mContext, List<Chat> mChat, String imageUrl) {
        this.mChat = mChat;
        this.mContext = mContext;
        this.imageUrl = imageUrl;
    }

    @NonNull
    @NotNull
    @Override
    public MessageAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        if(viewType == MSG_TYPE_RIGHT) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.chat_item_right, parent, false);
            return new MessageAdapter.ViewHolder(view);
        }
        else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.chat_item_left, parent, false);
            return new MessageAdapter.ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull com.example.meu.adapters.MessageAdapter.ViewHolder holder, int position) {

        Chat chat = mChat.get(position);
        holder.show_message.setText(chat.getMessage());

        if(imageUrl.equals("default") || imageUrl.equals("noImg"))
        {
            holder.profile_image.setImageResource(R.mipmap.ic_launcher);
        }
        else if(imageUrl.equals("1")) {
            holder.profile_image.setImageResource(R.drawable.av1);

        }else if(imageUrl.equals("2")) {
            holder.profile_image.setImageResource(R.drawable.av2);

        }else if(imageUrl.equals("3")) {
            holder.profile_image.setImageResource(R.drawable.av3);

        }else if(imageUrl.equals("4")) {
            holder.profile_image.setImageResource(R.drawable.av4);

        }else if(imageUrl.equals("5")) {
            holder.profile_image.setImageResource(R.drawable.av5);

        }else if(imageUrl.equals("6")) {
            holder.profile_image.setImageResource(R.drawable.av6);

        }
    }

    @Override
    public int getItemCount() {
        return mChat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView show_message;
        public ImageView profile_image;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            show_message = itemView.findViewById(R.id.show_message);
            profile_image = itemView.findViewById(R.id.profile_image);
        }
    }


    @Override
    public int getItemViewType(int position) {
        fuser = FirebaseAuth.getInstance().getCurrentUser();
        if(mChat.get(position).getSender().equals(fuser.getUid()))
        {
            return MSG_TYPE_RIGHT;
        }
        else
        {
            return MSG_TYPE_LEFT;

        }
    }
}
