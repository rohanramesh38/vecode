package com.example.veccode.design.ui.profile;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.veccode.utils.certModel;
import com.example.veccode.utils.profile;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

public class ProfileViewModel extends ViewModel {



    private profile prof;
    private MutableLiveData<profile> arrprof;

    public ProfileViewModel() {




        prof=new profile();
        arrprof=new MutableLiveData<>();
        String u=FirebaseAuth.getInstance().getCurrentUser().getEmail().replace("@vecode.com","");
        u=u.replace("student","");

        FirebaseDatabase.getInstance().getReference().child("Profile").child("20"+ u.substring(4,6)).child(u).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                 prof=dataSnapshot.getValue(profile.class);
                arrprof.setValue(prof);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }


    public LiveData<profile> getProfData(){ return arrprof;}


}