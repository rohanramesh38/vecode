package com.example.codeplus.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.codeplus.R;
import com.example.codeplus.utils.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class McqMainActivity extends AppCompatActivity {
    ViewPager2 viewPager2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mcq_main);
        viewPager2 = findViewById(R.id.viewPager2);

        List<String> list = new ArrayList<>();
        list.add("First Screen");
        list.add("Second Screen");
        list.add("Third Screen");
        list.add("Fourth Screen");

        viewPager2.setAdapter(new ViewPagerAdapter(this, list, viewPager2));

    }
}
