package com.example.veccode.design.ui.mcq;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.veccode.utils.certModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class McqViewModel extends ViewModel {



    private ArrayList<certModel> recMain;
    private MutableLiveData<ArrayList<certModel>> arrecMain;

    public McqViewModel() {


        recMain=new ArrayList<>();
        arrecMain=new MutableLiveData<>();

        FirebaseDatabase.getInstance().getReference().child("MainGrid").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot d1:dataSnapshot.getChildren())
                {
                    certModel model=d1.getValue(certModel.class);
                    recMain.add(model);
                    System.out.println(model.getImage());

                }

                arrecMain.setValue(recMain);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }

    public LiveData<ArrayList<certModel>> getRecMain(){ return arrecMain;}


}