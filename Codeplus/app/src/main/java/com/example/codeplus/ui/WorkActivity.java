package com.example.codeplus.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.codeplus.R;
import com.example.codeplus.utils.MyCustomPagerAdapter;
import com.example.codeplus.utils.RecyclerViewAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.Timer;
import java.util.TimerTask;

public class WorkActivity extends AppCompatActivity {

    private TabLayout indicator;
    private ViewPager viewPager;
    RecyclerView recyclerView,recyclerView1;
    RelativeLayout relativeLayout;
    RecyclerView.Adapter recyclerViewAdapter;
    RecyclerView.LayoutManager recylerViewLayoutManager;
    int images[] = {R.drawable.code2k19, R.drawable.code2k18, R.drawable.prize1, R.drawable.orange};

    int imagesl[]={R.drawable.c,R.drawable.cpp,R.drawable.java,R.drawable.py};
    MyCustomPagerAdapter myCustomPagerAdapter;
    String[]  fruits={"apple","grapes","mango","orange"};
    String[] subjects = {"ANDROID","PHP","BLOGGER","WORDPRESS","JOOMLA","ASP.NET","JAVA","C++","MATHS","HINDI","ENGLISH"};
    private int[] myImageList = new int[]{R.drawable.apple, R.drawable.mango,R.drawable.straw, R.drawable.pineapple,R.drawable.orange,R.drawable.blue,R.drawable.water};
    private String[] myImageNameList = new String[]{"Apple","Mango" ,"Strawberry","Pineapple","Orange","Blueberry","Watermelon"};

private TextView tv1,tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);

        tv1=findViewById(R.id.t1);
        tv2=findViewById(R.id.t2);

        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(WorkActivity.this,TrainCertActivity.class);
                i.putExtra("val","Certification");
                startActivity(i);
            }
        });


        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(WorkActivity.this,TrainCertActivity.class);
                i.putExtra("val","Training");
                startActivity(i);

            }
        });


        indicator = (TabLayout) findViewById(R.id.indicator);
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        myCustomPagerAdapter = new MyCustomPagerAdapter(this, images);
        viewPager.setAdapter(myCustomPagerAdapter);
        indicator.setupWithViewPager(viewPager, true);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView1 = (RecyclerView) findViewById(R.id.recyclerView1);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
        recyclerView1.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));

        recyclerViewAdapter = new RecyclerViewAdapter(this, imagesl);

        recyclerView.setAdapter(recyclerViewAdapter);
        //recyclerView1.setAdapter(recyclerViewAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerViewAdapter(WorkActivity.this, recyclerView, new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(WorkActivity.this, ""+fruits[position], Toast.LENGTH_SHORT).show();
                startActivity(new Intent(WorkActivity.this, McqMainActivity.class));

            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));

        recyclerView1.addOnItemTouchListener(new RecyclerViewAdapter(WorkActivity.this, recyclerView1, new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(WorkActivity.this, ""+fruits[position], Toast.LENGTH_SHORT).show();

              //  startActivity(new Intent(WorkActivity.this,MapsActivity.class));



            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));


                Timer timer = new Timer();
        timer.scheduleAtFixedRate(new SliderTimer(), 4000, 6000);

    }

    private class SliderTimer extends TimerTask {

        @Override
        public void run() {
            WorkActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                   if (viewPager.getCurrentItem() < images.length -1) {
                        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                    } else {
                        viewPager.setCurrentItem(0);
                    }
                }
            });
        }
    }

}
