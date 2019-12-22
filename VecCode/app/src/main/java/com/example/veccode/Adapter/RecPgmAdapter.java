package com.example.veccode.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.request.RequestOptions;
import com.example.veccode.R;

import java.util.ArrayList;

public class RecPgmAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder>{

private Context mContext;
private ArrayList<String> modelList;

private RecPgmAdapter.OnItemClickListener mItemClickListener;


public RecPgmAdapter(Context context,ArrayList<String> modelList){
        this.mContext=context;
        this.modelList=modelList;



        }

public void updateList(ArrayList<String> modelList){
        this.modelList=modelList;
        notifyDataSetChanged();

        }

@Override
public RecPgmAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){

        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.program_text,viewGroup,false);

        return new RecPgmAdapter.ViewHolder(view);
        }

@Override
public void onBindViewHolder(RecyclerView.ViewHolder holder,final int position){

        //Here you can fill your row view
        if(holder instanceof RecPgmAdapter.ViewHolder){
final String model=getItem(position);



            RecPgmAdapter.ViewHolder genericViewHolder=(RecPgmAdapter.ViewHolder)holder;

        if(position==0){
        }
        else if(position==1){}
        else if(position==2){}


        genericViewHolder.namevec.setText(model);
        System.out.println(model);
        //    System.out.println(model.getTitle()+" "+model.getPic()+" "+model.getPrice());


        // Picasso.get().load(model.getImage()).networkPolicy(NetworkPolicy.OFFLINE).centerCrop().into( genericViewHolder.imgUser);


        RequestOptions myOptions=new RequestOptions()
        .override(100,100);


        }


        }


@Override
public int getItemCount(){

        return modelList.size();
        }

public void SetOnItemClickListener(final RecPgmAdapter.OnItemClickListener mItemClickListener){
        this.mItemClickListener=mItemClickListener;
        }

private String getItem(int position){
        return modelList.get(position);
        }



public interface OnItemClickListener {
    void onItemClick(View view, int position, String model);
}

public class ViewHolder extends RecyclerView.ViewHolder {


    private TextView namevec;


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

        this.namevec = itemView.findViewById(R.id.tvpgm);



        try {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClickListener.onItemClick(itemView, getAdapterPosition(), modelList.get(getAdapterPosition()));


                }
            });
        } catch (NullPointerException nv) {

        }


    }
}
}

