package com.example.veccode.Adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.veccode.R;
import com.example.veccode.utils.certModel;

import java.util.ArrayList;

public class MyCustomPagerAdapter extends PagerAdapter {
    Context context;
    ArrayList<certModel> models;
    LayoutInflater layoutInflater;
Activity activity;

    public MyCustomPagerAdapter(Context context, ArrayList<certModel> models,Activity activity) {
        this.context = context;
        this.models = models;
        this.activity=activity;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View itemView = layoutInflater.inflate(R.layout.item, container, false);

        ImageView imageView = itemView.findViewById(R.id.imageView);


        RequestOptions myOptions = new RequestOptions()
                .override(100, 100);

        Glide.with(context)
                .load(models.get(position).getImage()).fitCenter().override(1000,1000)
                .into(imageView);


        container.addView(itemView);

        //listening to image click
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   NavController navController=
//
                Bundle args=new Bundle();
                args.putString("image",models.get(position).getImage());
                args.putString("desc",models.get(position).getDesc());
                args.putString("link",models.get(position).getLink());
                args.putString("name",models.get(position).getName());
                args.putString("rules",models.get(position).getRules());
                NavController navController= Navigation.findNavController(activity,R.id.nav_host_fragment);
                navController.navigate(R.id.to_adExplainFragment,args);
            }
        });

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}