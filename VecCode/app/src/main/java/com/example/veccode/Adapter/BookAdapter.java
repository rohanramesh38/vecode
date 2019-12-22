package com.example.veccode.Adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.veccode.R;
import com.example.veccode.utils.profile;
import com.example.veccode.utils.testModel;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {

    private List<testModel> mData;

    private Context context;
    private LayoutInflater mInflater;
    private ViewPager2 viewPager2;
    String User,link,AllBook="";
    String testArray[];
    private Activity activity;
    private int flag=0,score=0;
    HashMap<Integer,String> hashMap=new HashMap<>(5);
    HashMap<Integer,String>  hashMapAns=new HashMap<>(5);
    HashMap<Integer,String>  hashMapbook=new HashMap<>(5);

    private int[] colorArray = new int[]{android.R.color.white, android.R.color.white, android.R.color.white, android.R.color.white};

    public BookAdapter(Context context, ArrayList<testModel> data, ViewPager2 viewPager2, Activity activity) {
        this.context=context;
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.viewPager2 = viewPager2;
        this.activity=activity;

        for(int i=0;i<5;i++) {
            hashMap.put(i, "unknown");
            hashMapbook.put(i, "NO");
        }

    }


    @Override
    public BookAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_book_mark, parent, false);




        return new BookAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final BookAdapter.ViewHolder holder, final int position) {

        flag=0;
        if(mData.get(position)!=null) {
            String choice = mData.get(position).getChoice();
            String expla = mData.get(position).getExpl();
            String ques = mData.get(position).getQues();
            String solu = mData.get(position).getSol();
            String choiceArray[] = choice.split("___");
            holder.expl.setText(expla);
            holder.sol.setText(solu);



            String question = ques.replace("<br>", "\n");

            if (choiceArray.length == 4) {
                holder.A.setText(choiceArray[0]);
                holder.B.setText(choiceArray[1]);
                holder.C.setText(choiceArray[2]);
                holder.D.setText(choiceArray[3]);
            }
            hashMapAns.put(position, solu);


            holder.codeView.setText(question);
        }


        if (position == 0) {
            holder.prev.setVisibility(View.GONE);

        } else if (position == mData.size() - 1) {
            holder.next.setVisibility(View.GONE);

        }
        //    holder.myTextView.setText(animal);
        holder.constraintLayout.setBackgroundResource(colorArray[0]);

        holder.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radA:
                        hashMap.put(position, holder.A.getText().toString());
                        break;
                    case R.id.radB:
                        hashMap.put(position, holder.B.getText().toString());
                        break;
                    case R.id.radC:
                        hashMap.put(position, holder.C.getText().toString());
                        break;
                    case R.id.radD:
                        hashMap.put(position, holder.D.getText().toString());
                        break;
                    default:
                        break;
                }


                System.out.println(hashMap.get(position));


            }
        });



    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        // TextView myTextView;
        ConstraintLayout constraintLayout;
        Button button;
        TextView codeView,sol,expl;
        RadioGroup radioGroup;
        RadioButton A, B, C,D;
        ImageView prev,next;

        ArrayList<String> ans=new ArrayList<>();

        ViewHolder(View itemView) {
            super(itemView);
            //  myTextView = itemView.findViewById(R.id.tvTitle);
            constraintLayout = itemView.findViewById(R.id.container);
            prev=itemView.findViewById(R.id.prev);
            next=itemView.findViewById(R.id.next);

            sol=itemView.findViewById(R.id.sol);
            expl=itemView.findViewById(R.id.expl);

            radioGroup=itemView.findViewById(R.id.RadioGroup);
            A=itemView.findViewById(R.id.radA);
            B=itemView.findViewById(R.id.radB );
            C=itemView.findViewById(R.id.radC);
            D=itemView.findViewById(R.id.radD );


            button = itemView.findViewById(R.id.btnToggle);
            codeView=itemView.findViewById(R.id.code_view);


            next.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onClick(View v) {

                    if(viewPager2.getCurrentItem()==mData.size()-1){
                    }
                    else
                    {
                        viewPager2.setCurrentItem(viewPager2.getCurrentItem()+1);
                    }
                }
            });

            prev.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    viewPager2.setCurrentItem(viewPager2.getCurrentItem()-1);

                }
            });




/*
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(viewPager2.getOrientation() == ViewPager2.ORIENTATION_VERTICAL)
                    viewPager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
                else{
                    viewPager2.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
                }
            }
        });*/
        }
    }





}
