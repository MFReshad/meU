package com.example.meu.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meu.MessageActivity;
import com.example.meu.MessageActivity2;
import com.example.meu.R;
import com.example.meu.models.User;
import com.example.meu.models.mConsultant;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private Context mContext;
    private List<User> mUser;

    public UserAdapter(Context mContext, List<User> mUser)
    {
        this.mUser = mUser;
        this.mContext = mContext;
    }

    @NonNull
    @NotNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.user_item, parent,false);
        return new UserAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull UserAdapter.ViewHolder holder, int position) {

        User mCons = mUser.get(position);
        holder.username.setText(mCons.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent1 = new Intent(mContext, MessageActivity2.class);
                myIntent1.putExtra("userId",mCons.getId());
                mContext.startActivity(myIntent1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mUser.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView username;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.username);
        }
    }
}
