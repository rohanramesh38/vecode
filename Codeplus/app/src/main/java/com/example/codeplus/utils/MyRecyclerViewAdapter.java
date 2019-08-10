package com.example.codeplus.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.codeplus.R;

import java.util.ArrayList;
import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder>  {

    private List<String> Address,Distance;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private ArrayList examheading,clientheading;

    // data is passed into the constructor
    public MyRecyclerViewAdapter(Context context, List<String> data, List<String> data1) {
        this.mInflater = LayoutInflater.from(context);
        this.Address = data;
        this.Distance = data1;

    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_row, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String addr = Address.get(position);
        String dist = Distance.get(position);
        holder.myTextViewaddr.setText(addr);
        holder.myTextViewdist.setText(dist);

    }

    // total number of rows
    @Override
    public int getItemCount() {
        return Address.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextViewaddr,myTextViewdist;

        ViewHolder(View itemView) {
            super(itemView);
            myTextViewaddr = itemView.findViewById(R.id.addr);
            myTextViewdist = itemView.findViewById(R.id.dist);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    String getItem(int id) {
        return Address.get(id);
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    public void UpdateItemsList(List<String> addr,List<String> dist)
    {
        Address=new ArrayList();
        Address.addAll(addr);
        Distance=new ArrayList();
        Distance.addAll(dist);
        notifyDataSetChanged();

    }

}