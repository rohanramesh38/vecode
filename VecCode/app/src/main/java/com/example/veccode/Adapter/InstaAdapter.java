package com.example.veccode.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.veccode.R;
import com.example.veccode.utils.certModel;
import com.example.veccode.utils.instaModel;

import java.util.ArrayList;

public class InstaAdapter extends RecyclerView.Adapter<InstaAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<instaModel> modelList;

    private InstaAdapter.OnItemClickListener mItemClickListener;


    public InstaAdapter(Context context, ArrayList<instaModel> modelList) {
        this.mContext = context;
        this.modelList = modelList;

    }

    public void updateList(ArrayList<instaModel> modelList) {
        this.modelList = modelList;
        notifyDataSetChanged();

    }

    @Override
    public InstaAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_insta, viewGroup, false);

        return new InstaAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(InstaAdapter.ViewHolder holder, final int position) {

        //Here you can fill your row view
        if (holder instanceof InstaAdapter.ViewHolder) {
            final instaModel model = getItem(position);
            InstaAdapter.ViewHolder genericViewHolder = (InstaAdapter.ViewHolder) holder;


            //    System.out.println(model.getTitle()+" "+model.getPic()+" "+model.getPrice());


            // Picasso.get().load(model.getImage()).networkPolicy(NetworkPolicy.OFFLINE).centerCrop().into( genericViewHolder.imgUser);



            RequestOptions myOptions = new RequestOptions()
                    .override(100, 100);

            Glide.with(mContext)
                    .load(model.getImage()).fitCenter().override(1000,1000)
                    .into(genericViewHolder.imgUser);

            genericViewHolder.desc.setText(model.getDesc());
        }


    }


    @Override
    public int getItemCount() {

        return  modelList.size();
    }

    public void SetOnItemClickListener(final InstaAdapter.OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    private instaModel getItem(int position) {
        return modelList.get(position);
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position, instaModel model);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgUser;
        private TextView desc;



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

            this.imgUser = itemView.findViewById(R.id.coverImageView);
            this.desc=itemView.findViewById(R.id.desc);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClickListener.onItemClick(itemView, getAdapterPosition(), modelList.get(getAdapterPosition()));


                }
            });

        }
    }

}