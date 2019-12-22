package com.example.veccode.design.BottomNav.codezone;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.veccode.utils.instaModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class CodeZoneViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    private ArrayList<instaModel> recinsta;
    private MutableLiveData<ArrayList<instaModel>> arrecinsta;


    private MutableLiveData<String> mText;


    public CodeZoneViewModel() {
        recinsta=new ArrayList<>();
        arrecinsta=new MutableLiveData<>();

        mText = new MutableLiveData<>();
        mText.setValue("This is tools fragment");


        FirebaseDatabase.getInstance().getReference().child("InstaFeed").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot d1:dataSnapshot.getChildren())
                {
                    instaModel model=d1.getValue(instaModel.class);
                    recinsta.add(model);
                    System.out.println(model.getImage());

                }
                Collections.shuffle(recinsta);


                arrecinsta.setValue(recinsta);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public LiveData<ArrayList<instaModel>> getRecInsta(){ return arrecinsta;}

    public LiveData<String> getText() {
        return mText;
    }

}
