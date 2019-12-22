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

import java.util.ArrayList;

public class scoreVecAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private ArrayList<String> modelList,namelist,deptlist;

    private scoreVecAdapter.OnItemClickListener mItemClickListener;


    public scoreVecAdapter(Context context, ArrayList<String> modelList,ArrayList<String> namelist,ArrayList<String> deptlist) {
        this.mContext = context;
        this.modelList = modelList;
        this.namelist=namelist;
        this.deptlist=deptlist;


    }

    public void updateList(ArrayList<String> modelList) {
        this.modelList = modelList;
        notifyDataSetChanged();

    }

    @Override
    public scoreVecAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.score_vec, viewGroup, false);

        return new scoreVecAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        //Here you can fill your row view
        if (holder instanceof scoreVecAdapter.ViewHolder) {
            final String model = getItem(position);
            final  String name=getName(position);
            final String arr[]=model.split("_");




            scoreVecAdapter.ViewHolder genericViewHolder = (scoreVecAdapter.ViewHolder) holder;

            if(position==0){
            }
            else  if(position==1){}
            else  if(position==2){}
genericViewHolder.namevec.setText((position+1)+". "+name);

genericViewHolder.goldvec.setText(arr[0]);
            genericViewHolder.silvervec.setText(arr[1]);
            genericViewHolder.bronzevec.setText(arr[2]);

            System.out.println(model);
            //    System.out.println(model.getTitle()+" "+model.getPic()+" "+model.getPrice());


            // Picasso.get().load(model.getImage()).networkPolicy(NetworkPolicy.OFFLINE).centerCrop().into( genericViewHolder.imgUser);



            RequestOptions myOptions = new RequestOptions()
                    .override(100, 100);


        }


    }


    @Override
    public int getItemCount() {

        return  modelList.size();
    }

    public void SetOnItemClickListener(final scoreVecAdapter.OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    private String getItem(int position) {
        return modelList.get(position);
    }
    private String getName(int position) {
        return namelist.get(position);
    }
    private String getDept(int position) {
        return deptlist.get(position);
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position, String model);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        private TextView namevec,deptvec,goldvec,silvervec,bronzevec;


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

            this.namevec = itemView.findViewById(R.id.namevec);
            this.deptvec = itemView.findViewById(R.id.deptvec);
            this.goldvec = itemView.findViewById(R.id.goldvec);
            this.silvervec = itemView.findViewById(R.id.silvervec);
            this.bronzevec = itemView.findViewById(R.id.bronzevec);


try {
    itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mItemClickListener.onItemClick(itemView, getAdapterPosition(), modelList.get(getAdapterPosition()));


        }
    });
}
catch (NullPointerException nv)
{

}




        }
    }

}