package com.example.veccode.design.ui.bookmark;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager2.widget.ViewPager2;

import com.example.veccode.Adapter.BookAdapter;
import com.example.veccode.Adapter.ViewPagerAdapter;
import com.example.veccode.R;
import com.example.veccode.utils.testModel;

import java.util.ArrayList;

public class BookMarkFragment extends Fragment {

    private BookMarkModel bookMarkModel;
    private ViewPager2 viewPager2;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        bookMarkModel = ViewModelProviders.of(this).get(BookMarkModel.class);
        View root = inflater.inflate(R.layout.fragment_bookmark, container, false);
        viewPager2 = root.findViewById(R.id.viewPagerboom);


        bookMarkModel.getTestModel().observe(this, new Observer<ArrayList<testModel>>() {
            @Override
            public void onChanged(ArrayList<testModel> testModels) {


                viewPager2.setAdapter(new BookAdapter(getContext(), testModels, viewPager2,getActivity()));


            }
        });


        return root;
    }
}