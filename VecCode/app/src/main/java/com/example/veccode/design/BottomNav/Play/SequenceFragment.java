package com.example.veccode.design.BottomNav.Play;


import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.veccode.R;
import com.example.veccode.utils.gameModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 */
public class SequenceFragment extends Fragment {
    private Button btcheck,btreset,btskip;

    private ArrayList<gameModel> listAllSeqQues=new ArrayList<>();

    private TextView thint,tseq;

    private EditText ETans;
    String ques="",ans="",hint="";
    //private static int SPLASH_TIME_OUT = 2000;

    public SequenceFragment() {
        // Required empty public constructor
    }

View root;
    @Override
    public void onStart() {
        super.onStart();

        FirebaseDatabase.getInstance().getReference().child("Game").child("Sequence").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listAllSeqQues.clear();
                for(DataSnapshot d1:dataSnapshot.getChildren())
                {
                    gameModel gm=d1.getValue(gameModel.class);

                        System.out.println(gm.getQues());

                        if(gm.getQues().length()>4)
                    listAllSeqQues.add(gm);
                }

                reset(root);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //
        // Inflate the layout for this fragment
        root= inflater.inflate(R.layout.fragment_sequence, container, false);



        btcheck=root.findViewById(R.id.btcheck);
        btreset=root.findViewById(R.id.btreset);
        btskip=root.findViewById(R.id.btskip);
        thint=root.findViewById(R.id.hint);
        tseq=root.findViewById(R.id.seq);
        thint=root.findViewById(R.id.hint);
        ETans=root.findViewById(R.id.anseq);


btskip.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        reset(root);
    }
});

btcheck.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
     String yourans  = ETans.getText().toString();
     if(yourans.equals(ans)) {

         int red,green,blue;
      //   red= Color.parseColor("#FF5252");
         green=Color.parseColor("#4CAF50");
      //   blue=Color.parseColor("#3498db");


         thint.setTextColor(green);
         String ans = hint.replace("__", "\n");
         thint.setText(ans);
        /* new Handler().postDelayed(new Runnable() {

             @Override
             public void run() {
                 reset(root);
             }
         }, SPLASH_TIME_OUT);
*/


     }}});


btreset.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {


        int red,green,blue;
        //   red= Color.parseColor("#FF5252");
      //  green=Color.parseColor("#4CAF50");
           blue=Color.parseColor("#3498db");

           thint.setTextColor(blue);

        String ans = hint.replace("__", "\n");
        thint.setText(ans);


    }
});




            reset(root);

        return root;
    }

    private void reset(View root) {


        if(listAllSeqQues.size()>0) {
            int index = getNext();
            ques = listAllSeqQues.get(index).getQues().trim().toUpperCase();
            ans = listAllSeqQues.get(index).getAns().trim().toUpperCase();
            hint = listAllSeqQues.get(index).getExpl().trim();
        }
        tseq.setText(ques);


    }


    public int  getNext(){
        Random ram=new Random();


        return ((int)( (Math.random()*(listAllSeqQues.size()-1))+1));
    }

}
