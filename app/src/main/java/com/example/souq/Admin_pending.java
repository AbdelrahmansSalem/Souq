package com.example.souq;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Admin_pending extends Fragment {

    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();

    ArrayList <String> sellers= new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_admin_pending, container, false);

        TextView name=view.findViewById(R.id.seller_info_name);
        TextView mail=view.findViewById(R.id.seller_info_email);
        TextView id=view.findViewById(R.id.seller_info_id);
        TextView phone=view.findViewById(R.id.seller_info_phone);
        TextView type=view.findViewById(R.id.seller_info_product_type);

        CardView cardView =view.findViewById(R.id.materialCardView);

        RecyclerView recyclerView =view.findViewById(R.id.pendinglist);
        Pending_adaptor adpator=new Pending_adaptor(sellers,container.getContext(),position -> {

            //on click

            Toast.makeText(container.getContext(), sellers.get(position), Toast.LENGTH_SHORT).show();
            databaseReference.child("current_user").setValue(sellers.get(position));
            final String[] current_user = new String[1];
            databaseReference.child("current_user").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    current_user[0] =snapshot.getValue().toString();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            final Seller[] seller = new Seller[1];
            final String[] n =new String[1];
            final String[] m = new String[1];
            final String[] i = new String[1];
            final String[] p = new String[1];
            final String[] pass = new String[1];
            final String[] t = new String[1];
            databaseReference.child("Pending Emails").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    n[0] =snapshot.child(current_user[0]).child("name").getValue().toString();
                    m[0] =snapshot.child(current_user[0]).child("mail").getValue().toString();
                    i[0] =snapshot.child(current_user[0]).child("id").getValue().toString();
                    p[0] =snapshot.child(current_user[0]).child("phone").getValue().toString();
                    t[0] =snapshot.child(current_user[0]).child("product_type").getValue().toString();
                    pass[0]=snapshot.child(current_user[0]).child("password").getValue().toString();
                    name.setText(n[0]);
                    mail.setText(m[0]);
                    id.setText(i[0]);
                    phone.setText(p[0]);
                    type.setText(t[0]);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            Button cancel=view.findViewById(R.id.cancel);

            cancel.setOnClickListener(view1 -> {
                cardView.animate().alpha(0f).setDuration(1300);
            });
            Button decline=view.findViewById(R.id.decline);
            decline.setOnClickListener(view1 ->  {
                databaseReference.child("Pending Emails").child(current_user[0]).removeValue();
                Toast.makeText(getContext(), "Just Deleted", Toast.LENGTH_SHORT).show();
                cardView.animate().alpha(0f).setDuration(1300);

            });

            Button accept=view.findViewById(R.id.accept);
            accept.setOnClickListener(view1 -> {
                seller[0] =new Seller(n[0], m[0], p[0], pass[0], t[0], i[0]);
                databaseReference.child("Seller").child(m[0]).setValue(seller[0]);
                databaseReference.child("All Users").child(m[0]).setValue(seller[0]);
                databaseReference.child("All Users").child(m[0]).child("user_type").setValue("Seller");
                databaseReference.child("Pending Emails").child(current_user[0]).removeValue();
                Toast.makeText(getContext(), "Just Added", Toast.LENGTH_SHORT).show();
                cardView.animate().alpha(0f).setDuration(1300);
            });
            recyclerView.animate().alpha(0).setDuration(2000);
            cardView.animate().alpha(1f).setDuration(2000);
            //startActivity(new Intent(getActivity(),Pending_seller_info.class));

        });
        recyclerView.setAdapter(adpator);
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));

        databaseReference.child("Pending Emails").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1: snapshot.getChildren()) {
                    sellers.add(snapshot1.child("name").getValue().toString());

                }
                adpator.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return view;
    }


}