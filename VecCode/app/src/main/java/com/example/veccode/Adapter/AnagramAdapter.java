package com.example.veccode.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.veccode.R;
import com.example.veccode.utils.certModel;

import java.util.ArrayList;
import java.util.List;

public class AnagramAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    ArrayList<String> modelList;
     private int color;

     AnagramAdapter.OnItemClickListener mItemClickListener;


    public AnagramAdapter(Context context, ArrayList<String> modelList, int color) {
        this.mContext = context;
        this.modelList = modelList;
        this.color=color;

      //  Toast.makeText(mContext, "baaaaahai"+modelList.get(0), Toast.LENGTH_SHORT).show();
//System.out.println(modelList);

    }

    public void updateList(ArrayList<String> modelList,int color) {
        this.modelList = modelList;
        this.color=color;
        notifyDataSetChanged();

    }

    @Override
    public AnagramAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.anagram_box_layout, viewGroup, false);

        return new AnagramAdapter.ViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        //Here you can fill your row view
        if (holder instanceof ViewHolder) {



            final String model = getItem(position);


            ViewHolder genericViewHolder = (ViewHolder) holder;

            if(model!=null)
            {

            Toast.makeText(mContext, "bhai"+model, Toast.LENGTH_SHORT).show();
            //    System.out.println(model.getTitle()+" "+model.getPic()+" "+model.getPrice());
            // Picasso.get().load(model.getImage()).networkPolicy(NetworkPolicy.OFFLINE).centerCrop().into( genericViewHolder.imgUser);

            RequestOptions myOptions = new RequestOptions()
                    .override(100, 100);

         //   System.out.println(position+" "+model+" ");
            genericViewHolder.img.setText(model);
          //  genericViewHolder.img.setText(""+model);

                int red,green,blue;
                red=Color.parseColor("#FF5252");
                green=Color.parseColor("#4CAF50");
                blue=Color.parseColor("#3498db");

                //  genericViewHolder.img.setBackgroundResource(model);

                if(color==1) {
                    genericViewHolder.img.setTextColor(red);
                  //  genericViewHolder.img.setBackgroundColor(R.color.red);
                }else  if(color==2)
                    genericViewHolder.img.setTextColor(green);
                else
                {
                    genericViewHolder.img.setTextColor(blue);
                }





        }else
            {

                genericViewHolder.itemView.setVisibility(View.GONE);
            }


        }


    }


    @Override
    public int getItemCount() {

        return  modelList.size();
    }

    public void SetOnItemClickListener(final AnagramAdapter.OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    private String getItem(int position) {
        return modelList.get(position);
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position, String model);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView img;

        // @BindView(R.id.img_user)
        // ImageView imgUser;
        // @BindView(R.id.item_txt_title)
        // TextView itemTxtTitle;
        // @BindView(R.id.item_txt_message)
        // TextView itemTxtMessage;
        // @BindView(R.id.radio_list)
        // RadioButton itemTxtMessage;
        // @BindView(R.id.check_list)
        // CheckBox itemCheckList;
        public ViewHolder(final View itemView) {
            super(itemView);

            // ButterKnife.bind(this, itemView);

            this.img = itemView.findViewById(R.id.imageAna);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClickListener.onItemClick(itemView, getAdapterPosition(), modelList.get(getAdapterPosition()));


                }
            });

        }
    }


}
