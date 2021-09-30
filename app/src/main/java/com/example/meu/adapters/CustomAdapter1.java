package com.example.meu.adapters;


import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meu.R;
import com.example.meu.models.Consultant;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.CLIPBOARD_SERVICE;

public class CustomAdapter1 extends ArrayAdapter<Consultant> implements Filterable {
    private List<Consultant> cnslist;
    private List<Consultant> orig;
    private Activity context;
    public int flag, pr = 0;

    public CustomAdapter1(Activity context, List<Consultant> cnslist) {
        super(context, R.layout.sample_layout, cnslist);
        this.context = context;
        this.cnslist = cnslist;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = context.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.sample_layout, null, true);

        flag = position;
        Consultant cn = cnslist.get(position);
        TextView t1 = view.findViewById(R.id.cnsn);
        TextView t2 = view.findViewById(R.id.cnsg);

        t1.setText("Name: " + cn.getName());
        t2.setText("Gender: "+ cn.getGender());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, cnslist.get(position).getMail(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return cnslist.size();
    }

    @Override
    public Consultant getItem(int position) {
        return cnslist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}

