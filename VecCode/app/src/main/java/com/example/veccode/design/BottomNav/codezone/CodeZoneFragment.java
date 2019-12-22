package com.example.veccode.design.BottomNav.codezone;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.veccode.Adapter.InstaAdapter;
import com.example.veccode.R;
import com.example.veccode.utils.instaModel;

import java.util.ArrayList;

public class CodeZoneFragment extends Fragment {

    private CodeZoneViewModel codeZoneViewModel;
    private ArrayList<instaModel> rec;
    private RecyclerView recyclerViewInsta;
    InstaAdapter instaAdapter;

    public static CodeZoneFragment newInstance() {
        return new CodeZoneFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        codeZoneViewModel = ViewModelProviders.of(this).get(CodeZoneViewModel.class);
        View root = inflater.inflate(R.layout.fragment_code_zone, container, false);
        final TextView textView = root.findViewById(R.id.text_tools);
        codeZoneViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });


        recyclerViewInsta = root.findViewById(R.id.recyclerViewInsta);

        codeZoneViewModel.getRecInsta().observe(this, new Observer<ArrayList<instaModel>>() {
            @Override
            public void onChanged(ArrayList<instaModel> instaModel1) {
                rec=instaModel1;
                instaAdapter = new InstaAdapter(getContext(), rec);
                setAdapter();
            }
        });

        rec=new ArrayList<>();

        instaAdapter = new InstaAdapter(getContext(), rec);

        setAdapter();
        return root;



    }

    private void setAdapter() {


        recyclerViewInsta.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));

        recyclerViewInsta.setAdapter(instaAdapter);

        instaAdapter.SetOnItemClickListener(new InstaAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, instaModel model) {

                //    Toast.makeText(getActivity(), ""+rec.get(position).getLink(), Toast.LENGTH_SHORT).show();


            }
        });



    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        codeZoneViewModel = ViewModelProviders.of(this).get(CodeZoneViewModel.class);
        // TODO: Use the ViewModel
    }

}
