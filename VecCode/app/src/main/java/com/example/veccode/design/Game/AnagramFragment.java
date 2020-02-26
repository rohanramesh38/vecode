package com.example.veccode.design.Game;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import com.example.veccode.Adapter.AnagramAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cooltechworks.views.shimmer.ShimmerRecyclerView;
import com.example.veccode.Adapter.AnagramAdapter;
import com.example.veccode.Login.LoginActivity;
import com.example.veccode.MainActivity;
import com.example.veccode.R;
import com.example.veccode.design.MainWorkActivity;
import com.example.veccode.utils.gameModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import static androidx.recyclerview.widget.LinearLayoutManager.VERTICAL;

public class AnagramFragment extends Fragment {

    private AnagramViewModel mViewModel;

   private TextView thint;
    private Button btcheck,btreset,btskip;

    String ques="life",ans="FILE",hint="";
    private static int SPLASH_TIME_OUT = 2000;


  private  View root;
  private  TextView textdisp;

    private ArrayList<String> allAlpha,ansAlpha;

   private ArrayList<gameModel>  listAllQues=new ArrayList<>();
   private ShimmerRecyclerView recyclerViewAnaAns,recyclerViewAnaQues;
   private AnagramAdapter anagramAdapter,anagramAdapterAns;

    public static AnagramFragment newInstance() {
        return new AnagramFragment();
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseDatabase.getInstance().getReference().child("Game").child("Jumble").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listAllQues.clear();
                for(DataSnapshot d1:dataSnapshot.getChildren())
                {
                    gameModel gm=d1.getValue(gameModel.class);


                    listAllQues.add(gm);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
         root= inflater.inflate(R.layout.anagram_fragment, container, false);


        FirebaseDatabase.getInstance().getReference().child("Game").child("Jumble").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
listAllQues.clear();
                for(DataSnapshot d1:dataSnapshot.getChildren())
                {
                    gameModel gm=d1.getValue(gameModel.class);


                    listAllQues.add(gm);
                }


                int index=getNext();


                ques=listAllQues.get(index).getQues().trim().toUpperCase();
                ans=listAllQues.get(index).getAns().trim().toUpperCase();
                hint=listAllQues.get(index).getExpl().trim();

                reset(root);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        DisplayMetrics displayMetrics=new DisplayMetrics();

//        Display display=getActivity().getWindowManager().getDefaultDisplay();
//        Point size=new Point();
//
//        display.getMetrics(displayMetrics);
//        display.getSize(size);
//        int width =size.x;
//        Toast.makeText(getContext(), ""+width, Toast.LENGTH_SHORT).show();
//        System.out.println(displayMetrics.widthPixels);
//
        btcheck=root.findViewById(R.id.btcheck);
        btreset=root.findViewById(R.id.btreset);
        btskip=root.findViewById(R.id.btskip);
        thint=root.findViewById(R.id.hint);



        btreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset(root);
            }
        });

        btskip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int index=getNext();

                ques=listAllQues.get(index).getQues().trim().toUpperCase();
                ans=listAllQues.get(index).getAns().trim().toUpperCase();
                hint=listAllQues.get(index).getExpl().trim();
                reset(root);
            }
        });

        btcheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s="";
                for(String a:ansAlpha)
                {
                    s=s+a;
                }
               System.out.println(s);
                System.out.println(ans);
                //System.out.println(s.equals(ans));
                if(s.equals(ans)){
                    anagramAdapterAns.updateList(ansAlpha,2);
                    int index=getNext();

                    ques=listAllQues.get(index).getQues().trim().toUpperCase();
                    ans=listAllQues.get(index).getAns().trim().toUpperCase();
                    hint=listAllQues.get(index).getExpl().trim();


                   // reset(root);

                    //CodeProcessor.init(this);
                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            reset(root);
                        }
                    }, SPLASH_TIME_OUT);


                }
                else{
                    anagramAdapter.updateList(allAlpha,0);
                    anagramAdapterAns.updateList(ansAlpha,1);
                }


            }
        });

    return root;

    }

    private void reset( View root) {



        ansAlpha=new ArrayList<>();
        allAlpha=new ArrayList<>();

        allAlpha.clear();
        ansAlpha.clear();

        recyclerViewAnaAns=root.findViewById(R.id.recAnaAns);
        recyclerViewAnaQues=root.findViewById(R.id.recAnaQues);
        textdisp=root.findViewById(R.id.textdisp);
        textdisp.setText("");


        recyclerViewAnaQues.setLayoutManager(new GridLayoutManager(getContext(),4));
        recyclerViewAnaAns.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));

        ansAlpha.clear();
        allAlpha.clear();


        anagramAdapter=new AnagramAdapter(getContext(),allAlpha,0);
        anagramAdapterAns=new AnagramAdapter(getContext(),ansAlpha,0);



        recyclerViewAnaQues.setAdapter(anagramAdapter);
        recyclerViewAnaAns.setAdapter(anagramAdapterAns);




        String s[]=ques.toUpperCase().trim().split("");
        List<String> l=new ArrayList<String>();
        allAlpha.addAll(Arrays.asList(s));
        allAlpha.remove(0);

        thint.setText(hint);

        anagramAdapter=new AnagramAdapter(getContext(),allAlpha,0);
        recyclerViewAnaQues.setAdapter(anagramAdapter);

        anagramAdapter.SetOnItemClickListener(new AnagramAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, String model) {

                ansAlpha.add(model);
                allAlpha.remove(position);
                String s="";
                for(String a:ansAlpha)
                {
                    s=s+a;
                }
                textdisp.setText(s);
                anagramAdapter.updateList(allAlpha,0);

                anagramAdapterAns=new AnagramAdapter(getContext(),ansAlpha,0);
                recyclerViewAnaAns.setAdapter(anagramAdapterAns);

                anagramAdapterAns.SetOnItemClickListener(new AnagramAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position, String model) {
                        allAlpha.add(model);
                        ansAlpha.remove(position);
                        String s="";
                        for(String a:ansAlpha)
                        {
                            s=s+a;
                        }
                        textdisp.setText(s);


                        anagramAdapter.updateList(allAlpha,0);
                        anagramAdapterAns.updateList(ansAlpha,0);

                    }
                });


            }
        });


    }




    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(AnagramViewModel.class);
        // TODO: Use the ViewModel




    }


  public int  getNext(){
      Random ram=new Random();


        return ((int)( (Math.random()*(listAllQues.size()-1))+1));
  }

}
