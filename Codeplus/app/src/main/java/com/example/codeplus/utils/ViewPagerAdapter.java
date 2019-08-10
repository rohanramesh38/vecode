package com.example.codeplus.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.codeplus.R;

import java.util.List;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.ViewHolder> {

private List<String> mData;


private LayoutInflater mInflater;
private ViewPager2 viewPager2;


private int[] colorArray = new int[]{android.R.color.white, android.R.color.white, android.R.color.white, android.R.color.white};

        public ViewPagerAdapter(Context context, List<String> data, ViewPager2 viewPager2) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.viewPager2 = viewPager2;
        }


@Override
public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_viewpager, parent, false);
        return new ViewHolder(view);
        }

@Override
public void onBindViewHolder(ViewHolder holder, int position) {
        String animal = mData.get(position);
    //    holder.myTextView.setText(animal);
        holder.constraintLayout.setBackgroundResource(colorArray[position]);
        }

@Override
public int getItemCount() {
        return mData.size();
        }


public class ViewHolder extends RecyclerView.ViewHolder {
   // TextView myTextView;
    ConstraintLayout constraintLayout;
    Button button;

    ViewHolder(View itemView) {
        super(itemView);
      //  myTextView = itemView.findViewById(R.id.tvTitle);
        constraintLayout = itemView.findViewById(R.id.container);
        button = itemView.findViewById(R.id.btnToggle);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(viewPager2.getOrientation() == ViewPager2.ORIENTATION_VERTICAL)
                    viewPager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
                else{
                    viewPager2.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
                }
            }
        });
    }
}

}