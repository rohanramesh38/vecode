package com.example.veccode.design.ui.home;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.veccode.R;
import com.example.veccode.utils.certModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class HomeViewModel extends ViewModel {



    private ArrayList<certModel> rec, rec1,recRound;
    private MutableLiveData<ArrayList<certModel>> arrec,arrec1,arrRound;

    private ArrayList<certModel> vpgr;
    private MutableLiveData<ArrayList<certModel>> arrvpgr;


    public HomeViewModel() {



        /////////////////////////////////

        rec=new ArrayList<>();
        rec1=new ArrayList<>();
        vpgr=new ArrayList<>();
        recRound=new ArrayList<>();


        arrec=new MutableLiveData<>();
        arrec1=new MutableLiveData<>();
        arrvpgr=new MutableLiveData<>();
        arrRound=new MutableLiveData<>();

        ///////////////////////////////////////



        FirebaseDatabase.getInstance().getReference().child("Enhance").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                rec.clear();
                for(DataSnapshot d1:dataSnapshot.getChildren())
                {
                    certModel model=d1.getValue(certModel.class);
                    rec.add(model);

                }
                Collections.shuffle(rec);
                arrec.setValue(rec);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        FirebaseDatabase.getInstance().getReference().child("DeveloperEssential").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                recRound.clear();
                for(DataSnapshot d1:dataSnapshot.getChildren())
                {
                    certModel model=d1.getValue(certModel.class);
                    recRound.add(model);

                }
                Collections.shuffle(recRound);
                arrRound.setValue(recRound);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




        FirebaseDatabase.getInstance().getReference().child("TopSkills").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                rec1.clear();

                for(DataSnapshot d1:dataSnapshot.getChildren())
                {
                    certModel model=d1.getValue(certModel.class);
                    rec1.add(model);

                }
                Collections.shuffle(rec1);
                arrec1.setValue(rec1);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        FirebaseDatabase.getInstance().getReference().child("MainAd").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                vpgr.clear();
                for(DataSnapshot d1:dataSnapshot.getChildren())
                {
                    certModel model=d1.getValue(certModel.class);
                    vpgr.add(model);

                }
                arrvpgr.setValue(vpgr);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });






    }

    public LiveData<ArrayList<certModel>> getRec(){ return arrec;}

    public LiveData<ArrayList<certModel>> getRec1(){ return arrec1;}


    public LiveData<ArrayList<certModel>> getRecRound(){ return arrRound;}



    public LiveData<ArrayList<certModel>> getViewPagerData(){ return arrvpgr;}




}