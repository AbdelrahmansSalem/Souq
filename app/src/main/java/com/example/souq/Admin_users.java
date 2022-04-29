package com.example.souq;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;



public class Admin_users extends Fragment {

    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();
    ArrayList<String> users =new ArrayList<>();
    ArrayList<String> types=new ArrayList<>();
    RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

         View view= inflater.inflate(R.layout.fragment_admin_users, container, false);
         recyclerView=view.findViewById(R.id.users_list);
        Users_adaptor users_adaptor=new Users_adaptor(users,types,getContext(),position -> {

        });
        recyclerView.setAdapter(users_adaptor);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
         databaseReference.child("All Users").addListenerForSingleValueEvent(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot snapshot) {
                 for (DataSnapshot snapshot1 :snapshot.getChildren()){
                     users.add(snapshot1.child("name").getValue().toString());
                     types.add(snapshot1.child("user_type").getValue().toString());
                 }
                 users_adaptor.notifyDataSetChanged();
             }

             @Override
             public void onCancelled(@NonNull DatabaseError error) {

             }
         });
         return view;
    }
}