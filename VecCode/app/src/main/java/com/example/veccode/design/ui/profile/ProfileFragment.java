package com.example.veccode.design.ui.profile;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.veccode.Adapter.RecPgmAdapter;
import com.example.veccode.R;
import com.example.veccode.utils.profile;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static com.github.mikephil.charting.utils.ColorTemplate.COLORFUL_COLORS;


public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;
    private com.cooltechworks.views.shimmer.ShimmerRecyclerView recpgm;
    private ArrayList<String> arpgm=new ArrayList<>();

   private List<PieEntry> pieEntries = new ArrayList<>();
    private  profile prfiles;

    private  TextView textViewname,tvprofsilver,tvprofgold,tvprofbronze,tvregprof,tvdobprof,tvdeptprof;
    private de.hdodenhof.circleimageview.CircleImageView Imgprof;

    private ImageView plus;
    public static final int[] COLORFUL_COLORS = {Color.rgb(222,24,224),Color.rgb(122,204,24),Color.rgb(122,211,243), Color.rgb(100,111,221)};
    PieChart pieChart;

    private RecPgmAdapter recPgmAdapter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel =
                ViewModelProviders.of(this).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        recpgm=root.findViewById(R.id.recpgm);

        Imgprof =root.findViewById(R.id.imgprof);
        textViewname=root.findViewById(R.id.nameprof);

        tvprofsilver= root.findViewById(R.id.tvprofsilver);
        tvprofgold=root.findViewById(R.id.tvprofgold);
        tvprofbronze=root.findViewById(R.id.tvprofbronze);

        tvdeptprof=root.findViewById(R.id.deptprof);
        tvdobprof=root.findViewById(R.id.dobprof);
        tvregprof=root.findViewById(R.id.regnumprof);
         pieChart = root.findViewById(R.id.charts);

        plus=root.findViewById(R.id.plus);




        //"Piechart"



        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String arra[]={"C","C++","Python","Java","Java Script","Angular js","Node js","TypeScript","Kotlin","Ruby","Php","GO","Visual Basic .NET","Perl","Swift"};

                final ArrayList<Integer>  selectedItems =new ArrayList<>();
                // Where we track the selected items
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                builder.setMultiChoiceItems(arra, null,
                                new DialogInterface.OnMultiChoiceClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which,
                                                        boolean isChecked) {
                                        if (isChecked)
                                        {
                                            selectedItems.add(which);
                                        }
                                        else if (selectedItems.contains(which))
                                        {
                                            selectedItems.remove(Integer.valueOf(which));
                                        }
                                    }
                                })
                        .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {


                                String selected="";


                                 for(int i=0;i<selectedItems.size();i++)
                                {
                                    if(i==0)
                                        selected= selected+arra[selectedItems.get(i)];
                                    else
                                        selected= selected+"_"+arra[selectedItems.get(i)];

                                }

                                String u= FirebaseAuth.getInstance().getCurrentUser().getEmail().replace("@vecode.com","");
                                u=u.replace("student","");

                                FirebaseDatabase.getInstance().getReference().child("Profile").child("20"+ u.substring(4,6)).child(u).child("Lang").setValue(selected);
                                String m[]=selected.split("_");
                                arpgm.clear();

                                Collections.addAll(arpgm,m);
                                recPgmAdapter = new RecPgmAdapter(getContext(), arpgm);
                                recpgm.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                                recpgm.setAdapter(recPgmAdapter);
                                recPgmAdapter.SetOnItemClickListener(new RecPgmAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(View view, int position, String model) {

                                    }
                                });

                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });


                Dialog dialog =   builder.create();
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();

            }
        });


        profileViewModel.getProfData().observe(this, new Observer<profile>() {
            @Override
            public void onChanged(profile profile) {
                prfiles = profile;


                setpieChart();
                System.out.println(profile.getMedal());
                tvprofsilver.setText(profile.getMedal().split("_")[1]);
                tvprofgold.setText(profile.getMedal().split("_")[0]);
                tvprofbronze.setText(profile.getMedal().split("_")[2]);
                textViewname.setText(profile.getName());
                tvdeptprof.setText(profile.getDept());
                tvdobprof.setText(profile.getDob());
                tvregprof.setText(profile.getReg());

                if (!TextUtils.isEmpty(profile.getLang())) {
                    String arr[] = profile.getLang().split("_");

                    for (int i = 0; i < arr.length; i++)
                        arpgm.add(arr[i]);

                    recPgmAdapter = new RecPgmAdapter(getContext(), arpgm);
                    recpgm.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                    recpgm.setAdapter(recPgmAdapter);

                    recPgmAdapter.SetOnItemClickListener(new RecPgmAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position, String model) {

                        }
                    });
                } else {
                    arpgm.add(" Add ");
                    recpgm.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                    recpgm.setAdapter(recPgmAdapter);
                    recPgmAdapter.SetOnItemClickListener(new RecPgmAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position, String model) {

                        }
                    });

                }


            }
        });





        recPgmAdapter=new RecPgmAdapter(getContext(),arpgm);


        recpgm.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false));


        recpgm.setAdapter(recPgmAdapter);





  return root;
    }

    private void setpieChart() {
        try {


            String[] s3;
            String[] lang = {"C", "Python", "C++", "Java"};
            String[] s = prfiles.getPiechart().split("_");
            String[] s1 = new String[4];
            float[] percent = new float[4];
            float[] s2 = new float[4];

            String hell = " ";
            for (int i = 0; i < s.length; i++) {


                s3 = s[i].split("-");
                hell += s3[1];
                s1[i] = s3[0];
                s2[i] = Float.parseFloat(s3[1]);

            }


            float sum = 0;
            for (int i = 0; i < s2.length; i++) {
                sum += s2[i];
            }

            int x = 0;
            for (int i = 0; i < lang.length; i++) {
                if (lang[i].equals(s1[x])) {
                    percent[i] = s2[x] / sum;
                    x += 1;
                } else {
                    percent[i] = 0;
                }
            }


            List<PieEntry> pieEntries = new ArrayList<>();

            pieEntries.add(new PieEntry(percent[0] * 100, s1[0]));
            pieEntries.add(new PieEntry(percent[1] * 100, s1[1]));
            pieEntries.add(new PieEntry(percent[2] * 100, s1[2]));
            pieEntries.add(new PieEntry(percent[3] * 100, s1[3]));


            PieDataSet dataSet = new PieDataSet(pieEntries, "Statistics");
            dataSet.setColors(COLORFUL_COLORS);

            PieData data = new PieData(dataSet);

            pieChart.setData(data);
            pieChart.invalidate();


        }
        catch (NullPointerException e)
        {

        }
    }
}