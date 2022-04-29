package com.example.souq;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.souq.databinding.FragmentCustomerProfileBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;



public class Customer_Profile extends Fragment {
FragmentCustomerProfileBinding binding;
DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

String email;

    private static final int PICK_IMAGE = 100;
    Uri imageUri;
    int i=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding =FragmentCustomerProfileBinding.inflate(inflater,container,false);
        View view=binding.getRoot();

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                email=snapshot.child("current_customer").getValue().toString();
                binding.profileName.append(snapshot.child("All Users").child(email).child("name").getValue().toString());
                binding.profileEmail.append(snapshot.child("All Users").child(email).child("mail").getValue().toString());
                binding.profilePassword.append(snapshot.child("All Users").child(email).child("password").getValue().toString());
                binding.profilePhone.append(snapshot.child("All Users").child(email).child("phone").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        binding.getImage.setOnClickListener(view1 -> {
            openGallery();
        });

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild("Image Profile")){
                    if (snapshot.child("Image Profile").hasChild(email)){
                        String uri =snapshot.child("Image Profile").child(email).getValue().toString();
                        Picasso.with(getContext())
                                .load(Uri.parse(uri)) // mCategory.icon is a string
                                .resize(180, 180)
                                .error(R.drawable.ic_launcher_foreground) // default image to load
                                .into(binding.profileImage);
                    }
                    else{

                    }
                }
                else{

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return view;
    }


    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data.getData();
            binding.profileImage.setImageURI(imageUri);

            reference.child("Image Profile").child(email).setValue(imageUri.toString());
        }
    }
}