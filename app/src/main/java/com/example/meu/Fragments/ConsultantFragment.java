package com.example.meu.Fragments;

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


public class ConsultantFragment extends Fragment {

    private RecyclerView recyclerView;
    private ConsultantAdapter consultantAdapter;
    private List<mConsultant> mConst;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_consultant, container, false);

        recyclerView = view.findViewById(R.id.recycle_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mConst = new ArrayList<>();

        readConst();
        return view;

    }

    private void readConst() {

        FirebaseUser mfUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference("Consultant");

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                mConst.clear();
                for (DataSnapshot dsnapshot : snapshot.getChildren() )
                {
                    mConsultant mCons = dsnapshot.getValue(mConsultant.class);

                    assert mCons != null;
                    assert mfUser != null;
                    if(!mCons.getId().equals(mfUser.getUid()))
                    {
                        mConst.add(mCons);
                    }
                }

                consultantAdapter = new ConsultantAdapter(getContext(),mConst);
                recyclerView.setAdapter(consultantAdapter);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }


}