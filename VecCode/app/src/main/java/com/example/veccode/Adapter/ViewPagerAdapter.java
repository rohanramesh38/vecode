package com.example.veccode.Adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.UriMatcher;
import android.os.Build;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
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


import com.example.veccode.Login.LoginActivity;
import com.example.veccode.R;
import com.example.veccode.utils.profile;
import com.example.veccode.utils.testModel;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import static java.lang.String.*;


public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.ViewHolder> {

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

        public ViewPagerAdapter(Context context, List<testModel> data, ViewPager2 viewPager2, Activity activity,String link,  String[] testArray) {
            this.context=context;
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.testArray=testArray;
        this.viewPager2 = viewPager2;
        this.link=link;
        this.activity=activity;
        this.testArray=testArray;

        for(int i=0;i<5;i++) {
            hashMap.put(i, "unknown");
            hashMapbook.put(i, "NO");
        }

        }


@Override
public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_viewpager, parent, false);




        return new ViewHolder(view);
        }

@Override
public void onBindViewHolder(final ViewHolder holder, final int position) {

            flag=0;
            if(mData.get(position)!=null) {
                String choice = mData.get(position).getChoice();
                String expl = mData.get(position).getExpl();
                String ques = mData.get(position).getQues();
                String sol = mData.get(position).getSol();
                String choiceArray[] = choice.split("___");

                String question = ques.replace("<br>", "\n");

                if (choiceArray.length == 4) {
                    holder.A.setText(choiceArray[0]);
                    holder.B.setText(choiceArray[1]);
                    holder.C.setText(choiceArray[2]);
                    holder.D.setText(choiceArray[3]);
                }
                hashMapAns.put(position, sol);


                holder.codeView.setText(question);
            }


    if (position == 0) {
        holder.prev.setVisibility(View.GONE);

    } else if (position == mData.size() - 1) {
        holder.next.setImageResource(R.drawable.done);

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

    holder.book.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (flag % 2 == 0) {

                hashMapbook.put(position,testArray[position]);
                holder.book.setImageResource(R.drawable.ic_bookmark);

                //
            }
            else {
                holder.book.setImageResource(R.drawable.ic_bookmark_border);
                hashMapbook.put(position,"NO");



            }
            flag++;


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
    TextView codeView;
     RadioGroup radioGroup;
     RadioButton A, B, C,D;
     ImageView prev,next,book;

     ArrayList<String> ans=new ArrayList<>();

    ViewHolder(View itemView) {
        super(itemView);
      //  myTextView = itemView.findViewById(R.id.tvTitle);
        constraintLayout = itemView.findViewById(R.id.container);
         prev=itemView.findViewById(R.id.prev);
         next=itemView.findViewById(R.id.next);

         radioGroup=itemView.findViewById(R.id.RadioGroup);
                A=itemView.findViewById(R.id.radA);
                book= itemView.findViewById(R.id.imageView3);
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
                    score=0;
                    for (int i=0;i<5;i++)

                    {
                        if((hashMap.get(i)).equals(hashMapAns.get(i)))
                        {
                            score = score + 1;
                            System.out.println(hashMap.get(i) + " " + (hashMapAns.get(i)+" "+score+" "+(hashMap.get(i)).equals(hashMapAns.get(i))));

                        }



                    }

                    showDialod(score);



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

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void showDialod(final int s) {
try {


             User= FirebaseAuth.getInstance().getCurrentUser().getEmail().replace("student","");
             User= User.replace("@vecode.com","");


        final profile[] p = new profile[1];


        FirebaseDatabase.getInstance().getReference().child("Profile").child("20"+User.substring(4,6)).child(User).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

           p[0] =dataSnapshot.getValue(profile.class);

           HashMap<String,String> hashmmboo=new HashMap<>();

                HashMap<String,String>  hashMappie = new HashMap<>();
                String bk=p[0].getBook(),pc="";


                 bk=p[0].getBook();
                 pc=p[0].getPiechart();

                String langinc="";

                String arr[]=bk.split("<br>");
                String arrpie[]=pc.split("_");

                try {


                    if(link.equals("ALLC")){

                        String lang_Score[]=arrpie[0].split("-");
                        int val=Integer.parseInt(lang_Score[1])+1;
                        lang_Score[1]=""+val;
                        arrpie[0]=String.join("-",lang_Score);


                    }else if(link.equals("ALLCPP")){

                        String lang_Score[]=arrpie[2].split("-");
                        int val=Integer.parseInt(lang_Score[1])+1;
                        lang_Score[1]=""+val;
                        arrpie[2]=String.join("-",lang_Score);

                    } else if(link.equals("ALLJAVA")){

                        String lang_Score[]=arrpie[3].split("-");
                        int val=Integer.parseInt(lang_Score[1])+1;
                        lang_Score[1]=""+val;
                        arrpie[3]=String.join("-",lang_Score);

                    }
                    else
                        if(link.equals("ALLCPYTHON")){

                            String lang_Score[]=arrpie[1].split("-");
                            int val=Integer.parseInt(lang_Score[1])+1;
                            lang_Score[1]=""+val;
                            arrpie[1]=String.join("-",lang_Score);

                        }

                pc=String.join("_",arrpie);


                }
                catch (IndexOutOfBoundsException e){}





                System.out.println(arrpie[0]);
                String bb[] = new String[3];
                ArrayList<String> arrlik= new ArrayList<>();
for(int i=0;i<arr.length;i++)
{
    //hashmmboo.put(arr[i].substring(0,arr[i].indexOf("-")),arr[i].substring((arr[i].indexOf("-")+1),arr[i].length()));


 bb=arr[i].split("-");
    arrlik.add(bb[0]);
System.out.println(bb.length);
    System.out.println(bb[1]);

 if(link.equals(bb[0]))
        hashmmboo.put(bb[0],bb[1]+getAllBM());
    else
        hashmmboo.put(bb[0],bb[1]);



}

String set="";

for(int i=0;i<hashmmboo.size();i++)
{
if(i==0)
       set=set+""+arrlik.get(i)+"-"+hashmmboo.get(arrlik.get(i));
else
    set=set+"<br>"+arrlik.get(i)+"-"+hashmmboo.get(arrlik.get(i));


}

                FirebaseDatabase.getInstance().getReference().child("Profile").child("20" + User.substring(4, 6)).child(User).child("Book").setValue(set);

           //AllBook

           if(s==5)
           {

               FirebaseDatabase.getInstance().getReference().child("Profile").child("20" + User.substring(4, 6)).child(User).child("Piechart").setValue(pc);

               AlertDialog.Builder alertDialog = new AlertDialog.Builder(activity);
               alertDialog.setMessage("You have successfully completed your daily mcq");

               LayoutInflater inflater = activity.getLayoutInflater();

               View dailog = inflater.inflate(R.layout.medalbox, null);
               alertDialog.setView(dailog);


               ImageView med = dailog.findViewById(R.id.medal);
               TextView tvmed = dailog.findViewById(R.id.tvmedal);
               tvmed.setText("Congrats " + p[0].getName());
               String allmedal = p[0].getMedal();

               SharedPreferences sp =activity.getSharedPreferences("com.veccode.PRIVATEDATA", Context.MODE_PRIVATE);
               SharedPreferences.Editor editor = sp.edit();
               // editor.putString("name", name);
               final Calendar c = Calendar.getInstance();
               int mYear = c.get(Calendar.YEAR); // current year
               int mMonth = c.get(Calendar.MONTH)+1; // current month
               int mDay = c.get(Calendar.DAY_OF_MONTH); // c
               editor.putString("Today"+link, ""+mDay+"_"+mMonth+"_"+mYear);
               editor.apply();

               String medarr[] = allmedal.split("_");
               if (medarr.length == 3) {
                   int a = Integer.parseInt(medarr[2]) + 1;
                   String val = a + "";
                   medarr[2] = val;
                   String res = String.join("_", medarr);
                   FirebaseDatabase.getInstance().getReference().child("Profile").child("20" + User.substring(4, 6)).child(User).child("Medal").setValue(res);
                   FirebaseDatabase.getInstance().getReference().child("ScoreBoardVec").child("20" + User.substring(4, 6)).child(User).setValue(res);


                   NavController navController = Navigation.findNavController(activity, R.id.nav_host_fragment);
                   NavigationView navigationView = activity.findViewById(R.id.nav_view);
                   View header = navigationView.getHeaderView(0);
                   TextView tHB=header.findViewById(R.id.bronze);

                   tHB.setText(medarr[2]);



               }



               med.setImageResource(R.drawable.bronzemedal);
               Dialog dialog = alertDialog.create();


               dialog.setCanceledOnTouchOutside(true);

               dialog.show();

           }
           else
           {
               Toast.makeText(activity, "Test competed", Toast.LENGTH_SHORT).show();
           }
                   NavController navController= Navigation.findNavController(activity,R.id.nav_host_fragment);
                navController.popBackStack();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


}
catch (NullPointerException e)
{

}

    }

    private String getAllBM() {
    String ss="";

    for(int i=0;i<5;i++) {
        if (!hashMapbook.get(i).equals("NO")) {

                ss = ss + "_" + hashMapbook.get(i);

        }

    }
        return ss;
        }

}