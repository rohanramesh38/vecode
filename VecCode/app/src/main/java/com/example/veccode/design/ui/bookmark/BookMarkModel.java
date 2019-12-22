package com.example.veccode.design.ui.bookmark;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.veccode.utils.profile;
import com.example.veccode.utils.testModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

public class BookMarkModel extends ViewModel {

    String User="";

    private MutableLiveData<String> mText;
    private testModel tModel;
    private MutableLiveData<ArrayList<testModel>> livetModel;
    private  ArrayList<testModel> arrtm;


    public BookMarkModel() {
        mText = new MutableLiveData<>();
        livetModel= new MutableLiveData<>();
arrtm=new ArrayList<>();
        mText.setValue("This is tools fragment");


        User= FirebaseAuth.getInstance().getCurrentUser().getEmail().replace("student","");
        User= User.replace("@vecode.com","");


        FirebaseDatabase.getInstance().getReference().child("Profile").child("20"+User.substring(4,6)).child(User).addListenerForSingleValueEvent(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists())
                {
                 profile p= dataSnapshot.getValue(profile.class);
                 String allbook =p.getBook();

                String arr[]=allbook.split("<br>");
                String bb[] = new String[3];
                ArrayList<String> arrlik= new ArrayList<>();
                ArrayList<String> arrid= new ArrayList<>();

                for(int i=0;i<arr.length;i++)
                {

                    bb=arr[i].split("-");
                    arrlik.add(bb[0]);
                    arrid.add(bb[1]);

                }



                for(int i=0;i<arrlik.size();i++) {

                    if (arrlik.size() == arrid.size()) {
                        String ma[] = arrid.get(i).split("_");

                        ArrayList<String> arrayList=new ArrayList<>();
                        Collections.addAll(arrayList,ma);
                        arrayList  = (ArrayList<String>) arrayList.stream().distinct().collect(Collectors.<String>toList());
                      String ka[]=new  String[arrayList.size()];
                       for(int h=0;h<arrayList.size();h++)
                      {
                          ka[h]=arrayList.get(h);
                      }


                        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child(arrlik.get(i));


                        for (int j = 0; j < ka.length; j++) {
                            if (!ka[j].equals("NO"))
                            dbref.child(ka[j]).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    if(dataSnapshot.exists()) {
                                        testModel tm = dataSnapshot.getValue(testModel.class);
                                        arrtm.add(tm);
                                        System.out.println(arrtm);
                                        livetModel.setValue(arrtm);

                                        Collections.shuffle(arrtm);


                                    }

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });



                        }

                    }
                }

                System.out.println(arrtm);



                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public LiveData<String> getText() {
        return mText;
    }
    public LiveData<ArrayList<testModel>>getTestModel() {

        Collections.shuffle(arrtm);
livetModel.setValue(arrtm);
        return livetModel;
    }


}